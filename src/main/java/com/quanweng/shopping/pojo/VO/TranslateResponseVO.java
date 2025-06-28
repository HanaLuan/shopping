package com.quanweng.shopping.pojo.VO;

import com.quanweng.shopping.pojo.Translate;
import lombok.Data;

import java.util.List;

@Data
public class TranslateResponseVO {
    private List<Translate> data;
    private String dataSource; // "mainServer" 或 "cacheServer"
    private Double queryUsageTime; // 查询时间，单位ms
    
    public TranslateResponseVO(List<Translate> data, String dataSource, Double queryUsageTime) {
        this.data = data;
        this.dataSource = dataSource;
        this.queryUsageTime = queryUsageTime;
    }
    
    public static TranslateResponseVO fromMainServer(List<Translate> data, Double queryUsageTime) {
        return new TranslateResponseVO(data, "mainServer", queryUsageTime);
    }
    
    public static TranslateResponseVO fromCacheServer(List<Translate> data, Double queryUsageTime) {
        return new TranslateResponseVO(data, "cacheServer", queryUsageTime);
    }
} 