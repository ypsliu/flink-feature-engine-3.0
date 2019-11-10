package com.jffox.cloud.examples.developmentmode;

import org.apache.flink.api.java.tuple.Tuple2;

public class DstLamdaTestTuple2 extends Tuple2<Integer,Integer> {
    public DstLamdaTestTuple2(int f0,int f1) {
        this.f0 = f0;
        this.f1 = f1;
    }
}
