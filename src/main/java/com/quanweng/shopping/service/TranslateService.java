package com.quanweng.shopping.service;

import com.quanweng.shopping.pojo.Translate;

import java.util.List;

public interface TranslateService {
    List<Translate> getTranslation(String text);

    void createTranslation(Translate translate);

    void updateTranslation(Translate translate);

    void deleteTranslation(String text);
}
