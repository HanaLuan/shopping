package com.quanweng.shopping.mapper;

import com.quanweng.shopping.pojo.NoteImg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NoteImgMapper {

    @Select("select * from note_img_table where order_id = #{id}")
    List<NoteImg> getNoteImg(Long id);

    void createNoteImg(NoteImg noteImg);

    void updateNoteImg(NoteImg noteImg);

    void deleteNoteImg(Long id);
}
