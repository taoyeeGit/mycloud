package com.taoyee.cloud.entities.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author: taoyee
 * @date: 2022-09-19 23:27
 * @version:
 */
public class TestSyncAndReentrantLock {
    public static void main(String[] args) {
        SyncAndReentResource syncAndReentResource = new SyncAndReentResource();
        for (int i = 0; i <5 ; i++) {
            new Thread(() -> {
                syncAndReentResource.print5();
            }, "AA").start();
            new Thread(() -> {
                syncAndReentResource.print10();
            }, "BB").start();
            new Thread(() -> {
                syncAndReentResource.print15();
            }, "CC").start();
        }

    }
}

class SyncAndReentResource {
    private int number = 1;
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();
    public void print5() {
        lock.lock();
        try {
            while (number != 1) {
                condition1.await();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName()+"\t  "+i);
            }

            number = 2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void print10() {
        lock.lock();
        try {
            while (number != 2) {
                condition2.await();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName()+"\t  "+i);
            }
            number = 3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15() {
        lock.lock();
        try {
            while (number != 3) {
                condition3.await();
            }
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName()+"\t  "+i);
            }
            number = 1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


}
