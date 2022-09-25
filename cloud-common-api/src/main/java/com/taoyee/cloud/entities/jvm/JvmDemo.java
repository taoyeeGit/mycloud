package com.taoyee.cloud.entities.jvm;

import java.nio.ByteBuffer;
import java.util.ArrayList;

/**
 * @description: JAVA虚拟机
 * @author: taoyee
 * @date: 2022-09-21 07:49
 * @version:
 */
public class JvmDemo {
    public static void main(String[] args) {
        /*long totalMemory=Runtime.getRuntime().totalMemory();
        System.out.println("totalMemory= \t "+totalMemory/1024/1024);
        long freeMemory=Runtime.getRuntime().freeMemory();
        System.out.println("freeMemory=\t"+freeMemory/1024/1024);
        long maxMemory=Runtime.getRuntime().maxMemory();
        System.out.println("maxMemory=\t" +maxMemory/1024/1024);
        */
        //JVM参数设置 -Xms10m  -Xmx20m  -XX:+PrintGCDetails
        System.out.println("HelloGC");
        //aaaMethod();//递归调用：java.lang.StackOverflowError 栈
        //byte[] byarr=new byte[50*1024*1024];//堆，创建大对象。 java.lang.OutOfMemoryError: Java heap space
        //oomlimit();//java.lang.OutOfMemoryError: GC overhead limit exceeded
        //oomDirect();//考点NIO：java.lang.OutOfMemoryError: Direct buffer memory
        ddd();//java.lang.OutofMemoryError:unable to create new native thread，linux环境用户线程限制1024，超过就报此错
        //oom:metaspace
        System.out.println("HelloGC");
    }
    private static void ddd() {
        for (int i = 1; ; i++) {
            System.out.println("********i = " + i);
            new Thread(() -> {
                try {
                    Thread.sleep(Integer.MAX_VALUE);//
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "" + i).start();
        }
    }
    private static void aaaMethod() {  aaaMethod(); }

    private static void oomlimit() {
        int i = 0;
        ArrayList list = new ArrayList<>();
        try {
            while (true) {
                list.add(String.valueOf(++i).intern());
            }
        } catch (Exception e) {
            System.out.println(i);
            e.printStackTrace();
        }
    }
    private static void oomDirect() {
        System.out.println("配置的maxDirectMemory:" + (sun.misc.VM.maxDirectMemory() / (double) 1024 / 1024) + "MB");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } //-XX:MaxDirectMemorySize=5m
        ByteBuffer bb = ByteBuffer.allocate(6 * 1024 * 1024);//不会报错
        ByteBuffer cc = ByteBuffer.allocateDirect(6 * 1024 * 1024);//java.lang.OutOfMemoryError: Direct buffer memory
    }
}