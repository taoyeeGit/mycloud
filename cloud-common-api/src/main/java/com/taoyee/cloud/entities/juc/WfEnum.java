package com.taoyee.cloud.entities.juc;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 五福
 * @author: taoyee
 * @date: 2022-09-19 11:02
 * @version: 1.0
 */
@Getter
@AllArgsConstructor
public enum WfEnum {
    //长寿、富贵、康宁、好德、善终
    //ONE(1,"爱国福"),  TWO(2,"敬业福"),  THREE(3,"友善福"),  FOR(4,"和谐福"),  FIVE(0,"富强福");
    ONE(1,"长寿"),  TWO(2,"富贵"),  THREE(3,"康宁"),  FOR(4,"好德"),  FIVE(0,"善终");
    private Integer wfCode;
    private String  wfInfo;

    public static WfEnum getWfEnum(Integer index){
        for (WfEnum wf:WfEnum.values()) {
            if(wf.wfCode==index){
                return  wf;
            }
        }
        return null;
    }
}
