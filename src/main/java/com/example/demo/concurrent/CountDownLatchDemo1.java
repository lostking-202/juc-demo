package com.example.demo.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


//使一个线程等待其他线程各自执行完毕后再执行。
public class CountDownLatchDemo1 {
    public static void main(String[] args) {
        final CountDownLatch latch=new CountDownLatch(2);
        System.out.println("主线程开始执行");

        ExecutorService es1= Executors.newSingleThreadExecutor();
        es1.execute(()->{
            try{
                Thread.sleep(3000);
                System.out.println(Thread.currentThread().getName()+"执行");
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            latch.countDown();//将count值减1
        });
        es1.shutdown();

        ExecutorService es2=Executors.newSingleThreadExecutor();
        es2.execute(()->{
            try {
                Thread.sleep(3000);
                System.out.println(Thread.currentThread().getName()+"执行");
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            latch.countDown();
        });
        es2.shutdown();
        System.out.println("等待两个线程执行完毕");
        try{
            latch.await();//调用await()方法的线程会被挂起，它会等待直到count值为0才继续执行
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("两个线程都执行完毕");
    }
}
