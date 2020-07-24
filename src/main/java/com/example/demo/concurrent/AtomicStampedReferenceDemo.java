package com.example.demo.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

//AtomicStampedReference可以用于解决CAS操作时的ABA的问题
public class AtomicStampedReferenceDemo {
    private static AtomicInteger atomicInteger=new AtomicInteger(100);
    private static AtomicStampedReference<Integer> atomicStampedReference=new AtomicStampedReference<>(100,0);

    public static void main(String[] args)throws InterruptedException{
        Thread t1=new Thread(()->{
            atomicInteger.compareAndSet(100,101);
            atomicInteger.compareAndSet(101,100);
        });
        Thread t2=new Thread(()->{
            try{
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e){

            }
            System.out.println(atomicInteger.compareAndSet(100,101));
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        Thread t3=new Thread(()->{
            try{
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e){

            }
            atomicStampedReference.compareAndSet(100,101,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            atomicStampedReference.compareAndSet(101,100,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
        });

        Thread t4=new Thread(()->{
            int stamp=atomicStampedReference.getStamp();
            try{
                TimeUnit.SECONDS.sleep(2);
            }catch (InterruptedException e){

            }
            System.out.println(atomicStampedReference.compareAndSet(100,101,stamp,stamp+1));
        });
        t3.start();
        t4.start();
    }
}
