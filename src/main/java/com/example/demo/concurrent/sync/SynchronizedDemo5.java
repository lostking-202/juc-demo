package com.example.demo.concurrent.sync;

public class SynchronizedDemo5 {
    public static synchronized void method1() {
        try{
            System.out.println("method1");
            Thread.sleep(5000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }
    public static synchronized void method2() {
        try {
            System.out.println("method2");
            Thread.sleep(5000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SynchronizedDemo5 demo51 = new SynchronizedDemo5();
        SynchronizedDemo5 demo52 = new SynchronizedDemo5();
        /*new Thread(() -> demo51.method1()).start();
        new Thread(() -> demo52.method2()).start();*/
        //静态方法，只要属于一个类，都会互斥
        new Thread(() -> SynchronizedDemo5.method1()).start();
        new Thread(() -> SynchronizedDemo5.method2()).start();
    }
}
