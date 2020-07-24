package com.example.demo.concurrent.sync;

public class SynchronizedDemo3 {
    private int value = 1;

    public static void main(String[] args) {
        SynchronizedDemo3 demo3 = new SynchronizedDemo3();
        new Thread(() -> {
            while (true) {
                System.out.println("用户" + Thread.currentThread().getName() + "买了第" + demo3.increamentAndGet() + "张票");
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "张三").start();
        new Thread(() -> {
            while (true) {
                System.out.println("用户" + Thread.currentThread().getName() + "买了第" + demo3.increamentAndGet() + "张票");
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "王五").start();
        new Thread(() -> {
            while (true) {
                System.out.println("用户" + Thread.currentThread().getName() + "买了第" + demo3.increamentAndGet() + "张票");
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "李四").start();

    }

    //synchronized
    public int increamentAndGet() {
        return value++;
    }
}
