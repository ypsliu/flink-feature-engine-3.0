package com.jffox.cloud.function;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jffox.cloud.constants.Exceptions;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.functions.RichFlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.metrics.Counter;
import org.apache.flink.util.Collector;

/**
 * @author yanggang
 * @version 1.0
 * @date 2019-11-01
 * @describe flapMap算子继承类
 * @since jdk 1.8
 */
@Slf4j
public class FunFlatMapFunction extends RichFlatMapFunction<String, Tuple2<String, Integer>> {
    Counter counter;

    /**
     * @param s         输入字符串
     * @param collector 输出tuple2表格
     * @throws Exception
     */
    @Override
    public void flatMap(String s, Collector<Tuple2<String, Integer>> collector) throws Exception {
        this.counter.inc();
        log.info("flatMapIn--{},flanMapInData--{}", counter.getCount(), s);
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        Tuple2<String, Integer> tuple2 = new Tuple2<>();
        try {
            jsonObject = FunToJson.toJson(s);

            tuple2.setFields(jsonObject.toJSONString(), 1);
            collector.collect(tuple2);
            log.info("flatMapOut--{},flanMapOutData--{}", counter.getCount(), tuple2);

        } catch (Exception e) {
            e.printStackTrace();
            log.error("flatMapException--{},flanMapInData--{}", Exceptions.fromId(3).name, s);
        }

    }

    /**
     * @param parameters metrics配置
     * @throws Exception
     */
    @Override
    public void open(Configuration parameters) throws Exception {
        this.counter = getRuntimeContext()
                .getMetricGroup()
                .counter("FunFlatMapFunctionCounter");
        super.open(parameters);
    }
}
