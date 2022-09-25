package com.taoyee.cloud.entities.juc;

import java.util.concurrent.TimeUnit;

/**
 * 验证 volatile的 可见性
 */
public class TestVolatile {
    volatile int i = 0;//注意加volatile关键字前后的变化
    public void intTo60() {
        i = 60;
    }

    public static void main(String[] args) {
        TestVolatile tv = new TestVolatile();
        new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(5000);
                System.out.println(Thread.currentThread().getName()+"\t ====come in ==="+tv.i);
                tv.intTo60();
                System.out.println(Thread.currentThread().getName()+"\t ============"+tv.i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        },"AAA").start();
        while(tv.i==0){
            ///////////////
        }
        new Thread(() -> {
        System.out.println(Thread.currentThread().getName()+"\t  get i value: "+tv.i);
        },"BBB").start();
    }
}
