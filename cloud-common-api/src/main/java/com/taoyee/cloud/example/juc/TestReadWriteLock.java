package com.taoyee.cloud.example.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @description: 读写锁ReentrantReadWriteLock：读-->读，可以共存；读-->写，不可共存；写-->写，不可共存
 * @author: taoyee
 * @date: 2022-09-18 17:44
 * @version: 1.0
 */
public class TestReadWriteLock {

    public static void main(String[] args) {
        System.out.println("");
        Mycache mycache = new Mycache();
        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                mycache.putt(i + "", i + "");
            }
        }, "AAA").start();

        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                mycache.read(i + "");
            }
        }, "BBB").start();
    }
}

class Mycache {
    private  volatile Map<String, Object> map = new HashMap<>();
    private ReentrantReadWriteLock rrl = new ReentrantReadWriteLock();

    public  void putt(String key, Object value) {
        rrl.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在写入key");
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t 写key完成");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rrl.writeLock().unlock();
        }
    }

    public void read(String key) {
        rrl.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在读value");
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t 读value完成:" + result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rrl.readLock().unlock();
        }
    }
}