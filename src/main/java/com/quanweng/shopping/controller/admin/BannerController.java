package com.quanweng.shopping.controller.admin;

import com.quanweng.shopping.pojo.Banner;
import com.quanweng.shopping.pojo.common.Result;
import com.quanweng.shopping.service.BannerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin")
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @GetMapping("/banner")
    private Result getAllBanner(){
        List<Banner> bannerList = bannerService.getAllBanner();
        log.info("查询全部banner图:{}",bannerList);
        return Result.success(bannerList);
    }

    @GetMapping("/bannerByType/{type}")
    private Result getBannerByType(@PathVariable Integer type){
        List<Banner> bannerList = bannerService.getBannerByType(type);
        log.info("查询type{}的banner图:{}",type,bannerList);
        return Result.success(bannerList);
    }

    @PostMapping("/banner")
    private Result createBanner(@RequestBody Banner banner){
        bannerService.createBanner(banner);
        log.info("新增banner:{}",banner);
        return Result.success();
    }

    @PutMapping("/banner")
    private Result updateBanner(@RequestBody Banner banner){
        bannerService.updateBanner(banner);
        log.info("更新banner:{}",banner);
        return Result.success();
    }

    @DeleteMapping("/banner/{id}")
    private Result deleteBanner(@PathVariable Long id){
        bannerService.deleteBanner(id);
        log.info("删除banner:{}",id);
        return Result.success();
    }


}
