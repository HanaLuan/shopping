package com.quanweng.shopping.mapper;

import com.quanweng.shopping.pojo.BannerImg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BannerImgMapper {
    @Select("select * from banner_img_table where banner_id = #{bannerId}")
    List<BannerImg> getAllBannerById(Long bannerId);

    void createBannerImg(BannerImg bannerImg);

    void updateBannerImg(BannerImg bannerImg);

    void deleteBannerImg(Long id);
}
