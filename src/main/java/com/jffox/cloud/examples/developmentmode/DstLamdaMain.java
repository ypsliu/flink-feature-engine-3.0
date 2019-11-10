package com.jffox.cloud.examples.developmentmode;

import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.tuple.Tuple2;

@Slf4j
public class DstLamdaMain {
    public static void main(String[] args) {
        ExecutionEnvironment env = ExecutionEnvironment.createLocalEnvironment();
        try {
            DataSet<String> dataSet = env.fromElements("1024!", "flink hello world");
            /**The generic type parameters of 'Tuple2' are missing
             * 解决这个问题有四个方式也是我们平常开发模式要用到的
             * 1、使用returns指定返回数据格式
             * 2、使用实现MapFunction这个接口
             * 3、使用匿名inter class实现
             * 4、使用tuple function实现
             * @date 2019-11-25
             * @autor yangGang
             * */
           /* dataSet.map(s -> Tuple2.of(s, s))
                    .print();*/
           /*dataSet.map(s->Tuple2.of(s,s))
                   .returns(Types.TUPLE(Types.INT, Types.INT))
                   .print();*/
            dataSet.map(new DstLamdaMap())
                    .print();
/*
           dataSet.map(new DstRichMapFunction()).print();
*/
           /* dataSet.map(new MapFunction<Integer, Tuple2<Integer, Integer>>() {
                @Override
                public Tuple2<Integer, Integer> map(Integer integer) throws Exception {
                    Tuple2<Integer, Integer> tuple2 = new Tuple2<>();

                    try {
                        tuple2.setFields(integer, integer);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return tuple2;
                }
            }).print();*/
            /*dataSet.map(s -> new DstLamdaTuple2(s, s)).print();*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
