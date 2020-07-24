package com.example.demo.concurrent;

public class ThreadLocalDemo2 {
    static class ResourceClass{
        public final static ThreadLocal<String> RESOURCE1=new ThreadLocal<>();
        public final static ThreadLocal<String> RESOURCE2=new ThreadLocal<>();
    }
    static class A{
        public void setOne(String value){
            ResourceClass.RESOURCE1.set(value);
        }
        public void setTwo(String value){
            ResourceClass.RESOURCE2.set(value);
        }
    }
    static class B{
        public void display(){
            System.out.println(ResourceClass.RESOURCE1.get()+ResourceClass.RESOURCE2.get());
        }
    }

    public static void main(String[] args) {
        final A a=new A();
        final B b=new B();
        for(int i=0;i<15;i++){
            final String resource1="线程"+i;
            final String resource2="thread"+i;
            new Thread(){
                @Override
                public void run() {
                    try{
                        a.setOne(resource1);
                        a.setTwo(resource2);
                        b.display();
                    }finally {
                        ResourceClass.RESOURCE2.remove();
                        ResourceClass.RESOURCE1.remove();
                    }
                }
            }.start();
        }
    }
}
