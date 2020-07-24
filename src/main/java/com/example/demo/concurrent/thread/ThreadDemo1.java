package com.example.demo.concurrent.thread;

//wait()方法可以传入参数也可以不传入参数，传入参数就是在参数结束的时间后开始等待
//https://blog.csdn.net/zhaoheng2017/article/details/78409404
public class ThreadDemo1 {
    public static void main(String[] args) {
        final byte[] a={0};
        new Thread(new NumberPrint(1,a),"1").start();
        new Thread(new NumberPrint(2,a),"2").start();
    }

}

class NumberPrint implements Runnable{
    private int number;
    public byte res[];
    public static int count=5;
    public NumberPrint(int number,byte a[]){
        this.number=number;
        this.res=a;
    }
    @Override
    public void run() {
        synchronized (res){
            while(count-->0){
                try{
                    res.notify();
                    System.out.println(number);
                    res.wait();
                    System.out.println("线程"+Thread.currentThread().getName()+"获得锁");
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            return;
        }
    }
}

