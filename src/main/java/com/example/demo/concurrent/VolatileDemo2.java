package com.example.demo.concurrent;

//volatile只支持原子性操作，如果操作非原子性，还是无法正确同步
public class VolatileDemo2 {
    public static volatile int i=0;

    public static void main(String[] args)throws InterruptedException{
        for(int i=0;i<1000;i++){
            new Thread(() -> {
                Thread.yield();
                VolatileDemo2.i++;
            }).start();
        }
        Thread.sleep(3000);
        System.out.println(VolatileDemo2.i);
    }
}
