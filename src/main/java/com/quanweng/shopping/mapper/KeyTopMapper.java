package com.quanweng.shopping.mapper;

import com.quanweng.shopping.pojo.GoodsTop;
import com.quanweng.shopping.pojo.KeyTop;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.wltea.analyzer.core.Lexeme;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface KeyTopMapper {

    KeyTop findTheKeyTop(String keyWord);

    void createKeyTop(KeyTop createKeyTop);

    void updateKeyTop(KeyTop keyTop);

    List<KeyTop> getKeyTop();

    List<KeyTop> getKeyTopLimit(LocalDate startTime, LocalDate endTime);

    @Select("select count(*) from key_top_table")
    Integer getKeyTopCount();
}
