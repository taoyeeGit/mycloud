package com.taoyee.cloud.example.juc;

import java.security.KeyStore;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: 一个初始值为零的变量，两个线程对其交替操作，一个加一个减，来5轮 addDesc();
 * @author: taoyee
 * @date: 2022-09-19 21:07
 * @version:
 */
public class SczXfzDemo {
    public static void main(String[] args) {
        addDesc();//练习题

        scxfMethod();//生产者与消费者典型例子
    }

    private static void scxfMethod() {
        ShareResource shareResource = new ShareResource(new ArrayBlockingQueue<String>(10));
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"\t 生产线程启用");
            shareResource.sc();
        }, "生产线程").start();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"\t 消费线程启用");
            System.out.println("");
            System.out.println("");
            shareResource.xf();
        }, "消费线程").start();
        try {
            TimeUnit.MILLISECONDS.sleep(5000);
            System.out.println("");
            System.out.println("");
            System.out.println(Thread.currentThread().getName()+"\t5秒钟时间到，老板叫停，活动结束，歇业中...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        shareResource.stop();
    }

    private static void addDesc() {
        ShareData shareData = new ShareData();
        for (int i = 0; i < 50; i++) {
            new Thread(() -> {
                shareData.add();

            }, "T1").start();

            new Thread(() -> {
                shareData.desc();

            }, "T2").start();
        }
    }

}

class ShareResource {
    private volatile boolean FLAG = true;
    private AtomicInteger atomicInteger = new AtomicInteger();
    BlockingQueue<String> blockingQueue = null;

    public ShareResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println("用的阻塞队列类型为：\t"+blockingQueue.getClass().getName());
    }
    public void stop(){//歇业
        this.FLAG=false;
    }
    public void sc() {//生产
        String data = null;
        boolean rtValue;
        while (FLAG) {
            data = atomicInteger.incrementAndGet() + "";
            try {
                rtValue = blockingQueue.offer(data, 2, TimeUnit.SECONDS);
                if (rtValue) {
                    System.out.println(Thread.currentThread().getName() + "\t  插入队列" + data + "成功！");
                } else {
                    System.out.println(Thread.currentThread().getName() + "\t  插入队列" + data + "失败！");
                }
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "\t  老板叫停，FLAG=false,生产结束！");
    }

    public void xf() {//消费
        String result = null;
        while (FLAG) {
            try {
                result = blockingQueue.poll(2, TimeUnit.SECONDS);
                if (result == null || result.equalsIgnoreCase("")) {
                    FLAG = false;
                    System.out.println(Thread.currentThread().getName() + "\t  超时2秒没有取到产品，消费退出！");
                    return;
                }
                System.out.println(Thread.currentThread().getName() + "\t  消费队列" + result + "成功！");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class ShareData {
    private int initValue = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();


    public void add() {
        lock.lock();
        try {
            while (initValue != 0) {
                condition.await();
            }
            initValue++;
            System.out.println(Thread.currentThread().getName() + "\t initValue=" + initValue);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void desc() {
        lock.lock();
        try {
            while (initValue == 0) {
                condition.await();
            }
            initValue--;
            System.out.println(Thread.currentThread().getName() + "\t initValue=" + initValue);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
