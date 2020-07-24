package com.example.demo.concurrent;

public class DeadLockDemo {
    public static void main(String[] args) {
        Thread td1=new Thread(() -> {
            DeadLockDemo.method1();
        });
        td1.start();
        Thread td2=new Thread(()->{
            DeadLockDemo.method2();
        });
        td2.start();
    }
    public static void method1(){
        synchronized (String.class){
            try{
                Thread.sleep(2000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("线程A尝试获取Integer.class");
            synchronized (Integer.class){

            }
            System.out.println("线程a已经获取Integer.class");
        }
    }
    public static void method2(){
        synchronized (Integer.class){
            try{
                Thread.sleep(2000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("线程b尝试获取String.class");
            synchronized (String.class){

            }
            System.out.println("线程b已经获取String.class");
        }
    }
}
