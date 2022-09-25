package com.taoyee.cloud.entities.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @description: 自旋锁:指尝试获取锁的线程不会立即阻塞，而是采取循环的方式去 尝试获取锁
 * 优点：减少线程上下文切换的消耗
 * 缺点：循环会导致消耗CPU
 * @author: taoyee
 * @date: 2022-09-18 16:54
 * @version: 1.0
 */
public class TestSpinLock {
    AtomicReference<Thread> atomicInteger = new AtomicReference<>();

    public void mylock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "\t 进入了！开始工作了！");
        while (!atomicInteger.compareAndSet(null, thread)) {
        }
    }

    public void myunlock() {
        Thread thread = Thread.currentThread();
        atomicInteger.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName() + "\t 进入了！工作完成了！");
    }

    public static void main(String[] args) {
        TestSpinLock testSpinLock = new TestSpinLock();
        new Thread(() -> {
            testSpinLock.mylock();
            try {
                TimeUnit.MILLISECONDS.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            testSpinLock.myunlock();
        }, "T1").start();


        new Thread(() -> {
            testSpinLock.mylock();
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            testSpinLock.myunlock();
        }, "T2").start();

    }
}