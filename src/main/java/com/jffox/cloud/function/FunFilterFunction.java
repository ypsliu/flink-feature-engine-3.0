package com.jffox.cloud.function;

import com.alibaba.fastjson.JSONObject;
import com.jffox.cloud.constants.Exceptions;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.functions.RichFilterFunction;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.metrics.Counter;

@Slf4j
public class FunFilterFunction extends RichFilterFunction<String> {
    Counter counter;

    @Override
    public boolean filter(String s) throws Exception {
        this.counter.inc();
        log.info("filterIn--{},filterInData--{}", counter.getCount(), s);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = FunToJson.toJson(s);
            if (/*jsonObject.getString("apiKey").equals(DataItems.fromId("02").id)*/
                    1 == 1) {
                log.info("filterOut--{}", counter.getCount());
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("filterException--{},filterInData--{}", Exceptions.fromId(2).name, s);
            return false;
        }
    }

    @Override
    public void open(Configuration parameters) throws Exception {
        this.counter = getRuntimeContext()
                .getMetricGroup()
                .counter("FunFilterFunctionCounter");
        super.open(parameters);
    }
}
