package com.taoyee.cloud.example.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description: 验证 Volatile的可见性、不保证原子性、如何保证原子性
 *               Volatile是轻量级的同步机制，其特性：可见性、不保证原子性、禁止指令重排
 * @author: taoyee
 * @date: 2022-11-01 17:30
 * 线程操作资源类
 * @version: 0.0.1
 */
public class VolatileDemo {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("============Volatile的可见性=================");
        seeOk();//Volatile的可见性
        System.out.println();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("============Volatile不保证原子性=================");
        noAtomic();//Volatile不保证原子性
        System.out.println();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("============Volatile 保证原子性的解决方法=================");
        yesAtomic();
    }
    public static void seeOk(){//Volatile的可见性
        SeeOk  so = new SeeOk();
        new Thread( () -> {
            try {
                TimeUnit.SECONDS.sleep(6);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            so.mynumberTo60();
            System.out.println(Thread.currentThread().getName()+"===mynumber==="+so.mynumber);
        },"AAA").start();

        while(so.mynumber==0){
        //主线程/BBB线程 线程就一直在这循环直到mynumber不等于0
        //若进入此方法，说明mynumber没有任何变化，没有任何变化则无需做任何操作处理,即进入等待状态
        }
        System.out.println(Thread.currentThread().getName()+"===mynumber==="+so.mynumber);//主线程
        /*new Thread( () -> {
            System.out.println(Thread.currentThread().getName()+"===mynumber==="+so.mynumber);
        },"BBB").start();*/

    }
    public static void noAtomic(){//Volatile不保证原子性
        NoAtomic  noAtomic = new NoAtomic();
        for (int i = 0; i <20 ; i++) {
            new Thread( () -> {
                for (int j = 0; j < 1000; j++) {
                    noAtomic.add();
                }
            },"AAA").start();
        }
        while (Thread.activeCount() >2){
            // 2 是指最少有两个线程: main线程和 GC线程
            Thread.yield();//该方法会让当前线程交出CPU资源，让CPU去执行其他的线程， 不会释放锁。
            // 该方法不会让线程进入阻塞状态，而是让线程重回就绪状态，它只需要等待重新得到 CPU 的执行。
        }
        System.out.println("===mynumber==="+noAtomic.mynumber);//可以看出每次结果都不一样且不为20000.
    }

    public static void yesAtomic(){//Volatile如何保证原子性的解决方法
        YesAtomic  yesAtomic = new YesAtomic();
        for (int i = 0; i <20 ; i++) {
            new Thread( () -> {
                for (int j = 0; j < 1000; j++) {
                    yesAtomic.addAtomic();
                }
            },"AAA").start();
        }
        while (Thread.activeCount() >2){
            // 2 是指最少有两个线程: main线程和 GC线程
            Thread.yield();//该方法会让当前线程交出CPU资源，让CPU去执行其他的线程， 不会释放锁。
            // 该方法不会让线程进入阻塞状态，而是让线程重回就绪状态，它只需要等待重新得到 CPU 的执行。
        }
        System.out.println("===mynumber==="+yesAtomic.atomicInteger);//可以看出每次结果都不一样且不为200000.
    }
}
class  SeeOk{//Volatile的可见性,***注意对比加和没加volatile后的结果***
    //int mynumber=0;//没加关键字 volatile mynumber的结果不可见
    volatile int mynumber=0;// 加了关键字 volatile  mynumber的结果可见了
    public void mynumberTo60() {mynumber=60;}
}
class  NoAtomic{//Volatile不保证原子性
    volatile int mynumber=0;//尽管有volatile，但是它不保证原子性
    //该方法上加关键字：synchronized可解决原子性问题，但是没意义，本例讲的是volatile，用synchronized就是杀鸡用牛刀！
    public  void add() {mynumber++;}
}
class  YesAtomic{//如何保证原子性的解决方法：通过 AtomicInteger 包装类可以保证变量的原子性。
    AtomicInteger atomicInteger=new AtomicInteger(0);//初始为0
    public void addAtomic() {
        atomicInteger.getAndIncrement();// +1
    }
}