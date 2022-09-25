package com.taoyee.cloud.entities.juc;

import java.sql.Time;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @description: CountDownLatch 倒计时，火箭发射=跨年倒计时，做减法，到零才能执行。
 * @author: taoyee
 * @date: 2022-09-18 23:57
 * @version:
 */
public class TestCountDownLatch {
    public static void main(String[] args) {
        //TestDemo.countDownLatch();
        CountDownLatch countDownLatch=new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread (()->{
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                    System.out.println(Thread.currentThread().getName()+"\t 灭亡！");
                    countDownLatch.countDown();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },MyEnum.getMyEnum(i).getMsg()).start();
        }
        try {
            countDownLatch.await();
            TimeUnit.MILLISECONDS.sleep(100);
            Thread.currentThread().setName("秦国");
            System.out.println(Thread.currentThread().getName()+"\t 一统天下");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class TestDemo{
    public static void countDownLatch() {
        CountDownLatch countDownLatch=new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread (()->{
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                    System.out.println(Thread.currentThread().getName()+"\t 离开教室"+1);
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"T"+String.valueOf(i)).start();
        }
        try {
            countDownLatch.await();
            TimeUnit.MILLISECONDS.sleep(100);
            System.out.println(Thread.currentThread().getName()+"\t 班长离开教室");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}