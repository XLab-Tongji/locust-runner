package com.tongji.service.impl;

import com.tongji.service.SchedulerService;

/**
 * @Author: zelin
 * @Date: 2019-06-02 10:44
 */
public class SchedulerServiceImpl implements SchedulerService {
    private Thread t;
    private Integer duration;

    SchedulerServiceImpl(Integer runtime) {
        duration = runtime;
    }

    public void run() {
        System.out.println("Running " +  duration + " minute(s)");
        try {
            Thread.sleep(60000 * duration);
            //do something to inform testing-scheduler

        }catch (InterruptedException e) {
            System.out.println("scheduler " +  duration + " interrupted.");
        }
        System.out.println("scheduler " +  duration + " minute(s) exiting.");
    }

    public void start () {
        System.out.println("Starting " +  duration + " minute(s) scheduler" );
        if (t == null) {
            t = new Thread (this);
            t.start ();
        }
    }
}
