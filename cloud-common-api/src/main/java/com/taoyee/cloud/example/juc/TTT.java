package com.taoyee.cloud.example.juc;

import java.util.*;

/**
 * @description:
 * @author: taoyee
 * @date: 2022-11-09 15:07
 * @version:
 */
public class TTT {
    public static void main(String[] args) {
        List<String>  collection =new ArrayList();
        collection.add("123");
        collection.add("234");
        collection.add("345");
        collection.forEach(System.out::println);
        System.out.println("****************");
        Iterator iterator = collection.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println("****************");
        for (String s:collection) {
            System.out.println(s);
        }
        System.out.println("****************");
        for (int i = 0; i < collection.size(); i++) {
            System.out.println(collection.get(i));;
        }
        Collections.reverse(collection);
        System.out.println("******collection**********");
        for (int i = 0; i < collection.size(); i++) {
            System.out.println(collection.get(i));;
        }



        Map<String,String> map =new HashMap();
        map.put("Str1","String1");
        map.put("Str2","String2");
        map.put("Str3","String3");
        map.put("Str4","String4");

        System.out.println("****************" +map.size());
        for (String ss:map.values()){
            System.out.println(ss);
        }
        System.out.println("****************");
        Set ks=map.keySet();
        Iterator it=ks.iterator();
        while (it.hasNext()){
           System.out.println(it.next());
        }
        System.out.println("****************");
        Set es=map.entrySet();
        Iterator eit=es.iterator();
        while (eit.hasNext()){
           Map.Entry entry= (Map.Entry) eit.next();
           System.out.println( entry.getKey()+ "\t "+ entry.getValue());
        }
        System.out.println("****************");
        for (String entry:map.keySet()){
            String k=entry;
            String v=map.get(entry);

            System.out.println(k+"\t "+v);
        }
        System.out.println("****************");
        for (Map.Entry<String,String > entry:map.entrySet()){
            String k=entry.getKey();
            String v=entry.getValue();
            System.out.println(k+"\t "+v);
        }
    }
}