package com.jffox.cloud.operations.threedata.mobileoperation;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jffox.cloud.constants.Exceptions;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DstComputer {
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
