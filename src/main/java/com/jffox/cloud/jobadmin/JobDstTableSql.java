package com.jffox.cloud.jobadmin;

import com.jffox.cloud.configs.KafkaConsumerConfig;
import com.jffox.cloud.connectors.Kafka;
import com.jffox.cloud.constants.Exceptions;
import com.jffox.cloud.function.FunFilterFunction;
import com.jffox.cloud.function.FunFlatMapFunction;
import com.jffox.cloud.function.FunMapFunction;
import com.jffox.cloud.function.FunMapFunctionTuple2;
import com.jffox.cloud.sources.SourceMapping;
import com.jffox.cloud.targets.TargetMapping;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.contrib.streaming.state.RocksDBStateBackend;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.CheckpointConfig;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.java.StreamTableEnvironment;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author yanggang
 * @version 1.0
 * @date 2019-11-01
 * @describe 公共数据计算job入口
 * @since jdk 1.8
 */
@Slf4j
public class JobDstTableSql {


    public static void main(String[] args) throws Exception {
        log.info("###############StreamExecutionEnvironment is starting #######################");
        /**
         * 环境参数获取
         * */
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        //final StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironment();
        Configuration envConfig = new Configuration();
        Properties kafkaConsumerProperties = new Properties();
        ParameterTool params = null;
        String flinkJobName = null;
        String targetTopic = TargetMapping.getTargetTopic("4");
        int parallelism = 0;
        try {
            params = ParameterTool.fromArgs(args);
            parallelism = params.getInt("parallelism", 1);
            flinkJobName = params.get("flink.job.name", "JobDstCommon");
        } catch (Exception e) {
            log.error("params--{},params", Exceptions.fromId(1).name);
        }

        /**
         * 执行
         * */
        try {
            /*flink运行环境赋值*/
            env.setParallelism(parallelism);
            envConfig.setString("jobName", flinkJobName);
            env.getConfig().setGlobalJobParameters(envConfig);
            //每隔5秒保存一次以及相关取消退出时保留
            CheckpointConfig chkConfig = env.getCheckpointConfig();
            chkConfig.enableExternalizedCheckpoints(CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION);
            env.enableCheckpointing(10000);
            /**
             * 如果不设置检查点路径将会已配置全局为准
             * */
            //env.setStateBackend(new FsStateBackend("hdfs:///flink/checkpoints/" + flinkJobName));
            //env.setStateBackend(new RocksDBStateBackend("hdfs:///flink/checkpoints/" + flinkJobName));
            /**
             * 增加table环境配置
             */
            StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env);

            /**
             * kafka数据源加载配置
             * */
            kafkaConsumerProperties = KafkaConsumerConfig.getConfig(args, envConfig);
            List<String> topicList = new ArrayList<>();
            topicList = SourceMapping.getTopicList(1);
            DataStream<String> stream = Kafka.consumer(env, kafkaConsumerProperties, topicList, params).uid(flinkJobName + "-consumer").shuffle();
            /**
             * 数据流dst-operations
             *
             * */
            Table table = tableEnv.fromDataStream(stream, "user, product, amount");
            Table result = tableEnv.sqlQuery(
                    "SELECT SUM(amount) FROM " + table + " WHERE product LIKE '%Rubber%'");

            env.execute(flinkJobName);
            tableEnv.execute(flinkJobName);
            log.info("###############StreamExecutionEnvironment is started #######################");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("StreamUnknown--{}", Exceptions.fromId(-1).name);
        }
    }
}
