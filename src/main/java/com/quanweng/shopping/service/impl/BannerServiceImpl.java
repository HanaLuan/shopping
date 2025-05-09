package com.quanweng.shopping.service.impl;

import com.quanweng.shopping.mapper.BannerMapper;
import com.quanweng.shopping.pojo.Banner;
import com.quanweng.shopping.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerMapper bannerMapper;

    @Override
    public List<Banner> getAllBanner() {
        return bannerMapper.getAllBanner();
    }

    @Override
    public List<Banner> getBannerByType(Integer type) {
        return bannerMapper.getBannerByType(type);
    }

    @Override
    public void createBanner(Banner banner) {
        banner.setCreateTime(LocalDateTime.now());
        banner.setUpdateTime(LocalDateTime.now());
        bannerMapper.createBanner(banner);
    }

    @Override
    public void updateBanner(Banner banner) {
        banner.setUpdateTime(LocalDateTime.now());
        bannerMapper.updateBanner(banner);
    }

    @Override
    public void deleteBanner(Long id) {
        bannerMapper.deleteBanner(id);
    }
}
