package com.jffox.cloud.constants;

/**
 * Create by 杨刚 on 2019/09/29 10:18
 */
public enum KafkaTopics {
    UNKNOWN("-1", "未知数据","t_unknown"),
    REALTIME_CREDIT_PARTONE("1", "授权数据主题1","realtime_credit_partone"),
    REALTIME_CREDIT_PARTTWO("2", "授权数据主题2","realtime_credit_parttwo"),
    REALTIME_CREDIT_PARTTHREE("3", "授权数据主题3","realtime_credit_partthree"),
    TARGET_TOPIC1("4", "计算结果目标topic","test");


    public String id;
    public String name;
    public String enName;
    KafkaTopics(String id, String name, String enName) {
        this.id = id;
        this.name = name;
        this.enName=enName;
    }

    public static KafkaTopics fromId(String id) {
        for (KafkaTopics error : KafkaTopics.values()) {
            if (error.id == id)
                return error;
        }
        return null;
    }

    public static KafkaTopics fromName(String name) {
        for (KafkaTopics error : KafkaTopics.values()) {
            if (error.name.equals(name))
                return error;
        }
        return null;
    }
    public static KafkaTopics fromEnName(String enName) {
        for (KafkaTopics error : KafkaTopics.values()) {
            if (error.enName.equals(enName))
                return error;
        }
        return null;
    }

}
