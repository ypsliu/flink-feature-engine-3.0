package com.jffox.cloud.sources;

import com.jffox.cloud.configs.KafkaConsumerConfig;
import com.jffox.cloud.constants.DataItems;
import com.jffox.cloud.constants.KafkaTopics;
import com.jffox.cloud.constants.KafkaUrls;
import org.apache.flink.configuration.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SourceMapping {

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
