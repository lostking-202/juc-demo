package com.example.demo.concurrent;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoinDemo1 {
    public static void main(String[] args) {
        long l=System.currentTimeMillis();
        ForkJoinPool pool=new ForkJoinPool();
        ForkJoinTask<Long> task=new ForkJoinWork(0L,1000000000000L);
        Long result=pool.invoke(task);
    }
}
class ForkJoinWork extends RecursiveTask<Long>{
    private Long start;
    private Long end;
    public static final Long critical=100000L;
    public ForkJoinWork(Long start,Long end){
        this.start=start;
        this.end=end;
    }
    @Override
    protected Long compute() {
        Long length=end-start;
        if(length<=critical){
            Long sum=0L;
            for(Long i=start;i<=end;i++){
                sum+=i;
            }
            return sum;
        }else{
            Long middle=(end+start)/2;
            ForkJoinWork left=new ForkJoinWork(start,middle);
            left.fork();
            ForkJoinWork right=new ForkJoinWork(middle,end);
            right.fork();
            return left.join()+right.join();
        }
    }
}
