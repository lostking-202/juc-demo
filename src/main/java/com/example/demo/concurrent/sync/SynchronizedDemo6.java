package com.example.demo.concurrent.sync;

public class SynchronizedDemo6 {
    //同一个非静态方法，不同实例也不会互斥,相同实例会互斥
    public static void main(String[] args) {
        SynchronizedDemo4 demo41 = new SynchronizedDemo4();
        SynchronizedDemo4 demo42 = new SynchronizedDemo4();
        /*new Thread(() -> demo41.method1()).start();
        new Thread(() -> demo42.method1()).start();*/
        new Thread(() -> demo41.method1()).start();
        new Thread(() -> demo41.method1()).start();
    }
}
