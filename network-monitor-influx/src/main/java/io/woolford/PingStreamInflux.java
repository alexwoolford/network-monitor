package io.woolford;


import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer09;
import org.apache.flink.streaming.util.serialization.SimpleStringSchema;


import java.util.Properties;

public class PingStreamInflux {


    public static void main(String[] args) throws Exception {

        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        Properties properties = new Properties();

        properties.setProperty("bootstrap.servers", "localhost:9092");
        properties.setProperty("zookeeper.connect", "localhost:2181");
        properties.setProperty("group.id", "flinkPingStream");

        DataStream<String> messageStream = env.addSource(
                new FlinkKafkaConsumer09<>(
                    "ping",
                    new SimpleStringSchema(),
                    properties
        ));

        messageStream.print();

        env.execute();
    }

}
