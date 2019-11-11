package com.jffox.cloud.connectors;

import com.jffox.cloud.constants.Exceptions;
import com.jffox.cloud.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;

import java.lang.annotation.Documented;
import java.util.List;
import java.util.Properties;
/**
 * @author yanggang
 * @version 1.0
 * @date 2019-11-01
 * @describe kafka连接对象
 * @since jdk 1.8
 */

@Slf4j
public class Kafka {
    /**
     * @param env       流环境
     * @param props     配置信息
     * @param topicList 主题list
     * @param params    提交参数
     * @return 返回输入数据源流
     */
    public static DataStreamSource<String> consumer(StreamExecutionEnvironment env, Properties props, List<String> topicList, ParameterTool params) {
        /*statType is latest or history
         * if the is latest offset is setStartFromLatest
         * else if the is history is setStartFromTimestamp*/
        String startType = "";
        String dateTime = "";
        FlinkKafkaConsumer<String> consumer = new FlinkKafkaConsumer<>(topicList,
                new SimpleStringSchema(), props);
        try {
            startType = params.has("start.type") ? params.get("start.type") : "";
            dateTime = params.has("date.time") ? params.get("date.time") : "";
        } catch (Exception e) {
            log.error(Exceptions.fromId(1).name);
        }
        if ("latest".equals(startType)) {
            consumer.setStartFromLatest();
        } else if ("history".equals(startType)) {
            Long timestamp = DateUtils.turnTime2Long(dateTime);
            consumer.setStartFromTimestamp(timestamp);
        } else {
            consumer.setStartFromGroupOffsets();
        }
        return env.addSource(consumer);
    }

    /**
     * @param props 配置信息
     * @param topic 主题
     * @return 返回kafka生产者对象
     */
    public static FlinkKafkaProducer<String> producer(Properties props, String topic) {

        FlinkKafkaProducer<String> myProducer = new FlinkKafkaProducer<String>(
                topic,
                new SimpleStringSchema(), props);

        myProducer.setWriteTimestampToKafka(true);
        return myProducer;
    }
}
