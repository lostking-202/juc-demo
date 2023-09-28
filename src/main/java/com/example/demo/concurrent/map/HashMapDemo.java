package com.example.demo.concurrent.map;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * 重现HashMap的并发问题
 *
 * https://www.cnblogs.com/binyue/p/3726403.html
 */
public class HashMapDemo{
    public static void main(String[] args) {
        MapThread t0 = new MapThread();
        MapThread t1 = new MapThread();
        MapThread t2 = new MapThread();
        MapThread t3 = new MapThread();
        MapThread t4 = new MapThread();
        MapThread t5 = new MapThread();
        MapThread t6 = new MapThread();
        MapThread t7 = new MapThread();
        MapThread t8 = new MapThread();
        MapThread t9 = new MapThread();

        t0.start();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        t9.start();

    }
}
class MapThread extends Thread{
    private static AtomicInteger ai=new AtomicInteger(0);
    private static HashMap<Integer,Integer> map=new HashMap<>();

    public void run(){
        while(ai.get()<1000000){
            map.put(ai.get(),ai.get());
            ai.incrementAndGet();
        }
        System.out.println(Thread.currentThread().getName()+"线程即将结束");
    }
}
