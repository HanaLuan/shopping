package com.quanweng.shopping.controller.admin;

import com.quanweng.shopping.pojo.GoodsTop;
import com.quanweng.shopping.pojo.KeyTop;
import com.quanweng.shopping.pojo.NameTop;
import com.quanweng.shopping.pojo.common.Result;
import com.quanweng.shopping.service.AnalyzerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin")
public class AnalyzerController {
    @Autowired
    private AnalyzerService analyzerService;

    @GetMapping("/goodsTop")
    private Result GetGoodsTop(){
        List<GoodsTop> goodsTops = analyzerService.getGoodsTop();
        return Result.success(goodsTops);
    }

    @GetMapping("/namesTop")
    private Result GetNamesTop(){
        List<NameTop> nameTops = analyzerService.getNameTop();
        return Result.success(nameTops);
    }

    @GetMapping("/keyTop")
    private Result GetKeyTop(){
        List<KeyTop> keyTops = analyzerService.getKeyTop();
        return Result.success(keyTops);
    }

    @GetMapping("/goodsTopLimit")
    private Result GetGoodsTopLimit(@RequestParam(defaultValue = "1") Integer pages,
                                    @RequestParam(defaultValue = "100") Integer size,
                                    @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startTime,
                                    @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endTime){
        List<GoodsTop> goodsTops = analyzerService.getGoodsTopLimit(pages,size,startTime,endTime);
        return Result.success(goodsTops);
    }

    @GetMapping("/namesTopLimit")
    private Result GetNamesTopLimit(@RequestParam(defaultValue = "1") Integer pages,
                                    @RequestParam(defaultValue = "100") Integer size,
                                    @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startTime,
                                    @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endTime){
        List<NameTop> nameTops = analyzerService.getNameTopLimit(pages,size,startTime,endTime);
        return Result.success(nameTops);
    }

    @GetMapping("/keysTopLimit")
    private Result GetKeyTopLimit(@RequestParam(defaultValue = "1") Integer pages,
                                    @RequestParam(defaultValue = "100") Integer size,
                                    @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startTime,
                                    @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endTime){
        List<KeyTop> keyTops = analyzerService.getKeyTopLimit(pages,size,startTime,endTime);
        return Result.success(keyTops);
    }
}
