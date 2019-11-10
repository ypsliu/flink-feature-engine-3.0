package com.jffox.cloud.targets;

import com.jffox.cloud.constants.KafkaTopics;

public class TargetMapping {
    public static String getTargetTopic(String id){
        return KafkaTopics.fromId(id).enName;
    }
}
