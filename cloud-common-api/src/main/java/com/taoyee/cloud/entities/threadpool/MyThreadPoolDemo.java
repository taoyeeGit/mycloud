package com.taoyee.cloud.entities.threadpool;

import java.util.concurrent.*;

/**
 * @description:  线程池
 * @author: taoyee
 * @date: 2022-09-20 14:50
 * @version:
 */
public class MyThreadPoolDemo {

    public static void main(String[] args) {
        //apiThreadpool();//线程池API练习
ExecutorService threadpool=new ThreadPoolExecutor(
        2,//核心线程池数
        5,//最大
        6,//存活或销毁时间
        TimeUnit.SECONDS,//时间单位
        new LinkedBlockingQueue<Runnable>(5),//工作队列
        Executors.defaultThreadFactory(),//默认工厂
        //四种拒绝策略
        new ThreadPoolExecutor.AbortPolicy()  //拒绝策略1，默认 的
        //new ThreadPoolExecutor.CallerRunsPolicy()  //拒绝策略2
        //new ThreadPoolExecutor.DiscardOldestPolicy()  //拒绝策略
        //new ThreadPoolExecutor.DiscardPolicy()  //拒绝策略4
        );

        try {
            for (int i = 0; i < 10; i++) {
                int finalI = i;
                threadpool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t 开始办理业务==="+ finalI);
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadpool.shutdown();
        }

    }

    private static void egThreadpool() {//线程池API练习
        //ExecutorService threadPoll=Executors.newFixedThreadPool(5);
        //ExecutorService threadPoll=Executors.newSingleThreadExecutor();
        ExecutorService threadPoll= Executors.newCachedThreadPool();
        try {
            for (int i = 0; i < 10; i++) {
                threadPoll.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t 办理业务");
                });
                TimeUnit.MILLISECONDS.sleep(200);
                System.out.println("Integer最大值："+Integer.MAX_VALUE);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPoll.shutdown();
        }
    }
}
class MyResource{

}
