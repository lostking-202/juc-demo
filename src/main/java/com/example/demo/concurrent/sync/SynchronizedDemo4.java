package com.example.demo.concurrent.sync;

public class SynchronizedDemo4 {
    public synchronized void method1() {
        try{
            System.out.println("method1");
            Thread.sleep(5000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }
    public synchronized void method2() {
        try {
            System.out.println("method2");
            Thread.sleep(5000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //同一个实例的非静态方法会互斥，不同实例的非静态方法不互斥
        SynchronizedDemo4 demo41 = new SynchronizedDemo4();
        SynchronizedDemo4 demo42 = new SynchronizedDemo4();
        new Thread(() -> demo41.method1()).start();
        new Thread(() -> demo42.method2()).start();
    }
}
