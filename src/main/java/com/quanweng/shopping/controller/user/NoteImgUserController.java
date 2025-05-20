package com.quanweng.shopping.controller.user;

import com.quanweng.shopping.pojo.NoteImg;
import com.quanweng.shopping.pojo.common.Result;
import com.quanweng.shopping.service.NoteImgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class NoteImgUserController {
    @Autowired
    private NoteImgService noteImgService;

    @GetMapping("/noteImg/{id}")
    private Result getNoteImg(@PathVariable Long id){
        List<NoteImg> noteImgList = noteImgService.getNoteImg(id);
        return Result.success(noteImgList);
    }

    @PostMapping("/noteImg")
    private Result createNoteImg(@RequestBody List<NoteImg> noteImgList){
        noteImgService.createNoteImg(noteImgList);
        return Result.success();
    }

    @PutMapping("/noteImg")
    private Result updateNoteImg(@RequestBody List<NoteImg> noteImgList){
        noteImgService.updateNoteImg(noteImgList);
        return Result.success();
    }

    @DeleteMapping("/noteImg/{id}")
    private Result deleteNoteImg(@PathVariable Long id){
        noteImgService.deleteNoteImg(id);
        return Result.success();
    }


}
