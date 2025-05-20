package com.quanweng.shopping.service;

import com.quanweng.shopping.pojo.NoteImg;
import org.springframework.stereotype.Service;

import java.util.List;

public interface NoteImgService {
    List<NoteImg> getNoteImg(Long id);

    void createNoteImg(List<NoteImg> noteImgList);

    void updateNoteImg(List<NoteImg> noteImgLIst);

    void deleteNoteImg(Long id);
}
