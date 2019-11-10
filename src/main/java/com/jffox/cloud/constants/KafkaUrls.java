package com.jffox.cloud.constants;

/**
 * Create by 杨刚 on 2019/09/29 10:18
 */
public enum KafkaUrls {
    UNKNOWN("-1", "未知","t_unknown"),
    KAFKA_VPC_TEST("1", "数据湖阿里vpc测试环境kafka","web01:9092,web02:9092,web04:9092");
    public String id;
    public String name;
    public String enName;
    KafkaUrls(String id, String name, String enName) {
        this.id = id;
        this.name = name;
        this.enName=enName;
    }

    public static KafkaUrls fromId(String id) {
        for (KafkaUrls error : KafkaUrls.values()) {
            if (error.id == id)
                return error;
        }
        return null;
    }

    public static KafkaUrls fromName(String name) {
        for (KafkaUrls error : KafkaUrls.values()) {
            if (error.name.equals(name))
                return error;
        }
        return null;
    }
    public static KafkaUrls fromEnName(String enName) {
        for (KafkaUrls error : KafkaUrls.values()) {
            if (error.enName.equals(enName))
                return error;
        }
        return null;
    }

}
