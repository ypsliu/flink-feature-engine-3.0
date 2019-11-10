package com.jffox.cloud.function;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jffox.cloud.constants.Exceptions;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FunToJson {
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
