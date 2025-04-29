package com.ssstm.virtualclock;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.Locale;
import java.net.InetAddress;
import java.net.UnknownHostException;

import java.lang.management.ManagementFactory;

@RestController
public class VirtualClockController {

    private final AtomicLong counter = new AtomicLong();
    private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z", Locale.getDefault());
    private final String username = System.getProperty("user.name");
    private final String hostname = fetchHostname();
    private final long pid = fetchPid();

    @GetMapping("/tmdemo/gettime")
    @ResponseBody
    public VirtualClock getCurrentTime() {
        return new VirtualClock(counter.incrementAndGet(), pid, username, hostname,
				dateFormat.format(new Date()));
    }

    private String fetchHostname() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            return "unknown";
        }
    }

    private long fetchPid() {
        // This piece code is copied from
        // https://shekhargulati.com/2015/11/16/how-to-programmatically-get-process-id-of-a-java-process/

        String vmName = ManagementFactory.getRuntimeMXBean().getName();
        int p = vmName.indexOf("@");
        String pidStr = vmName.substring(0, p);

        return Long.parseLong(pidStr);
    }
}
