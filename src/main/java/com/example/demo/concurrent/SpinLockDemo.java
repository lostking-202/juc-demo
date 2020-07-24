package com.example.demo.concurrent;

import java.util.concurrent.atomic.AtomicStampedReference;

//自旋锁
public class SpinLockDemo {
    static AtomicStampedReference<Thread> atomicThread=new AtomicStampedReference<>(null,1);

    public static void main(String[] args) {
        new Thread(()->{
            myLock();
            try{
                Thread.sleep(5000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                myUnlock();
            }
        },"AAA").start();
        new Thread(()->{
            myLock();
            myUnlock();
        },"BBB").start();
    }

    public static void myLock(){
        Thread thread=Thread.currentThread();
        while(!atomicThread.compareAndSet(null,thread,1,2)){
            System.out.println(thread.getName()+"加锁失败");
        }
        System.out.println(thread.getName()+"加锁成功");
    }

    public static void myUnlock(){
        Thread thread=Thread.currentThread();
        while(!atomicThread.compareAndSet(thread,null,2,1)){
            System.out.println(thread.getName()+"解锁失败");
        }
        System.out.println(thread.getName()+"解锁成功");
    }

}
