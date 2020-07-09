package com.ssstm.virtualclock;

public class VirtualClock {
    private final long seq;
    private final long pid;
    private final String username;
    private final String hostname;
    private final String timestamp;

    public VirtualClock(long seq, long pid, String username, String hostname, String timestamp) {
        this.seq = seq;
        this.pid = pid;
        this.username = username;
        this.hostname = hostname;
        this.timestamp = timestamp;
    }

    public long getSeq() {
        return seq;
    }

    public long getPid() {
        return pid;
    }

    public String getUsername() {
        return username;
    }

    public String getHostname() {
        return hostname;
    }

    public String getTimestamp() {
        return timestamp;
    }

}