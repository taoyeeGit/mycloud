package com.taoyee.cloud.entities.juc;

import java.sql.Array;
import java.sql.Connection;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class CASDemo {
    public static void main(String[] args) {
        //cas();
        //tichuMethod();
        List list= Arrays.asList("a","b","c");
        //list.add("d");// java.lang.UnsupportedOperationException
        list.forEach(System.out::println);
        try {
            TimeUnit.MILLISECONDS.sleep(50000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static void cas() {
        AtomicInteger atomicInteger=new AtomicInteger(5);
        atomicInteger.compareAndSet(5,2019);
        System.out.println(atomicInteger.compareAndSet(5,2019)+"\t 当前值为：  "+atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5,1024)+"\t 当前值为：  "+atomicInteger.get());
    }
    private static void  tichuMethod() { //ctrl+alt+m
        List aa=new CopyOnWriteArrayList<>();
        List bb=new Vector();
        List cc= Collections.synchronizedList(new ArrayList());

        aa.add("1");
        aa.add("2");
        aa.add("3");
        aa.add("4");
    }


}
