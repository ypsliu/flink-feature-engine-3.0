package com.jffox.cloud.constants;

/**
 * @author yanggang
 * @version 1.0
 * @date 2019-11-01
 * @describe 异常枚举
 * @since jdk 1.8
 */
public enum Exceptions {
    UNKNOWN_EXC(-1, "未知异常"),
    NO_EXC(0, "没有异常::"),
    PARAMS_EXC(1, "参数异常"),
    JSON_EXC(2, "json异常"),
    FLAT_MAP_EXC(3, "flatMap异常"),
    MAP_EXC(4, "map异常");
    public int id;
    public String name;

    /**
     * @param id
     * @param name
     */
    Exceptions(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * @param id
     * @return
     */
    public static Exceptions fromId(int id) {
        for (Exceptions error : Exceptions.values()) {
            if (error.id == id)
                return error;
        }
        return null;
    }

    /**
     * @param name
     * @return
     */
    public static Exceptions fromName(String name) {
        for (Exceptions error : Exceptions.values()) {
            if (error.name.equals(name))
                return error;
        }
        return null;
    }


}
