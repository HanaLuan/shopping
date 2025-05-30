package com.quanweng.shopping.mapper;

import com.quanweng.shopping.pojo.NameTop;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface NameTopMapper {

    NameTop findTheNameTop(String keyWord);

    void createNameTop(NameTop createNameTop);

    void updateNameTop(NameTop nameTop);

    List<NameTop> getNameTop();

    List<NameTop> getNameTopLimit(LocalDate startTime, LocalDate endTime);
}
