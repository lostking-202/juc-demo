package com.example.demo.springdemo.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AsyncService {
    private final TaskExecutor asyncThreadPoolExecutor;

    public AsyncService(@Qualifier("applicationTaskExecutor") TaskExecutor asyncThreadPoolExecutor) {
        this.asyncThreadPoolExecutor = asyncThreadPoolExecutor;
    }

    public void testAsync(){
        for(int i=0;i<100;i++){
            asyncThreadPoolExecutor.execute(()->{
                System.out.println(Thread.currentThread().getName()+"开始了"+LocalDateTime.now());
                try {
                    Thread.sleep(5);
                    System.out.println(Thread.currentThread().getName()+"结束了"+LocalDateTime.now());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
