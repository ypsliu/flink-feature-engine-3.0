package com.jffox.cloud.examples;

import com.jffox.cloud.constants.Exceptions;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.shaded.akka.org.jboss.netty.channel.ExceptionEvent;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Slf4j
public class DstApiKey02 {
    public static void main(String[] args) throws Exception {
        //args配置
        final String hostname;
        final int port;
        try {
            final ParameterTool params = ParameterTool.fromArgs(args);
            hostname = params.has("hostname") ? params.get("hostname") : "localhost";
            port = params.getInt("port");
        } catch (Exception e) {
            log.error(Exceptions.fromId(1).name);
            return;
        }
        //env配置
        //StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironment();
        //kafka配置
        List<String> topicList = new ArrayList<>();
        DataStreamSource<String>  stream;
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "web01:9092,web02:9092,web04:9092");
        properties.setProperty("group.id", "flink-feature-engine-group.id");
        topicList.add("ygtest001");
        topicList.add("ygtest002");
        try {
            stream = env
                    .addSource(new FlinkKafkaConsumer<>(topicList, new SimpleStringSchema(), properties));
            stream.print();
            log.info(Exceptions.fromId(0).name);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(Exceptions.fromId(1).name);
        }
        env.execute("DstApiKey02");

    }
}
