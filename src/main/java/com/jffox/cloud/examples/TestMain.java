package com.jffox.cloud.examples;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestMain {
    public void get(int... a) {
        for (int ai : a) {
            log.info("ai--{},className--{}", ai, super.getClass().getName());
        }
        Thread thread=new Thread();
        Thread thread1=new Thread();
        //thread.join(10000);
    }

    public static void main(String[] args) {
        //new TestMain().get(1, 2, 3);

        Thread previousThread=Thread.currentThread();
        for(int i=0;i<3;i++){
            TestThread testThread=new TestThread(i,previousThread);
            testThread.start();
            previousThread=testThread;
        }
    }
}
