package com.quanweng.shopping.pojo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Translate implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String text;
    private String lang;
    private String translateText;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
