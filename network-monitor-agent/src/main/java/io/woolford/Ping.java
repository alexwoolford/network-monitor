package io.woolford;


import com.google.common.base.Stopwatch;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.reflect.ReflectData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static javafx.scene.input.KeyCode.T;

@Component
public class Ping {

    static Logger logger = Logger.getLogger(Ping.class.getName());

    @Autowired
    private KafkaTemplate<Integer, String> template;

    @Scheduled(fixedRate = 1000L)
    private void pingTest(){

        InetAddress inetAddress = null;
        try {
            inetAddress = InetAddress.getByName("hadoop01");
            pingTestAsync(inetAddress);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }

    @Async
    private void pingTestAsync(InetAddress remoteInetAddress){

        try {
            PingRecord pingRecord = new PingRecord();
            Boolean isReachable = null;
            Stopwatch pingTimer = Stopwatch.createStarted();
            isReachable = remoteInetAddress.isReachable(1000);
            pingTimer.stop();

            InetAddress localInetAddress = InetAddress.getLocalHost();

            Schema pingRecordSchema = ReflectData.get().getSchema(pingRecord.getClass());
            GenericRecord pingRecordAvro = new GenericData.Record(pingRecordSchema);

            pingRecordAvro.put("timestamp", new Date());
            pingRecordAvro.put("isReachable", isReachable);
            pingRecordAvro.put("localInetAddress", localInetAddress);
            pingRecordAvro.put("remoteInetAddress", remoteInetAddress);
            pingRecordAvro.put("responseMicroseconds", pingTimer.elapsed(TimeUnit.MICROSECONDS));

            // TODO: serialize as Avro
            template.send("ping", pingRecordAvro.toString());

            logger.info(pingRecordAvro.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
