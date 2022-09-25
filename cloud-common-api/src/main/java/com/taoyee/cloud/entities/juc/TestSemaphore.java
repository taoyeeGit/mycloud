package com.taoyee.cloud.entities.juc;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.RandomUtil;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: taoyee
 * @date: 2022-09-19 12:17
 * @version:
 */
public class TestSemaphore {
    public static void main(String[] args) {
        Integer s=3;
        Semaphore semaphore=new Semaphore(s);

        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread (()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"\t   抢到车位！"+ finalI);
                    Integer waittime= RandomUtil.randomInt(10,10000);
                    try {TimeUnit.MILLISECONDS.sleep(waittime); } catch (InterruptedException e) {e.printStackTrace();}
                    System.out.println(Thread.currentThread().getName()+"\t   离开车位！"+ finalI);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            },"T"+i).start();
        }
    }
}
