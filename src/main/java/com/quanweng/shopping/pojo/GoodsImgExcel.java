package com.quanweng.shopping.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class GoodsImgExcel {
    @ExcelProperty("new_code")
    private String newCode;
    @ExcelProperty("old_code")
    private String oldCode;
}
