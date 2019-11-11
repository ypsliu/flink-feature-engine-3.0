package com.jffox.cloud.operations.threedata.mobileoperation;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jffox.cloud.constants.Exceptions;
import lombok.extern.slf4j.Slf4j;

@Slf4j
/**
 * @autor yanggang
 * @date 2019-11-01
 * @version 1.0
 * @since jdk 1.8
 * @describe 业务逻辑计算类
 */
public class DstComputer {
    /**
     * @param s 入参数据字符串类型
     * @return 返回null
     * @describe 业务逻辑计算方法
     */
    public static JSONObject com(String s) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = JSON.parseObject(s).getJSONObject("data");
            jsonObject.put("certId", JSON.parseObject(s).getString("certId"));
            log.info("mapEnd:::" + jsonObject);
            return jsonObject;
        } catch (Exception e) {
            log.error(Exceptions.fromId(4).name);
            return null;
        }
    }
}
