package com.jffox.cloud.configs;

import com.jffox.cloud.constants.Exceptions;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.configuration.Configuration;

import java.util.Properties;

/**
 * @author yanggang
 * @version 1.0
 * @date 2019-11-01
 * @describe kafka配置对象信息
 * @since jdk 1.8
 */
@Slf4j
public class KafkaConsumerConfig {
    /**
     * @param args      提交数组参数
     * @param envConfig 配置信息
     * @return 返回配置对象
     */
    public static Properties getConfig(String[] args, Configuration envConfig) {
        ParameterTool params = null;
        String bootstrapServers = null;
        String groupId = null;
        try {
            params = ParameterTool.fromArgs(args);
            bootstrapServers = params.get("bootstrap.servers", "web01:9092,web02:9092,web04:9092");
            groupId = params.get("group.id", "dl-ffe-" + envConfig.getString("jobName", "unknowJob") + "-gi");

        } catch (Exception e) {
            log.error(Exceptions.fromId(1).name);
        }
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", bootstrapServers);
        props.setProperty("group.id", groupId);
        props.setProperty("request.timeout.ms", "400000");//default:40000
        props.setProperty("session.timeout.ms", "100000");//default:10000
        props.setProperty("fetch.max.wait.ms", "12000");
        return props;
    }
}
