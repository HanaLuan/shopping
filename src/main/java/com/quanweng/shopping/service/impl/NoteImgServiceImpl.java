package com.quanweng.shopping.service.impl;

import com.quanweng.shopping.mapper.NoteImgMapper;
import com.quanweng.shopping.pojo.NoteImg;
import com.quanweng.shopping.service.NoteImgService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class NoteImgServiceImpl implements NoteImgService {
    @Autowired
    private NoteImgMapper noteImgMapper;

    @Override
    public List<NoteImg> getNoteImg(Long id) {
        return noteImgMapper.getNoteImg(id);
    }

    @Override
    public void createNoteImg(List<NoteImg> noteImgList) {
        for(NoteImg noteImg:noteImgList){
            noteImg.setCreateTime(LocalDateTime.now());
            noteImg.setUpdateTime(LocalDateTime.now());
            noteImgMapper.createNoteImg(noteImg);
        }
    }

    @Override
    public void updateNoteImg(List<NoteImg> noteImgList) {
        for(NoteImg noteImg:noteImgList) {
            noteImg.setUpdateTime(LocalDateTime.now());
            noteImgMapper.updateNoteImg(noteImg);
        }
    }

    @Override
    public void deleteNoteImg(Long id) {
        noteImgMapper.deleteNoteImg(id);
    }
}
