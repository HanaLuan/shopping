package com.quanweng.shopping.service.impl;

import com.quanweng.shopping.mapper.TranslateMapper;
import com.quanweng.shopping.pojo.Translate;
import com.quanweng.shopping.service.TranslateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TranslateServiceImpl implements TranslateService {
    @Autowired
    private TranslateMapper translateMapper;

    @Override
    public List<Translate> getTranslation(String text) {
        return translateMapper.getTranslation(text);
    }

    @Override
    public void createTranslation(Translate translate) {
        if (translateMapper.getTranslation(translate.getText()).isEmpty()) {
            translate.setCreateTime(LocalDateTime.now());
            translate.setUpdateTime(LocalDateTime.now());
            translateMapper.createTranslation(translate);
        }
    }

    @Override
    public void updateTranslation(Translate translate) {
        translate.setUpdateTime(LocalDateTime.now());
        translateMapper.updateTranslation(translate);
    }

    @Override
    public void deleteTranslation(String text) {
        translateMapper.deleteTranslation(text);
    }
}
