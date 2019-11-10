package com.jffox.cloud.examples.developmentmode;

import org.apache.flink.api.java.tuple.Tuple2;

public  class DstLamdaTuple2 extends Tuple2<Integer, Integer> {
    public DstLamdaTuple2(Integer f0, Integer f1) {
        this.f0 = f0;
        this.f1 = f1;
    }
}