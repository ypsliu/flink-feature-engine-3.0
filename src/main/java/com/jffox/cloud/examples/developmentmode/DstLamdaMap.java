package com.jffox.cloud.examples.developmentmode;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;

public class DstLamdaMap implements MapFunction<String, Tuple2<String, String>> {
    @Override
    public Tuple2<String, String> map(String s) throws Exception {
        Tuple2<String, String> tuple2 = new Tuple2<>();
        try {
            tuple2.setFields(s, s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tuple2;
    }
}
