package io.woolford;

import com.google.gson.Gson;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer09;
import org.apache.flink.streaming.util.serialization.SimpleStringSchema;
import java.util.Properties;
import java.util.logging.Logger;

public class PingStreamInflux {

    static Logger logger = Logger.getLogger(PingStreamInflux.class.getName());

    public static void main(String[] args) throws Exception {

        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        Properties properties = new Properties();

        properties.setProperty("bootstrap.servers", "localhost:9092");
        properties.setProperty("zookeeper.connect", "localhost:2181");
        properties.setProperty("group.id", "flinkPingStream");

        DataStream<String> dataStream = env.addSource(
                new FlinkKafkaConsumer09<>(
                    "ping",
                    new SimpleStringSchema(),
                    properties
        ));

        dataStream.map(new MapFunction<String, PingRecord>() {
            @Override
            public PingRecord map(String pingRecordJsonString) {
                Gson gson = new Gson();
                PingRecord pingRecord = gson.fromJson(pingRecordJsonString, PingRecord.class);
                return pingRecord;
            }
        }).print();

        env.execute();
    }

}
