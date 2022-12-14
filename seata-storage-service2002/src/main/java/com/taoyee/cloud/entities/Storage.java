package com.taoyee.cloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Storage {
    private Long id;
    private Long productId;
    private Long userId;
    private Integer total;
    private Integer used;
    private Integer residue;
}
