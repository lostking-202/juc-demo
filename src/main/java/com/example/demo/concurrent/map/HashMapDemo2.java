package com.example.demo.concurrent.map;

import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * todo 怎样写一个demo证明HashMap并发put时会元素丢失？
 */
public class HashMapDemo2 {

    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch latch=new CountDownLatch(10);
        HashMap<Integer,Integer> map=new HashMap<>();
        AtomicInteger ai=new AtomicInteger(0);
        while(ai.get()<100000){
            for(int i=0;i<10;i++)
            new Thread(()->{
                map.put(ai.get(),ai.get());
                ai.incrementAndGet();
                latch.countDown();
            }).start();
        }
        latch.await();
        System.out.println(map.size());
    }
}
