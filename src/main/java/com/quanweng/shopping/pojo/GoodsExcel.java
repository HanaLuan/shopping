package com.quanweng.shopping.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GoodsExcel {
    @ExcelProperty("product_code")
    private String productCode;
    @ExcelProperty("chinese_name")
    private String chineseName;
    @ExcelProperty("english_name")
    private String englishName;
    @ExcelProperty("composition")
    private String composition;
    @ExcelProperty("width")
    private String width;
    @ExcelProperty("weight")
    private Integer weight;
    @ExcelProperty("product_type")
    private String productType;
    @ExcelProperty("img")
    private String img;

}
