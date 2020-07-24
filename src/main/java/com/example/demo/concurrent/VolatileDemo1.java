package com.example.demo.concurrent;

public class VolatileDemo1 extends Thread{
    public static boolean flag=false;
    //public static volatile boolean flag=false;
    @Override
    public void run() {
        while(!flag){
            System.out.println("1");
        }
    }
    public static void main(String[] args) throws InterruptedException {
        new VolatileDemo1().start();
        Thread.sleep(100);
        //主线程修改的flag必须要被VolatileDemo的线程可见才会终止循环
        flag=true;
    }
}
