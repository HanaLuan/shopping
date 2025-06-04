package com.quanweng.shopping.pojo.VO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NameTopVO {
    private Long id;
    private String name;
    private Long nameCount;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer total;
}
