package com.taoyee.cloud.entities.juc;

import com.taoyee.cloud.entities.Payment;
import sun.security.jgss.SunProvider;

import java.util.function.Supplier;

import static cn.hutool.cron.CronUtil.start;

public class LambdaDemo {
    public static void main(String[] args) {
        //getlambd();
    }

    private static void getlambd() {
        LambdaDemo ld = new LambdaDemo();
        // 类型声明
        MathOperation ddd = (int a, int b) -> a + b;
        System.out.println(ddd.operation(2, 3));
        // 不用类型声明
        MathOperation subtraction = (a, b) -> a - b;
        // 大括号中的返回语句
        MathOperation multiplication = (int a, int b) -> {
            return a * b;
        };
        // 没有大括号及返回语句
        MathOperation division = (int a, int b) -> a / b;
        System.out.println("10 + 5 = " + ld.operate(10, 5, ddd));
        System.out.println("10 - 5 = " + ld.operate(10, 5, subtraction));
        System.out.println("10 x 5 = " + ld.operate(10, 5, multiplication));
        System.out.println("10 / 5 = " + ld.operate(10, 5, division));
        // 不用括号
        GreetingService greetService1 = message ->
                System.out.println("Hello " + message);
        // 用括号
        GreetingService greetService2 = (message) ->
                System.out.println("Hello " + message);
        greetService1.sayMessage("Runoob");
        greetService2.sayMessage("Google");
    }

    interface MathOperation {
        int operation(int a, int b);
    }

    interface GreetingService {
        void sayMessage(String message);
    }

    private int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }
}