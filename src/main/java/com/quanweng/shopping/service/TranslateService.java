package com.quanweng.shopping.service;

import com.quanweng.shopping.pojo.Translate;
import com.quanweng.shopping.pojo.VO.TranslateResponseVO;

import java.util.List;

public interface TranslateService {
    List<List<Translate>> getTranslation(List<String> text);

    TranslateResponseVO getTranslationWithMetadata(String text);

    void createTranslation(Translate translate);

    void updateTranslation(Translate translate);

    void deleteTranslation(String text);
}
