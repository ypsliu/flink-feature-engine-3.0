package com.jffox.cloud.examples;

import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

@Slf4j
public class LambdaEg {
    public static void main(String[] args) {
        ExecutionEnvironment env = ExecutionEnvironment.createLocalEnvironment();
        try {
            DataSet<Integer> dataSet = env.fromElements(1, 2, 3);
            dataSet.map(data -> Tuple2.of(data, data))
                    .returns(Types.TUPLE(Types.INT,Types.INT))
                    .print();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
