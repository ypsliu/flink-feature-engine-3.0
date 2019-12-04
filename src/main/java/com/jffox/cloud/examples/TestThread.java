package com.jffox.cloud.examples;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestThread extends Thread {
    int i;
    Thread previousThread;

    public TestThread(int i, Thread previousThread) {
        this.i = i;
        this.previousThread = previousThread;

    }

    @Override
    public void run() {
        super.run();
        try {
            previousThread.join();
            log.info("i--{},previousThread--{}", i, previousThread);
        } catch (Exception e) {
            log.error("getStackTrace", e.getStackTrace());
        }
    }
}
