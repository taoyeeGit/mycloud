package com.taoyee.cloud.example.juc;

import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: taoyee
 * @date: 2022-11-04 14:58
 * @version:
 */
public class Bank {
 static int money=950;

    public static void main(String[] args) {
       /// aaa();
        AAA qian = new AAA();
        Thread t1 = new Thread(qian);
        Thread t2 = new Thread(qian);
        Thread t3 = new Thread(qian);
        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");
        t1.start();
        t2.start();
        t3.start();
    }

    private static void aaa() {
        new Thread(()->{
            int uB=200;
            while (money>=uB){
                GT(uB);
                System.out.println(Thread.currentThread().getName()+"取款,还剩"+money+"元");
                /*try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            }
            System.out.println(Thread.currentThread().getName()+"取款,余额不足！,还剩"+money+"元");
        },"用户B").start();

        new Thread(()->{
            int ua=100;
            while (money>=ua){
                ATM(ua);
                System.out.println(Thread.currentThread().getName()+"取款,还剩"+money+"元");
               /* try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            }
            System.out.println(Thread.currentThread().getName()+"取款,余额不足！,还剩"+money+"元");
        },"用户A").start();
    }

    public static void ATM(int money){
        Bank.money-=money;
    }
    public static void GT(int money){
        Bank.money-=money;
    }

}
class AAA implements Runnable{
   private int qian=1000;
    Object obj=new Object();
    @Override
    public void run() {

        while(true){
synchronized (obj){
            if(qian>=100){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                qian=qian-10;
                System.out.println(Thread.currentThread().getName()+"还剩"+qian+"元");
            }else{
                break;
            }
        }
    }
}}