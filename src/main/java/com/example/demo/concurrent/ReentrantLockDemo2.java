package com.example.demo.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//可重入锁可以实现公平锁机制，及等待时间长的线程有限获取锁
public class ReentrantLockDemo2 {
    private static final Lock lock=new ReentrantLock(true);

    public static void main(String[] args) {
        new Thread(()->test(),"线程A").start();
        new Thread(()->test(),"线程B").start();
        new Thread(()->test(),"线程C").start();
        new Thread(()->test(),"线程D").start();
        new Thread(()->test(),"线程E").start();
    }
    public static void test(){
        for(int i=0;i<2;i++){
            try{
                lock.lock();
                System.out.println(Thread.currentThread().getName()+"获得了锁");
            }finally {
                lock.unlock();
            }
        }
    }
}
