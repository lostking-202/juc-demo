package com.example.demo.concurrent;

//不加volatile只是可见性较差，不能及时读取共享变量的变化，但过一段时间或者加其他干扰还是可以读取共享变量的变化，但是要是保证每个线程都能即使读取共享变量的变化，还是要加volatile关键字的
public class VolatileDemo3 {
    boolean isStop=false;
    public void test(){
        Thread t1= new Thread(() -> isStop=true);
        Thread t2= new Thread(() -> {
            while(!isStop){
                //https://www.jianshu.com/p/f67ce303030a
                //synchronized和IO操作都是比较重量级的操作，让线程有机会放弃CPU的使用权，并且有机会去拉去主内存中的变量。

                //System.out.println("1");
            };
        });
        t1.start();;
        t2.start();
    }
    public static void main(String[] args) {
        for(int i=0;i<25;i++){
            new VolatileDemo3().test();
        }
    }
}
