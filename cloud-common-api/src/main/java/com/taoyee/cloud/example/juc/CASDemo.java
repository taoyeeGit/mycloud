package com.taoyee.cloud.example.juc;


import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CASDemo {
    public static void main(String[] args) {
        //cas();//ctrl+alt+m
        //tichuMethod();
    }
    private static void cas() {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        atomicInteger.compareAndSet(5, 2019);
        System.out.println(atomicInteger.compareAndSet(5, 2019) + "\t 当前值为：  " + atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 1024) + "\t 当前值为：  " + atomicInteger.get());
    }

    private static void tichuMethod() {
        List aa = new CopyOnWriteArrayList<>();
        List bb = new ArrayList();
        List cc = Collections.synchronizedList(new ArrayList());

        aa.add("1");
        aa.add("2");
        aa.add("3");
        aa.add("4");
    }
}