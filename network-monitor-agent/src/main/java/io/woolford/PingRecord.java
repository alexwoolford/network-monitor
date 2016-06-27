package io.woolford;

import java.net.InetAddress;
import java.util.Date;

public class PingRecord {

    private Date timestamp;
    private Boolean isReachable;
    private InetAddress localInetAddress;
    private InetAddress remoteInetAddress;
    private Long responseMicroseconds;

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Boolean getReachable() {
        return isReachable;
    }

    public void setReachable(Boolean reachable) {
        isReachable = reachable;
    }

    public InetAddress getLocalInetAddress() {
        return localInetAddress;
    }

    public void setLocalInetAddress(InetAddress localInetAddress) {
        this.localInetAddress = localInetAddress;
    }

    public InetAddress getRemoteInetAddress() {
        return remoteInetAddress;
    }

    public void setRemoteInetAddress(InetAddress remoteInetAddress) {
        this.remoteInetAddress = remoteInetAddress;
    }

    public Long getResponseMicroseconds() {
        return responseMicroseconds;
    }

    public void setResponseMicroseconds(Long responseMicroseconds) {
        this.responseMicroseconds = responseMicroseconds;
    }

    @Override
    public String toString() {
        return "PingRecord{" +
                "timestamp=" + timestamp +
                ", isReachable=" + isReachable +
                ", localInetAddress=" + localInetAddress +
                ", remoteInetAddress=" + remoteInetAddress +
                ", responseMicroseconds=" + responseMicroseconds +
                '}';
    }

}
