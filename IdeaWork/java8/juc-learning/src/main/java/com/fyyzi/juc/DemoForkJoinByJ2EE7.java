package com.fyyzi.juc;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author 息阳
 * 2018/2/2 16:48
 * @version 1.0
 */

public class DemoForkJoinByJ2EE7 {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        Long invoke = pool.invoke(new ForkJoinSumCalculate(0, 10000));
        System.out.println(invoke);
    }
}
class ForkJoinSumCalculate extends RecursiveTask<Long>{

    private static final long serialVersionUID = 7048372529242388831L;
    private long start;
    private long end;
    private static final Long THRSHOLD = 10L;

    public ForkJoinSumCalculate(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        Long length = end - start;
        if (length<=THRSHOLD){
            Long sum = 0L;
            for (long i = start ;i<=end;i++){
                sum += i;
            }
            return sum;
        }else{
            long middle = (start + end )/2;
            ForkJoinSumCalculate left = new ForkJoinSumCalculate(start,middle);
            left.fork();
            ForkJoinSumCalculate right = new ForkJoinSumCalculate(middle+1,end);
            right.fork();
            return left.join() + right.join();
        }
    }

}

