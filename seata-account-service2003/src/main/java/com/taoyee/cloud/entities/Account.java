package com.taoyee.cloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Account
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private Long id;
    private Long userId;//用户id
    private BigDecimal total;//总额度
    private BigDecimal used;//已用余额
    private BigDecimal residue;// 剩余可用额度
}
