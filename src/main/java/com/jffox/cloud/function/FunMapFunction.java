package com.jffox.cloud.function;

import com.alibaba.fastjson.JSONObject;
import com.jffox.cloud.constants.Exceptions;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.metrics.Counter;

@Slf4j
public class FunMapFunction extends RichMapFunction<String, String> {
    Counter counter;

    @Override
    public String map(String s) throws Exception {
        this.counter.inc();
        log.info("mapIn--{},mapInData--{}", counter.getCount(), s);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = FunToJson.toJson(s);
            jsonObject.put("testResult","testResult");
            log.info("mapOut--{},mapOutData--{}", counter.getCount(), jsonObject);
            return jsonObject.toJSONString();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("mapException--{},mapInData--{}", Exceptions.fromId(4).name, s);
            return null;
        }

    }

    @Override
    public void open(Configuration parameters) throws Exception {
        this.counter = getRuntimeContext()
                .getMetricGroup()
                .counter("FunMapFunctionCounter");
        super.open(parameters);
    }

}
