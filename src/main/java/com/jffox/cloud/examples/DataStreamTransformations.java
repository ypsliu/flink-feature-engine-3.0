package com.jffox.cloud.examples;

import org.apache.flink.api.common.functions.*;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.WindowedStream;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

public class DataStreamTransformations {
    /*Takes one element and produces one element
    A map function that doubles the values of the input stream:*/
    public static void map(DataStream<Integer> input) {
        DataStream<Integer> dataStream = input;
        dataStream.map(new MapFunction<Integer, Integer>() {
            @Override
            public Integer map(Integer value) throws Exception {
                return 2 * value;
            }
        }).print();
    }

    /*Takes one element and produces zero, one, or more elements.
    A flatmap function that splits sentences to words:*/
    public static void flatMap(DataStream<String> dataStream) {
        dataStream.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public void flatMap(String value, Collector<String> out)
                    throws Exception {
                for (String word : value.split(" ")) {
                    out.collect(word);
                }
            }
        }).print();
    }

    /*Evaluates a boolean function for each element and retains
    those for which the function returns true.
    A filter that filters out zero values:*/
    public static void filter(DataStream<Integer> dataStream) {
        dataStream.filter(new FilterFunction<Integer>() {
            @Override
            public boolean filter(Integer value) throws Exception {
                return value != 0;
            }
        }).print();
    }

    /*Logically partitions a stream into disjoint partitions.
     All records with the same key are assigned to the same partition.
     Internally, keyBy() is implemented with hash partitioning.
     There are different ways to specify keys.
     This transformation returns a KeyedStream, which is, among other things, required to use keyed state.*/
    public static void keyBy(DataStream<Tuple2<String, String>> dataStream, String word) {
        dataStream.keyBy(word);
    }

    /*A "rolling" reduce on a keyed data stream.
    Combines the current element with the last reduced value and emits the new value.
    A reduce function that creates a stream of partial sums:*/
    public static void reduce(KeyedStream<Integer, Integer> keyedStream) {
        keyedStream.reduce(new ReduceFunction<Integer>() {
            @Override
            public Integer reduce(Integer value1, Integer value2)
                    throws Exception {
                return value1 + value2;
            }
        });

    }

    /*A "rolling" fold on a keyed data stream with an initial value.
    Combines the current element with the last folded value and emits the new value.
    A fold function that, when applied on the sequence (1,2,3,4,5), emits the sequence "start-1", "start-1-2", "start-1-2-3", ...*/
    public static void fold(KeyedStream<Integer, String> keyedStream) {
        DataStream<String> result =
                keyedStream.fold("start", new FoldFunction<Integer, String>() {
                    @Override
                    public String fold(String current, Integer value) {
                        return current + "-" + value;
                    }
                });
    }

    /*Rolling aggregations on a keyed data stream.
    The difference between min and minBy is that min returns the minimum value,
    whereas minBy returns the element that has the minimum value in this field (same for max and maxBy).*/
    public static void maxMinSum(KeyedStream<Integer, Integer> keyedStream, String key) {
        keyedStream.sum(key);
        keyedStream.min(key);
        keyedStream.max(key);
        keyedStream.minBy(key);
        keyedStream.maxBy(key);
    }

    /*Windows can be defined on already partitioned KeyedStreams.
    Windows group the data in each key according to some characteristic
    (e.g., the data that arrived within the last 5 seconds).
    See windows for a complete description of windows.*/
    public static void window(DataStream dataStream, String key, Integer s) {
        dataStream.keyBy(key).window(TumblingEventTimeWindows.of(Time.seconds(s))); // Last 5 seconds of data
    }

    public static void windowAll(DataStream dataStream) {
        dataStream.windowAll(TumblingEventTimeWindows.of(Time.seconds(5))); // Last 5 seconds of data

    }

    /*public static void applyall(AllWindowedStream allWindowedStream) {
        allWindowedStream.apply(new AllWindowFunction<Tuple2<String, Integer>, Integer, Window>() {
            public void apply(Window window,
                              Iterable<Tuple2<String, Integer>> values,
                              Collector<Integer> out) throws Exception {
                int sum = 0;
                for (value t : values) {
                    sum += t.f1;
                }
                out.collect(new Integer(sum));
            }
        });
    }*/
    /*public static void apply(WindowedStream windowedStream){
        windowedStream.apply (new WindowFunction<Tuple2<String,Integer>, Integer, Tuple, Window>() {
            public void apply (Tuple tuple,
                               Window window,
                               Iterable<Tuple2<String, Integer>> values,
                               Collector<Integer> out) throws Exception {
                int sum = 0;
                for (value t: values) {
                    sum += t.f1;
                }
                out.collect (new Integer(sum));
            }
        });
    }*/
    public static void reduceWin(WindowedStream windowedStream) {
        windowedStream.reduce(new ReduceFunction<Tuple2<String, Integer>>() {
            public Tuple2<String, Integer> reduce(Tuple2<String, Integer> value1, Tuple2<String, Integer> value2) throws Exception {
                return new Tuple2<String, Integer>(value1.f0, value1.f1 + value2.f1);
            }
        });
    }

    public static void foldWin(WindowedStream windowedStream) {
        windowedStream.fold("start", new FoldFunction<Integer, String>() {
            public String fold(String current, Integer value) {
                return current + "-" + value;
            }
        });
    }

    public static void sumMinMax(WindowedStream windowedStream) {
        windowedStream.sum(0);
        windowedStream.sum("key");
        windowedStream.min(0);
        windowedStream.min("key");
        windowedStream.max(0);
        windowedStream.max("key");
        windowedStream.minBy(0);
        windowedStream.minBy("key");
        windowedStream.maxBy(0);
        windowedStream.maxBy("key");
    }

    public static void union(DataStream dataStream, DataStream otherStream1, DataStream otherStream2) {
        dataStream.union(otherStream1, otherStream2);

    }

    /*public static void  join(DataStream dataStream,DataStream otherStream){
        dataStream.join(otherStream)
                .where(<key selector>).equalTo(<key selector>)
    .window(TumblingEventTimeWindows.of(Time.seconds(3)))
                .apply (new JoinFunction () {...});
    }*/
    /*public static void() {
        // this will join the two streams so that
        // key1 == key2 && leftTs - 2 < rightTs < leftTs + 2
        keyedStream.intervalJoin(otherKeyedStream)
                .between(Time.milliseconds(-2), Time.milliseconds(2)) // lower and upper bound
                .upperBoundExclusive(true) // optional
                .lowerBoundExclusive(true) // optional
                .process(new IntervalJoinFunction() {...
                });
    }*/
    /*public static void coGroup(DataStream dataStream, DataStream otherStream) {
        dataStream.coGroup(otherStream)
                .where(0).equalTo(1)
                .window(TumblingEventTimeWindows.of(Time.seconds(3)))
                .apply(new CoGroupFunction() {...
                });
    }*/
}
