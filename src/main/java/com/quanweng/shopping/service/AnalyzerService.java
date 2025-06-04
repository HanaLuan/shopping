package com.quanweng.shopping.service;

import com.quanweng.shopping.pojo.GoodsTop;
import com.quanweng.shopping.pojo.KeyTop;
import com.quanweng.shopping.pojo.NameTop;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface AnalyzerService {
    List<GoodsTop> getGoodsTop();

    List<NameTop> getNameTop();

    List<KeyTop> getKeyTop();

    List<GoodsTop> getGoodsTopLimit(Integer pages, Integer size, LocalDate startTime, LocalDate endTime);

    List<NameTop> getNameTopLimit(Integer pages, Integer size, LocalDate startTime, LocalDate endTime);

    List<KeyTop> getKeyTopLimit(Integer pages, Integer size, LocalDate startTime, LocalDate endTime);

    Integer getGoodsTopCount();

    Integer getNameTopCount();

    Integer getKeyTopCount();
}
