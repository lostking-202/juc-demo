package com.example.demo.concurrent.sync;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SynchronizedDemo1 {
    public static void main(String[] args)throws InterruptedException {
        UnsafeCounter uc = new UnsafeCounter();
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 设置 CountDownLatch 的计数器为 100，保证在主线程打印累加结果之前，100 个线程已经执行完累加
        CountDownLatch cdl = new CountDownLatch(100);
        for(int i = 0; i < 100; i++) {
            executorService.execute(() -> {
                /*synchronized (SynchronizedDemo1.class){
                    uc.add();
                }*/
                uc.add();
                // 每一个线程执行完累加操作，都将计数器减 1
                cdl.countDown();
            });
        }
        // 主线程等待，直到 cdl 的计数器为0
        cdl.await();
        System.out.println("计数器执行完100次累加后值为：" + uc.get());
    }
}
class UnsafeCounter {
    public static int count = 0;
    public void add() {
        count++;
    }
    public int get() {
        return count;
    }
}
