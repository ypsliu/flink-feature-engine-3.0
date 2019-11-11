package com.jffox.cloud.sources;

import com.jffox.cloud.configs.KafkaConsumerConfig;
import com.jffox.cloud.constants.DataItems;
import com.jffox.cloud.constants.KafkaTopics;
import com.jffox.cloud.constants.KafkaUrls;
import org.apache.flink.configuration.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author yanggang
 * @version 1.0
 * @date 2019-10-01
 * @describe 数据源映射对象类
 * @since jdk 1.8
 */
public class SourceMapping {
    /**
     * @param mappingId kafka实例配置方法输入id
     * @return 返回kafka返回连接实例地址
     */
    public static Properties getProperties(Integer mappingId) {
        String[] args = null;
        Configuration envConfig = null;
        Properties properties = KafkaConsumerConfig.getConfig(args, envConfig);

        if (mappingId != null) {
            switch (mappingId) {
                case 1:
                    properties.setProperty("bootstrap.servers", KafkaUrls.fromId("1").enName);
                    break;
                case 2:
                    break;
            }
        }
        return properties;
    }

    /**
     * @param mappingId 消费kafka-topic主题id
     * @return 消费kafka-topic主题list
     */
    public static List<String> getTopicList(Integer mappingId) {
        List<String> topicList = new ArrayList<String>();
        if (mappingId != null) {
            switch (mappingId) {
                case 1:
                    topicList.add(KafkaTopics.fromId("1").enName);
                    topicList.add(KafkaTopics.fromId("2").enName);
                    topicList.add(KafkaTopics.fromId("3").enName);
                    break;
                case 2:
                    break;
            }
        }
        return topicList;
    }

    public static List<String> getDataItemList(Integer mappingId) {

        List<String> dataItemList = new ArrayList<String>();
        if (mappingId != null) {
            switch (mappingId) {
                case 1:
                    dataItemList.add(DataItems.fromId("02").id);
                    break;
                case 2:
                    break;
            }
        }
        return dataItemList;
    }

}
