package com.taoyee.cloud.example.threadpool;

import java.util.concurrent.*;

/**
 * @description: 线程池的创建:  通过  Executors 或 ThreadPoolExecutor两种方式创建线程池
 * @author: taoyee
 * 创建线程池的方式具体又有 7种实现方法：
 * 0、ThreadPoolExecutor：手动创建线程池的方式，它创建时最多可以设置 7个参数。（***强烈建议用该方法***）
 *
 * 1、Executors.newFixedThreadPool：创建一个固定大小的线程池，可控制并发的线程数，超出的线程会在队列中等待。
 * 2、Executors.newCachedThreadPool：创建一个可缓存的线程池，若线程数超过处理所需，缓存一段时间后会回收，若线程数不够，则新建线程。
 * 3、Executors.newScheduledThreadPool：创建一个可以执行延迟任务的线程池。
 * 4、Executors.newSingleThreadExecutor：创建单个线程数的线程池，它可以保证先进先出的执行顺序。
 * 5、Executors.newSingleThreadScheduledExecutor：创建一个单线程的可以执行延迟任务的线程池。
 *
 * 6、Executors.newWorkStealingPool：创建一个抢占式执行的线程池(任务执行顺序不确定)【JDK 1.8 添加】。
 *
 * @date: 2022-09-20 14:50
 * @version:
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {
       //apiThreadpool();//线程池API练习   ctrl +alt +m -->提出方法
        sdcjpool();//手动创建线程池
    }

    private static void sdcjpool() {
        ExecutorService threadpool = new ThreadPoolExecutor(
                2,//核心线程池数
                5,//最大线程池数
                1,  //存活或销毁时间
                TimeUnit.SECONDS,//时间单位
                new LinkedBlockingQueue<Runnable>(5),//阻塞队列数
                Executors.defaultThreadFactory(),//默认工厂
                //四种拒绝策略，超过【最大线程池】+【阻塞队列】的数量和，则
                new ThreadPoolExecutor.AbortPolicy()  //拒绝策略1，默认的,直接报Exception
                //new ThreadPoolExecutor.CallerRunsPolicy()  //拒绝策略2,不报错，也不丢弃，而是将超出线程退回给调用者
                //new ThreadPoolExecutor.DiscardOldestPolicy()  //拒绝策略3，丢弃队列中等待最久的
                //new ThreadPoolExecutor.DiscardPolicy()  //拒绝策略4，超过的直接丢弃
                );
        try {
            for (int i = 0; i < 12; i++) {
                int finalI = i;
                threadpool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 开始办理业务===" + finalI);
                });
            }
        } catch (Exception e) {e.printStackTrace();}
        finally {threadpool.shutdown();}
    }
    private static void apiThreadpool() {//线程池API练习，六种方法创建线程池
        //创建一个固定大小的线程池，可控制并发的线程数，超出的线程会在队列中等待。
        //ExecutorService threadPoll=Executors.newFixedThreadPool(5);

        //创建一个可缓存的线程池，若线程数超过处理所需，缓存一段时间后会回收，若线程数不够，则新建线程。
        ExecutorService threadPoll=Executors.newCachedThreadPool();

        //创建一个可以执行延迟任务的线程池。
        //ExecutorService threadPoll=Executors.newScheduledThreadPool(3);

        //创建单个线程数的线程池，它可以保证先进先出的执行顺序。
        //ExecutorService threadPoll=Executors.newSingleThreadExecutor();

        //创建一个单线程的可以执行延迟任务的线程池。
        //ExecutorService threadPoll=Executors.newSingleThreadScheduledExecutor();


        //创建一个抢占式执行的线程池(任务执行顺序不确定)【JDK 1.8 添加】。
        //ExecutorService threadPoll=Executors.newWorkStealingPool();
        try {
            for (int i = 0; i < 4; i++) {
                threadPoll.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
                TimeUnit.MILLISECONDS.sleep(200);
            }
        } catch (Exception e) { e.printStackTrace(); }
        finally { threadPoll.shutdown(); }
    }
}
