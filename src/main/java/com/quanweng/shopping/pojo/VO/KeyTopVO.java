package com.quanweng.shopping.pojo.VO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class KeyTopVO {
    private Long id;
    private String keyWord;
    private Long keyCount;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer total;
}
