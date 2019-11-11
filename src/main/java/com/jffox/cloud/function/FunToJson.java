package com.jffox.cloud.function;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jffox.cloud.constants.Exceptions;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yanggang
 * @version 1.0
 * @date 2019-11-01
 * @describe 字符转json类
 * @since jdk 1.8
 */
@Slf4j
public class FunToJson {
    /**
     * @param s 入参为字符串
     * @return 出参为json对象
     * @throws Exception
     */
    public static JSONObject toJson(String s) throws Exception {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = JSON.parseObject(s);
        } catch (Exception e) {
            log.error(Exceptions.fromId(2).name);
        }
        return jsonObject;
    }
}
