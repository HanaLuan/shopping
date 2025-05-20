package com.quanweng.shopping.controller;

import com.quanweng.shopping.pojo.common.Result;
import com.quanweng.shopping.pojo.Translate;
import com.quanweng.shopping.service.TranslateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class TranslateController {
    @Autowired
    private TranslateService translateService;

    @GetMapping("/translate")
    private Result getTranslation(@RequestParam String text){
        List<Translate> translateList = translateService.getTranslation(text);
        log.info("查询文字{}对应的翻译:{}",text,translateList);
        return Result.success(translateList);
    }

    @PostMapping("/translate")
    private Result createTranslation(@RequestBody Translate translate){
        translateService.createTranslation(translate);
        log.info("新建翻译:{}",translate);
        return Result.success();
    }

    @PutMapping("/translate")
    private Result updateTranslation(@RequestBody Translate translate){
        translateService.updateTranslation(translate);
        log.info("更新翻译:{}",translate);
        return Result.success();
    }

    @DeleteMapping("/translate")
    private Result deleteTranslation(@RequestParam String text){
        translateService.deleteTranslation(text);
        log.info("根据字段{}删除翻译",text);
        return Result.success();
    }
}
