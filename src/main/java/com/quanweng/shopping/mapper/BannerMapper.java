package com.quanweng.shopping.mapper;

import com.quanweng.shopping.pojo.Banner;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BannerMapper {
    @Select("select * from banner_table")
    List<Banner> getAllBanner();

    @Select("select * from banner_table where banner_type = #{type}")
    List<Banner> getBannerByType(Integer type);

    void createBanner(Banner banner);

    void updateBanner(Banner banner);

    void deleteBanner(Long id);
}
