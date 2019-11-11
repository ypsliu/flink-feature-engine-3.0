package com.jffox.cloud.targets;

import com.jffox.cloud.constants.KafkaTopics;

/**
 * @author yanggang
 * @version 1.0
 * @date 2019-11-02
 * @describe 特征目标结果输出topic
 * @since jdk 1.8
 */
public class TargetMapping {
    /**
     * @param id 特征结果输出id
     * @return 返回特征计算topic
     */
    public static String getTargetTopic(String id) {
        return KafkaTopics.fromId(id).enName;
    }
}
