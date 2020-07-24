package com.example.demo.concurrent.sync;

import org.junit.Test;

public class SynchronizedDemo2 {

    int j=0;
    public static void main(String[] args)throws InterruptedException{

        SynchronizedDemo2 demo2=new SynchronizedDemo2();
        for(int i=0;i<10000;i++){
            new Thread(()->{
                System.out.println(demo2.increaseAndGet());
            }).start();
        }
        Thread.sleep(3000);
    }
    public int increaseAndGet(){
        return j++;
    }
    int m=0;
    @Test
    public void test()throws InterruptedException{
        for(int i=0;i<10000;i++){
            new Thread(()->{
                m++;
                m--;
            }).start();
        }
        Thread.sleep(3000);
        System.out.println(m);
    }
}
