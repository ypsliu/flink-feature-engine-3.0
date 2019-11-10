package com.jffox.cloud.examples.developmentmode;

import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.tuple.Tuple2;

@Slf4j
public class DstLamdaTest {
    public static void main(String[] args) {
        ExecutionEnvironment env = ExecutionEnvironment.createLocalEnvironment();
        DataSet<Integer> dataSet = env.fromElements(1, 2, 3);
        try {
            dataSet.map(integer -> new DstLamdaTestTuple2(integer,integer))
                    .print();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
