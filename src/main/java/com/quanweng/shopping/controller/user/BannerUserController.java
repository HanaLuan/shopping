package com.quanweng.shopping.controller.user;

import com.quanweng.shopping.pojo.Banner;
import com.quanweng.shopping.pojo.common.Result;
import com.quanweng.shopping.service.BannerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class BannerUserController {
    @Autowired
    private BannerService bannerService;

    @GetMapping("/banner")
    private Result getAllBanner(){
        List<Banner> bannerList = bannerService.getAllBanner();
        return Result.success(bannerList);
    }

    @GetMapping("/banner/{type}")
    private Result getBannerByType(@PathVariable Integer type){
        List<Banner> bannerList = bannerService.getBannerByType(type);
        return Result.success(bannerList);
    }





}
