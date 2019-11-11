package com.jffox.cloud.function;

import com.alibaba.fastjson.JSONObject;
import com.jffox.cloud.constants.Exceptions;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.metrics.Counter;

/**
 * @author yanggang
 * @version 1.0
 * @date 2019-11-01
 * @describe mapTuple2算子继承类
 * @since jdk 1.8
 */
@Slf4j
public class FunMapFunctionTuple2 extends RichMapFunction<Tuple2<String, Integer>, String> {
    Counter counter;

    /**
     * @param tuple2 输入流为tuple2表格
     * @return 输出流为字符串
     * @throws Exception
     */
    @Override
    public String map(Tuple2<String, Integer> tuple2) throws Exception {
        this.counter.inc();
        log.info("mapTuple2In--{},mapTuple2InData--{}", counter.getCount(), tuple2);
        JSONObject jsonObject = new JSONObject();
        try {
            String s = tuple2.f0;
            jsonObject = FunToJson.toJson(s);
            jsonObject.put("testResult", "testResult");
            log.info("mapTuple2Out--{},mapTuple2OutData--{}", counter.getCount(), jsonObject);
            return jsonObject.toJSONString();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("mapException--{},mapTuple2InData--{}", Exceptions.fromId(4).name, tuple2);
            return null;
        }

    }

    /**
     * @param parameters 输入流为metrics配置
     * @throws Exception
     */
    @Override
    public void open(Configuration parameters) throws Exception {
        this.counter = getRuntimeContext()
                .getMetricGroup()
                .counter("FunMapFunctionTuple2Counter");
        super.open(parameters);
    }

}
