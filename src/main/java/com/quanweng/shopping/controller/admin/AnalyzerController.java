package com.quanweng.shopping.controller.admin;

import com.quanweng.shopping.pojo.GoodsTop;
import com.quanweng.shopping.pojo.common.Result;
import com.quanweng.shopping.service.AnalyzerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
