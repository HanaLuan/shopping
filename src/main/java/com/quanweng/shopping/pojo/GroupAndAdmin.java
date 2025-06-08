package com.quanweng.shopping.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GroupAndAdmin {
    private Long id;
    private Long groupId;
    private Long adminId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
