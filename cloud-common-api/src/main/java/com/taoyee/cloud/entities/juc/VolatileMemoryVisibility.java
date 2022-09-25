package com.taoyee.cloud.entities.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class VolatileMemoryVisibility {
    public static void main(String[] args) {
        volatileIsSee();//验证Volatile的可见性
        //volatileSeeOk();
    }
    static class VolatileIsSee {//资源库，验证Volatile的可见性
        /**
         * Volatile关键字：【内存可见性】
         * - 使用语句（1），注释语句（2） -> 程序一直等待
         * - 使用语句（2），注释语句（1） -> 程序正常结束
         * @author taoyee
         */
        //private int number;                // （1）
        private volatile int number;     // （2）
        public void changeNum() {
            this.number = 100;
        }
        public void changeNumAdd() {
            this.number++;//多线程下，会导致值覆盖了，从而导致丢失，不能保证完整性
        }
        //AtomicInteger atomicInteger=new AtomicInteger();
    }
    public static void volatileSeeOk (){

        VolatileIsSee vis = new VolatileIsSee();
        new Thread(() -> {//线程 操作 资源库
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t  线程is come in  number==" + vis.number);
            vis.changeNum();
            System.out.println(Thread.currentThread().getName() + "\t  线程任务已已完成  number==" + vis.number);
        }, "AAA").start();
        while (vis.number == 0) {
            //若进入此方法，说明number没有任何变化，没有任何变化则无需做任何操作处理,即进入等待状态
        }
        System.out.println(Thread.currentThread().getName() + " 线程任务已已完成，  number已经发送变化，其值为 \t  :" + vis.number);

    }
    public static void volatileIsSee(){//验证Volatile的可见性
        VolatileIsSee rd = new VolatileIsSee();
        new Thread(() -> {//线程 操作 资源库
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t  线程is come in  number==" + rd.number);
            rd.changeNum();
            System.out.println(Thread.currentThread().getName() + "\t  线程任务已已完成  number==" + rd.number);
        }, "AAA").start();
        while (rd.number == 0) {
            //若进入此方法，说明number没有任何变化，没有任何变化则无需做任何操作处理,即进入等待状态
        }
        System.out.println(Thread.currentThread().getName() + " 线程任务已已完成，  number已经发送变化，其值为 \t  :" + rd.number);
    }
}