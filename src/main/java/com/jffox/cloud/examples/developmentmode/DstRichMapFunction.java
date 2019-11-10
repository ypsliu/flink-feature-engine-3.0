package com.jffox.cloud.examples.developmentmode;

import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.metrics.Counter;
@Slf4j
public class DstRichMapFunction extends RichMapFunction<String, String> {
    Counter counter;

    @Override
    public String map(String s) throws Exception {
        this.counter.inc();
        log.info("s--{},counter--{}",s,counter.getCount());
        return s;
    }

    @Override
    public void open(Configuration parameters) throws Exception {
        this.counter = getRuntimeContext()
                .getMetricGroup()
                .counter("myCounter");

        super.open(parameters);
    }
}
