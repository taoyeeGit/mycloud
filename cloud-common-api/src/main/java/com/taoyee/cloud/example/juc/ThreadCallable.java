package com.taoyee.cloud.example.juc;
import java.util.concurrent.*;

/**
 * @description:
 * @author: taoyee
 * @date: 2022-11-06 15:35
 * @version:
 */
public class ThreadCallable {
    public static void main(String[] args) {
        //tcallable();
        RejectedExecutionHandler j=new ThreadPoolExecutor.AbortPolicy();
        BlockingQueue bb=new LinkedBlockingQueue(10);
        BlockingQueue bc=new DelayQueue();
        BlockingQueue bd=new PriorityBlockingQueue();
        BlockingQueue be=new ArrayBlockingQueue(10);
        ThreadPoolExecutor tt = new ThreadPoolExecutor(2,5,1,TimeUnit.MILLISECONDS,bc,Executors.defaultThreadFactory(),new  ThreadPoolExecutor.AbortPolicy());
        TCallable numThread = new TCallable();
        TRuncale tr = new TRuncale();
        tt.execute(tr);
        tt.submit(numThread);
        tt.shutdown();
    }
    private static void tcallable() {
        TCallable numThread = new TCallable();
        FutureTask futureTask = new FutureTask<>(numThread);
        Thread t1 = new Thread(futureTask);
        Thread t2 = new Thread(futureTask);
        t1.start();
        t2.start();
        try {
            Object o = futureTask.get();
            System.out.println(o);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
class TCallable implements Callable {
    @Override
    public Object call() throws Exception {
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            if (i % 2 == 0) {
                sum += i;
            }
        }
        System.out.println(Thread.currentThread().getName()+":"+sum);
        return sum;
    }
}
class TRuncale implements Runnable {
    @Override
    public void run() {
        int t=0;
        for (int i = 1; i <=100 ; i++) {
            if(i%2!=0){
                t+=i;
            }
        }
        System.out.println(Thread.currentThread().getName()+":"+t);
    }
}
