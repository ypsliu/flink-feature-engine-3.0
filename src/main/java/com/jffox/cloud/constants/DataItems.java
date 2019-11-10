package com.jffox.cloud.constants;

/**
 * Create by 杨刚 on 2019/09/29 10:18
 */
public enum DataItems {
    UNKNOWN("-1", "未知","t_unknown"),
    CR_OPERATOR_MOBILE("02", "运营商","cr_operator_mobile_v3");
    public String id;
    public String name;
    public String enName;
    DataItems(String id, String name, String enName) {
        this.id = id;
        this.name = name;
        this.enName=enName;
    }

    public static DataItems fromId(String id) {
        for (DataItems error : DataItems.values()) {
            if (error.id == id)
                return error;
        }
        return null;
    }

    public static DataItems fromName(String name) {
        for (DataItems error : DataItems.values()) {
            if (error.name.equals(name))
                return error;
        }
        return null;
    }
    public static DataItems fromEnName(String enName) {
        for (DataItems error : DataItems.values()) {
            if (error.enName.equals(enName))
                return error;
        }
        return null;
    }

}
