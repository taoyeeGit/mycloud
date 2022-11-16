package com.taoyee.cloud.example.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**  CyclicBarrier同步屏障, 赛克利克百瑞尔
 * @description: 集五福才能兑奖，与CountDownLatch相反，做加法，加到5才能抽奖。
 * @author: taoyee
 * @date: 2022-09-19 10:47
 * @version: 1.0
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        //wfLambdaOK();
        //jiwufu();
        wfOK();

    }

    private static void wfOK() {
        CyclicBarrier cyclicBarrier=new CyclicBarrier(5, () -> new Runnable() {
            @Override
            public void run() {
                System.out.println( "\t    五福集齐，开奖！");
            }
        }.run());
        for (int i = 0; i <5 ; i++) {
            int finalI = i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t    收集到" + WfEnum.getWfEnum(finalI).getWfInfo());
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },"AAA").start();
        }
    }

    private static void wfLambdaOK() {
        CyclicBarrier cyclicBarrier=new CyclicBarrier(5,()->System.out.println("\t    五福集齐，开奖！"));
        for (int i = 0; i <5 ; i++) {
            int finalI = i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t    收集到 \t    " + WfEnum.getWfEnum(finalI).getWfInfo());
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },"AAA").start();
        }
    }

    private static void jiwufu() {
        CyclicBarrier cyclicBarrier=new CyclicBarrier(5);
        for (int i = 0; i <5 ; i++) {

            int finalI = i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t    收集到" + WfEnum.getWfEnum(finalI).getWfInfo());
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }

            },"AAA").start();
        }

        new Thread(()->{
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"\t    五福集齐，开奖！" );

        },Thread.currentThread().getName()).start();
    }
}
