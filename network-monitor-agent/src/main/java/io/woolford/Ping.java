package io.woolford;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.common.base.Stopwatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

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
            Boolean reachable = null;
            Stopwatch pingTimer = Stopwatch.createStarted();
            reachable = remoteInetAddress.isReachable(1000);
            pingTimer.stop();

            InetAddress localInetAddress = InetAddress.getLocalHost();

            long timestamp = System.nanoTime();

            PingRecord pingRecord = new PingRecord();
            pingRecord.setTimestamp(timestamp);
            pingRecord.setReachable(reachable);
            pingRecord.setLocalInetAddress(localInetAddress);
            pingRecord.setRemoteInetAddress(remoteInetAddress);
            pingRecord.setResponseMicroseconds(pingTimer.elapsed(TimeUnit.MICROSECONDS));

            ObjectWriter objectWriter = new ObjectMapper().writer();
            String pingRecordJson = objectWriter.writeValueAsString(pingRecord);

            template.send("ping", pingRecordJson);

            logger.info(pingRecord.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
