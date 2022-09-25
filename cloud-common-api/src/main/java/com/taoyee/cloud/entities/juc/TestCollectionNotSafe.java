package com.taoyee.cloud.entities.juc;
import cn.hutool.core.lang.UUID;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
/**
 * @description: 集合类不安全的解决方法 Concurrent Modification Exception
 * @author: taoyee
 * @date: 2022-09-18 10:16
 * @version: 1.0
 */
public class TestCollectionNotSafe {
    public static void main(String[] args) {
        //testListNotSafe();
        //testSetNotSafe();
        testMapNotSafe();
    }
    private static void testMapNotSafe() {
        //Map<Integer,String > map = new HashMap<Integer,String >();//java.util.ConcurrentModificationException,解决方法有以下两种
        //Map<Integer,String > map = Collections.synchronizedMap(new HashMap<Integer,String>());//方法1
        Map<Integer,String >  map = new ConcurrentHashMap<Integer,String>();//方法2
        for (int i = 0; i < 50; i++) {
            int finalI = i;
            new Thread(() -> {
                map.put(finalI,UUID.randomUUID().toString().substring(0, 8));
                System.out.println("map: "+map);
            }, "线程" + String.valueOf(i)).start();

         }
    }
    private static void testSetNotSafe() {
        //Set<String > set = new HashSet<String >();//java.util.ConcurrentModificationException,解决方法有以下两种
        //Set<String > set = Collections.synchronizedSet(new HashSet<String >());//方法1
        Set<String> set = new CopyOnWriteArraySet<String>();//方法2
        for (int i = 0; i < 500; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println("set: "+set);
            }, "线程" + String.valueOf(i)).start();
        }
    }
    private static void testListNotSafe() {
        //List<String> ls = new ArrayList<String>();//java.util.ConcurrentModificationException,解决方法有以下三种
        //List<String> ls = new Vector<String>();//方法1
        //List<String > ls = Collections.synchronizedList(new ArrayList<String >());//方法2
        List<String> ls = new CopyOnWriteArrayList<String>();//方法3
        for (int i = 0; i < 200; i++) {
            new Thread(() -> {
                ls.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println("list: "+ls);
            }, "线程" + String.valueOf(i)).start();
        }
    }

}
