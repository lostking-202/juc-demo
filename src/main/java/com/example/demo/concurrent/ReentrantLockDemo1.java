package com.example.demo.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo1 {
    private static final Lock lock=new ReentrantLock();

    public static void main(String[] args) {
        new Thread(()->test(),"线程A").start();
        new Thread(()->test(),"线程B").start();
    }

    public static void test(){
        try{
            lock.lock();
            System.out.println(Thread.currentThread().getName()+"获得了锁");
        }finally {
            System.out.println(Thread.currentThread().getName()+"释放了锁");
            lock.unlock();
        }

    }
}
