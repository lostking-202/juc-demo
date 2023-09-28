package com.example.demo.concurrent.sync;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class SoldTicket {

    @Test
    public void test() throws InterruptedException {
        sellTicket();
    }

    private static volatile AtomicInteger ticketNum=new AtomicInteger(5000);

    public void sellTicket() throws InterruptedException {
        for(int i=0;i<5;i++){
            Thread t=new Thread(()->{
                //这里主要因为跳不出去，只能多写一遍了
                while(ticketNum.get()>0&&ticketNum.get()>=1){
                    a(1);
                }
            });
            t.start();
         }
        Thread.sleep(10000);
        System.out.println(ticketNum);
    }

    public synchronized void a(int num){
        if(ticketNum.get()>0&&ticketNum.get()>=num){
            System.out.println(ticketNum+Thread.currentThread().getName());
            ticketNum.addAndGet(-num);
        }
    }
}
