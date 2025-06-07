package com.quanweng.shopping.controller.admin;

import com.quanweng.shopping.pojo.Banner;
import com.quanweng.shopping.pojo.BannerImg;
import com.quanweng.shopping.pojo.common.Result;
import com.quanweng.shopping.service.BannerImgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin")
public class BannerImgController {
    @Autowired
    private BannerImgService bannerImgService;

    @GetMapping("/bannerImg/{bannerId}")
    private Result getAllBannerById(@PathVariable Long bannerId){
        List<BannerImg> bannerImgList = bannerImgService.getAllBannerById(bannerId);
        return Result.success(bannerImgList);
    }

    @PostMapping("/bannerImg")
    private Result createBannerImg(@RequestBody List<BannerImg> bannerImgList){
        bannerImgService.createBannerImg(bannerImgList);
        return Result.success();
    }

    @PutMapping("/bannerImg")
    private Result updateBannerImg(@RequestBody BannerImg bannerImg){
        bannerImgService.updateBannerImg(bannerImg);
        return Result.success();
    }

    @DeleteMapping("/bannerImg/{id}")
    private Result deleteBannerImg(@PathVariable Long id){
        bannerImgService.deleteBannerImg(id);
        return Result.success();
    }
}
