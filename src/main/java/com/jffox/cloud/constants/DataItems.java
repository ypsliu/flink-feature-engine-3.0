package com.jffox.cloud.constants;

/**
 * @author yanggang
 * @version 1.0
 * @date 2019-11-01
 * @describe 数据项枚举
 * @since jdk 1.8
 */
public enum DataItems {
    UNKNOWN("-1", "未知", "t_unknown"),
    CR_OPERATOR_MOBILE("02", "运营商", "cr_operator_mobile_v3");
    public String id;
    public String name;
    public String enName;

    /**
     * @param id
     * @param name
     * @param enName
     */
    DataItems(String id, String name, String enName) {
        this.id = id;
        this.name = name;
        this.enName = enName;
    }

    /**
     * @param id
     * @return
     */
    public static DataItems fromId(String id) {
        for (DataItems error : DataItems.values()) {
            if (error.id == id)
                return error;
        }
        return null;
    }

    /**
     * @param name
     * @return
     */
    public static DataItems fromName(String name) {
        for (DataItems error : DataItems.values()) {
            if (error.name.equals(name))
                return error;
        }
        return null;
    }

    /**
     * @param enName
     * @return
     */
    public static DataItems fromEnName(String enName) {
        for (DataItems error : DataItems.values()) {
            if (error.enName.equals(enName))
                return error;
        }
        return null;
    }

}
