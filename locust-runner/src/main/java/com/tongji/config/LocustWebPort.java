package com.tongji.config;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : rain
 * @date : 2018/11/13 10:38 AM
 */
public class LocustWebPort {
    private static AtomicInteger port = new AtomicInteger(18089);

    public static int getLocustWebPort() {
        return port.getAndIncrement();
    }

    public static int getPortValue() {
        return port.intValue();
    }
}
