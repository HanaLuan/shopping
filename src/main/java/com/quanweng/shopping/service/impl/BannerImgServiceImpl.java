package com.quanweng.shopping.service.impl;

import com.quanweng.shopping.mapper.BannerImgMapper;
import com.quanweng.shopping.pojo.BannerImg;
import com.quanweng.shopping.service.BannerImgService;
import com.quanweng.shopping.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BannerImgServiceImpl implements BannerImgService {
    @Autowired
    private BannerImgMapper bannerImgMapper;


    @Override
    public List<BannerImg> getAllBannerById(Long bannerId) {
        return bannerImgMapper.getAllBannerById(bannerId);
    }

    @Override
    public void createBannerImg(List<BannerImg> bannerImgList) {
        for(BannerImg bannerImg:bannerImgList){
            bannerImg.setCreateTime(LocalDateTime.now());
            bannerImg.setUpdateTime(LocalDateTime.now());
            bannerImgMapper.createBannerImg(bannerImg);
        }
    }

    @Override
    public void updateBannerImg(BannerImg bannerImg) {
        bannerImg.setUpdateTime(LocalDateTime.now());
        bannerImgMapper.updateBannerImg(bannerImg);
    }

    @Override
    public void deleteBannerImg(Long id) {
        bannerImgMapper.deleteBannerImg(id);
    }


}
