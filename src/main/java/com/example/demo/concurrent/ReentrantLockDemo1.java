package com.example.demo.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock和synchronized的区别
 * ReentrantLock可重入
 * ReentrantLock公平锁
 * ReentrantLock可中断
 * ReentrantLock同步非阻塞 乐观并发  synchronized 同步阻塞 悲观并发 synchronized 的实现涉及到锁的升级，具体为无锁、偏向锁、自旋锁、向OS申请重量级锁，
 */
public class ReentrantLockDemo1 {
    private static final Lock lock=new ReentrantLock();

    public synchronized static void main(String[] args) {

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
