package com.example.demo.concurrent;

public class DaemonThreadDemo {
    //守护进程会随着主进程的结束而结束,不会有InterruptedException
    public static void main(String[] args) {
        Thread t1=new Thread(()->{
            for(int i=0;i<50;i++){
                System.out.println(Thread.currentThread().getName()+i);
            }
        });
        Thread t2=new Thread(()->{
            try{
                while(true){
                    Thread.sleep(1);
                    System.out.println(Thread.currentThread().getName());
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        });
        t2.setDaemon(true);
        t1.start();
        t2.start();
    }
}
