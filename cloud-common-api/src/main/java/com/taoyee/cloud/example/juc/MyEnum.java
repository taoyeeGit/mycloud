package com.taoyee.cloud.example.juc;

import lombok.*;

/**
 * @description:
 * @author: taoyee
 * @date: 2022-09-19 08:29
 * @version:
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum MyEnum {
    ONE(1, "齐国","AAA"), TWO(2, "楚国","BBB"), THREE(3, "燕国","CCC"), FOUR(4, "韩国","DDD"), FIVE(5, "赵国","EEE"), SIX(6, "魏国","FFF");

    private Integer code;
    private String msg;
    private String sd;

     public static  MyEnum  getMyEnum (Integer code) {
        for (MyEnum m : MyEnum.values()) {
            if (m.getCode() == code) {
                //System.out.println(code+"===" +m.code+"==="+m.msg+"===="+m.sd);
                return m;
            }
        }
        return null;
    }
    public static  String  getSd (Integer code) {
        for (MyEnum m : MyEnum.values()) {
            if (m.code == code) {
                return m.getSd();
            }
        }
        return null;
    }
    public static  String  getMsg (Integer code) {
        for (MyEnum m : MyEnum.values()) {
            if (m.getCode() == code) {
                return m.getMsg();
            }
        }
        return null;
    }
}
