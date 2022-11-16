package com.taoyee.cloud.example.juc;

import cn.hutool.json.JSONUtil;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * @description:
 * @author: taoyee
 * @date: 2022-11-04 20:24
 * @version:
 */
public class TestCountDown {

    public static void main(String[] args) {
        test();
        test1();
    }

    public static void  test(){
        CountDownLatch countDownLatch=new CountDownLatch(6);
        for (int i = 0; i <6 ; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"离开教室");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"班长离开教室");
    }
    public static void  test1(){
        CyclicBarrier cyclicBarrier=
                new CyclicBarrier( 5,()->{ System.out.println("\t 五福集齐，开奖"); });

        for (int i = 0; i <5 ; i++) {
            new Thread(()->{
                System.out.println("收集到 "+Thread.currentThread().getName()+"福");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },WfEnum.getWfEnum(i).getWfInfo()).start();
        }

    }
}
