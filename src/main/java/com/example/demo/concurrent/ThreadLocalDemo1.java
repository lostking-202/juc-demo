package com.example.demo.concurrent;

public class ThreadLocalDemo1 {
    static class MyThread extends Thread{
        private static ThreadLocal<Integer> threadLocal=new ThreadLocal<>();
        @Override
        public void run() {
            super.run();
            for(int i=0;i<3;i++){
                threadLocal.set(i);
                System.out.println(getName()+"---"+threadLocal.get());
            }
        }
    }
    public static void main(String[] args) {
        MyThread thread1=new MyThread();
        thread1.setName("thread1");
        MyThread thread2=new MyThread();
        thread2.setName("thread2");
        thread1.start();
        thread2.start();
    }
}
