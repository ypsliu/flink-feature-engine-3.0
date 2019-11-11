package com.jffox.cloud.constants;

/**
 * @author yanggang
 * @version 1.0
 * @date 2019-11-01
 * @describe kafka配置信息枚举
 * @since jdk 1.8
 */
public enum KafkaUrls {
    UNKNOWN("-1", "未知", "t_unknown"),
    KAFKA_VPC_TEST("1", "数据湖阿里vpc测试环境kafka", "web01:9092,web02:9092,web04:9092");
    public String id;
    public String name;
    public String enName;

    /**
     * @param id
     * @param name
     * @param enName
     */
    KafkaUrls(String id, String name, String enName) {
        this.id = id;
        this.name = name;
        this.enName = enName;
    }

    /**
     * @param id
     * @return
     */
    public static KafkaUrls fromId(String id) {
        for (KafkaUrls error : KafkaUrls.values()) {
            if (error.id == id)
                return error;
        }
        return null;
    }

    /**
     * @param name
     * @return
     */
    public static KafkaUrls fromName(String name) {
        for (KafkaUrls error : KafkaUrls.values()) {
            if (error.name.equals(name))
                return error;
        }
        return null;
    }

    /**
     * @param enName
     * @return
     */
    public static KafkaUrls fromEnName(String enName) {
        for (KafkaUrls error : KafkaUrls.values()) {
            if (error.enName.equals(enName))
                return error;
        }
        return null;
    }

}
