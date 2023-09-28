package com.example.demo.concurrent.map;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class HashMapDemo3 {
    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch latch=new CountDownLatch(10);
        ConcurrentHashMap<Integer,Integer> map=new ConcurrentHashMap<>();
        AtomicInteger ai=new AtomicInteger(0);
        while(ai.get()<100000){
            for(int i=0;i<10;i++)
                new Thread(()->{
                    map.putIfAbsent(ai.get(),ai.get());
                    ai.incrementAndGet();
                    latch.countDown();
                }).start();
        }
        latch.await();
        System.out.println(map.size());
    }
}
