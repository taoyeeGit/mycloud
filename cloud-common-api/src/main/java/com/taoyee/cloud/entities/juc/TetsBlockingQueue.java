package com.taoyee.cloud.entities.juc;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @description: BlockingQueue:队列慢的时候，往里面添加元素会被阻塞；队列空的时候，从里面取出元素会被阻塞
 * @author: taoyee
 * @date: 2022-09-19 14:27
 * @version:
 */
public class TetsBlockingQueue {

    public static void main(String[] args) throws Exception{
        // List<String > arrlist=new ArrayList<>();

        /*BlockingQueue<String > blockingQueue=new ArrayBlockingQueue<>(3);
        blockingQueueLX();//API 练习
        */

        BlockingQueue<String> synchronousQueue=new SynchronousQueue<>();//单个元素的存取，存一个才能取一个
        eGsynchronousQueue();//API 练习example

    }
    private static void eGsynchronousQueue() {
        BlockingQueue<String> synchronousQueue=new SynchronousQueue<>();//单个元素的存取，存一个才能取一个
        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+"=ADD=111==");
                synchronousQueue.put("111");
                System.out.println(Thread.currentThread().getName()+"=ADD=222=");
                synchronousQueue.put("222");
                System.out.println(Thread.currentThread().getName()+"=ADD=333==");
                synchronousQueue.put("333");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        },"AAA").start();

        new Thread(()->{
            try {
                TimeUnit.MILLISECONDS.sleep(5000);
                System.out.println(Thread.currentThread().getName()+"==remove=="+ synchronousQueue.take());
                TimeUnit.MILLISECONDS.sleep(5000);
                System.out.println(Thread.currentThread().getName()+"==remove==" +synchronousQueue.take());
                TimeUnit.MILLISECONDS.sleep(5000);
                System.out.println(Thread.currentThread().getName()+"==remove=="+ synchronousQueue.take());;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"BBB").start();
    }

    private static void blockingQueueLX() throws InterruptedException {//API 练习
        BlockingQueue<String > blockingQueue=new ArrayBlockingQueue<>(3);
        /*System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        //System.out.println(blockingQueue.add("d"));//java.lang.IllegalStateException: Queue full
        System.out.println(blockingQueue.element());//检查队列的队首元素
        System.out.println(blockingQueue.add("e"));

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        //System.out.println(blockingQueue.element());//检查队列的队首元素, java.util.NoSuchElementException
        System.out.println(blockingQueue.remove());*/

        /*System.out.println(blockingQueue.offer("aaa"));//trye 添加
        System.out.println(blockingQueue.offer("bbb"));//true
        System.out.println(blockingQueue.offer("ccc"));//true
        System.out.println(blockingQueue.offer("ddd"));//false,添加不进去
        System.out.println(blockingQueue.peek());//检查队列的队首元素:aaa
        System.out.println(blockingQueue.offer("eee"));//false,添加不进去

        System.out.println(blockingQueue.poll());//aaa，移除
        System.out.println(blockingQueue.poll());//bbb
        System.out.println(blockingQueue.poll());//ccc
        System.out.println(blockingQueue.poll());//null
        System.out.println(blockingQueue.peek());//null
        System.out.println(blockingQueue.poll());//null*/

       /* blockingQueue.put("111");
        blockingQueue.put("222");
        blockingQueue.put("333");

        blockingQueue.take();
        blockingQueue.take();
        blockingQueue.take();
        blockingQueue.take();*/

        System.out.println(blockingQueue.offer("A",3, TimeUnit.SECONDS));//True
        System.out.println(blockingQueue.offer("B",3, TimeUnit.SECONDS));//
        System.out.println(blockingQueue.offer("C",3, TimeUnit.SECONDS));//
        System.out.println(blockingQueue.offer("D",3, TimeUnit.SECONDS));//false

        System.out.println(blockingQueue.poll(3, TimeUnit.SECONDS));//A
        System.out.println(blockingQueue.poll(3, TimeUnit.SECONDS));//B
        System.out.println(blockingQueue.poll(3, TimeUnit.SECONDS));//C
        System.out.println(blockingQueue.poll(3, TimeUnit.SECONDS));//null
    }
}
