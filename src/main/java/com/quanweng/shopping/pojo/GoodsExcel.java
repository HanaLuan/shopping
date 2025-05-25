package com.quanweng.shopping.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GoodsExcel {
    @ExcelProperty("商品名")
    private String goodsName;
    @ExcelProperty("商品名(英文)")
    private String goodsNameEn;
    @ExcelProperty("商品大小")
    private String goodsSize;
    @ExcelProperty("商品详情")
    private String goodsDetail;
    @ExcelProperty("商品详情(英文)")
    private String goodsDetailEn;
    @ExcelProperty("商品权重")
    private Integer goodsWeight;
    @ExcelProperty("商品标签内容")
    private String goodsTip;
    @ExcelProperty("商品标签内容(英文)")
    private String goodsTipEn;

}
