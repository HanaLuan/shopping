package com.quanweng.shopping.service;

import com.quanweng.shopping.pojo.Banner;

import java.util.List;

public interface BannerService {
    List<Banner> getAllBanner();

    List<Banner> getBannerByType(Integer type);

    void createBanner(Banner banner);

    void updateBanner(Banner banner);

    void deleteBanner(Long id);
}
