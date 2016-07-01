package io.woolford;

import java.net.InetAddress;

public class PingRecord {

    private long timestamp;
    private Boolean reachable;
    private InetAddress localInetAddress;
    private InetAddress remoteInetAddress;
    private Long responseMicroseconds;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Boolean getReachable() {
        return reachable;
    }

    public void setReachable(Boolean reachable) {
        reachable = reachable;
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
                ", reachable=" + reachable +
                ", localInetAddress=" + localInetAddress +
                ", remoteInetAddress=" + remoteInetAddress +
                ", responseMicroseconds=" + responseMicroseconds +
                '}';
    }

}