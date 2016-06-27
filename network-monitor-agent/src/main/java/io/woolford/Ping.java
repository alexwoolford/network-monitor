package io.woolford;


import com.google.common.base.Stopwatch;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@Component
public class Ping {

    static Logger logger = Logger.getLogger(Ping.class.getName());

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

            InetAddress locateInetAddress = InetAddress.getLocalHost();

            pingRecord.setTimestamp(new Date());
            pingRecord.setReachable(isReachable);
            pingRecord.setLocalInetAddress(locateInetAddress);
            pingRecord.setRemoteInetAddress(remoteInetAddress);
            pingRecord.setResponseMicroseconds(pingTimer.elapsed(TimeUnit.MICROSECONDS));

            PingRecordAvro pingRecordAvro = getPingRecordAvro(pingRecord);

            logger.info(pingRecordAvro.toString());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private PingRecordAvro getPingRecordAvro(PingRecord pingRecord){

        PingRecordAvro pingRecordAvro = new PingRecordAvro();

        pingRecordAvro.setTimestamp(pingRecord.getTimestamp().getTime());
        pingRecordAvro.setIsReachable(pingRecord.getReachable());
        pingRecordAvro.setLocalInetAddress(pingRecord.getLocalInetAddress().toString());
        pingRecordAvro.setRemoteInetAddress(pingRecord.getRemoteInetAddress().toString());
        pingRecordAvro.setResponseMicroseconds(pingRecord.getResponseMicroseconds());

        return pingRecordAvro;
    }

    

}
