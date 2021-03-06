package io.woolford;

import java.net.InetAddress;

public class PingRecord {

    private long timestamp;
    private Boolean isReachable;
    private InetAddress localInetAddress;
    private InetAddress remoteInetAddress;
    private Long responseMicroseconds;

    public Long getResponseMicroseconds() {
        return responseMicroseconds;
    }

    public void setResponseMicroseconds(Long responseMicroseconds) {
        this.responseMicroseconds = responseMicroseconds;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
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