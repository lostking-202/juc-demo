package com.example.demo.concurrent;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;


//Semaphore用于限定同一时间线程进行的最大个数
public class SemaphoreDemo {
    public static void main(String[] args) {
        ExecutorService executorService= Executors.newCachedThreadPool();

        final Semaphore semaphore=new Semaphore(3);

        for(int i=0;i<10;i++){
            final long num=i;
            executorService.execute(()->{
                try{
                    semaphore.acquire();
                    System.out.println("Accessing"+num);
                    Thread.sleep(new Random().nextInt(5000));
                    semaphore.release();
                    System.out.println("Release..."+num);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
    }
}
