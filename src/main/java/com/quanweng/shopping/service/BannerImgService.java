package com.quanweng.shopping.service;

import com.quanweng.shopping.pojo.BannerImg;

import java.util.List;

public interface BannerImgService {
    List<BannerImg> getAllBannerById(Long bannerId);

    void createBannerImg(List<BannerImg> bannerImgList);

    void updateBannerImg(BannerImg bannerImg);

    void deleteBannerImg(Long id);
}
