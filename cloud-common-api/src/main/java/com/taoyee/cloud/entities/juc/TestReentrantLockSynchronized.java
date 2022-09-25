package com.taoyee.cloud.entities.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 避免死锁 ：ReentrantLock可重入互斥锁、Synchronized同步锁
 * @description: 可重入锁 即 递归锁：线程可以进入  任何一个  它已经拥有的锁  所同步的代码块
 *                               在同一线程 在外层方法获取锁的时候，在进入内层方法会自动获取锁
 *                               主人进了外大门，就可以进入厨房门
 * @author: taoyee
 * @date: 2022-09-18 10:16
 * @version:
 */
public class TestReentrantLockSynchronized {

    public static void main(String[] args) {
        Phonee phone=new Phonee();
        System.out.println("=====T1、T2是同步锁==============");
        new Thread (() -> {
            phone.sengMsg();
        },"T1").start();

        new Thread (() -> {
            phone.sengMsg();
        },"T2").start();

        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("======T3、T4是ReentrantLock=============");
        new Thread (() -> {
            phone.gett();
        },"T3").start();

        new Thread (() -> {
            phone.gett();
        },"T4").start();

    }
}
class Phonee  {
    public  synchronized void sengMsg(){//外层 持有 同步锁
        System.out.println(Thread.currentThread().getName()+"\t 进入大门 sengMsg()");
        receiveMsg();//外层 调用 内层 方法
    }
    public synchronized void receiveMsg() {//内层 持有 同步锁
        System.out.println(Thread.currentThread().getName()+"\t 进入厨房 receiveMsg()");
    }
    //==========================================================
    Lock lock= new ReentrantLock();//可重入互斥锁
    public void gett(){
        lock.lock();//锁lock与解锁unlock必须 匹配 或 成对 出现
        //lock.lock();
        System.out.println(Thread.currentThread().getName()+"\t 进入大门 gett()");
        try {
            sett();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            //lock.unlock();
        }
    }
    public void sett(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t 进入厨房 sett()");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
