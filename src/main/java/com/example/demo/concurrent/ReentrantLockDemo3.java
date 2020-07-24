package com.example.demo.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


//ReentrantLock加Condition可以让两个线程交替执行
public class ReentrantLockDemo3 {
    public static void main(String[] args) {
        Counter counter=new Counter();
        new Thread(()->{
            while(counter.getCount()>=0){
                counter.desc1();
            }
        }).start();
        new Thread(()->{
            while(counter.getCount()>=0){
                counter.desc2();
            }
        }).start();
    }
}

class Counter{
    private int count=100;
    private Lock lock=new ReentrantLock();
    Condition condition1=lock.newCondition();
    Condition condition2=lock.newCondition();
    boolean state=true;
    public void desc1(){
        lock.lock();
        try{
            while(state){
                condition1.await();
            }
            if(count>=0){
                System.out.println(Thread.currentThread().getName()+"--->"+count);
                count--;
            }
            state=true;
            condition2.signal();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void desc2(){
        lock.lock();
        try{
            while(!state){
                condition2.await();
            }
            if(count>=0){
                System.out.println(Thread.currentThread().getName()+"--->"+count);
                count--;
            }
            state=false;
            condition1.signal();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public int getCount(){
        return count;
    }
}
