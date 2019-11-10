package com.jffox.cloud.jobadmin;

import com.jffox.cloud.utils.DateUtils;
import com.jffox.cloud.utils.RegUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Slf4j
public class JobDstBusiness {


    public static void main(String[] args) throws Exception {

       // final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironment();


        ParameterTool params = ParameterTool.fromArgs(args);

        /** 配置信息 */
        int parall = params.getInt("parall", 5);
        String kafkaGroup = params.get("kafkaGroup", "dl_cert_feature_bs_on_group");
        String jobName = params.get("jobName", "dl_cert_feature_bs_on");

        env.setParallelism(parall);

        /**参数*/
        Configuration fig = new Configuration();
        fig.setString("jobName", jobName);
        env.getConfig().setGlobalJobParameters(fig);
        /**source kafka**/
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "web01:9092,web02:9092,web04:9092");
        props.setProperty("group.id", kafkaGroup);

        props.setProperty("request.timeout.ms", "400000");//default:40000
        props.setProperty("session.timeout.ms", "100000");//default:10000
        props.setProperty("fetch.max.wait.ms", "12000");

        List<String> topicList = new ArrayList<>();
        topicList.add("sync_db_wk");
        topicList.add("sync_db_hrt");
        topicList.add("sync_db_saas_ph");
        topicList.add("sync_db_sass");
        topicList.add("sync_db_cf");

        DataStreamSource<String> stream1 = createStream(env, props, topicList, params);

        //风控mq数据的topic
        /**处理 etl*/
        stream1.filter(json -> kafkaFilter(json))
                .map(jsonData -> {
                    try {
                        //计算逻辑
                        //  calculate(jsonData);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return jsonData;
                }).name("kafkaMess");


        env.execute();
    }


    private static DataStreamSource<String> createStream(StreamExecutionEnvironment env, Properties props,
                                                         List<String> topicList, ParameterTool params) {
        FlinkKafkaConsumer<String> consumer = new FlinkKafkaConsumer<>(topicList,
                new SimpleStringSchema(), props);
        String startType = params.get("startType");
        if ("latest".equals(startType)) {
            consumer.setStartFromLatest();
        } else if ("timestamp".equals(startType)) {
            Long datetime = DateUtils.turnTime2Long(params.get("datetime"));
            consumer.setStartFromTimestamp(datetime);
        } else {
            consumer.setStartFromGroupOffsets();
        }
        return env.addSource(consumer);
    }


    /**
     * 筛选要处理的数据
     *
     * @param json
     * @return
     */
    private static boolean kafkaFilter(String json) {

        //过滤非标准格式，及delete的数据
        if (json.contains("\"type\":\"delete\"") || !(json.contains("\"database\"") && json.contains("\"table\""))) {
            return false;
        }

        //共享额度用的表

        if (json.contains("wk_db_loan")) {
            if (json.contains("wk_customer_log") || json.contains("wk_quota_check_log")
                    || json.contains("wk_auth_info")) { //头条建模用表
                return true;
            }
        }

        if (json.contains("wk_db_quota")) {
            if (RegUtil.isInclude(json, "t_wk_quota_master_\\d{1}")
                    || RegUtil.isInclude(json, "t_wk_quota_master_status_log_\\d{1}")) {
                return true;
            }
        }


        if (json.contains("cf_db_market")) {
            if (json.contains("mks_coupon_detail")) {
                return true;
            }
        }

        return false;
    }

}
