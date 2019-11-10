package com.jffox.cloud.examples.developmentmode;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;

public class DstLamdaTestMap implements MapFunction<Integer, Tuple2<Integer, Integer>> {
    @Override
    public Tuple2<Integer, Integer> map(Integer integer) throws Exception {
        Tuple2<Integer, Integer> tuple2 = new Tuple2<>();
        tuple2.setFields(integer, integer);
        return tuple2;
    }
}
