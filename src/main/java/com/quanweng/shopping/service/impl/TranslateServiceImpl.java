package com.quanweng.shopping.service.impl;

import com.quanweng.shopping.mapper.TranslateMapper;
import com.quanweng.shopping.pojo.Translate;
import com.quanweng.shopping.pojo.VO.TranslateResponseVO;
import com.quanweng.shopping.service.TranslateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class TranslateServiceImpl implements TranslateService {
    @Autowired
    private TranslateMapper translateMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String CACHE_KEY_PREFIX = "shopping:translate:";
    private static final long CACHE_TTL = 60; // 缓存过期时间：60分钟

    @Override
    @Cacheable(value = "translate", key = "#textList")
    public List<List<Translate>> getTranslation(List<String> textList) {
        List<List<Translate>> list = new ArrayList<>();
        for(String text : textList){
           List<Translate> translateList = translateMapper.getTranslation(text);
           list.add(translateList);
        }
        return list;
    }

    @Override
    public TranslateResponseVO getTranslationWithMetadata(String text) {
        long startTime = System.nanoTime();
        String cacheKey = CACHE_KEY_PREFIX + text;

        try {
            // 首先尝试从Redis缓存获取
            @SuppressWarnings("unchecked")
            List<Translate> cachedData = (List<Translate>) redisTemplate.opsForValue().get(cacheKey);

            if (cachedData != null) {
                // 缓存命中
                long endTime = System.nanoTime();
                double queryTime = (endTime - startTime) / 1_000_000.0; // 转换为毫秒
                log.info("缓存命中 - 查询文字: {}, 耗时: {}ms", text, queryTime);
                return TranslateResponseVO.fromCacheServer(cachedData, queryTime);
            } else {
                // 缓存未命中，从数据库查询
                List<Translate> dbData = translateMapper.getTranslation(text);
                long endTime = System.nanoTime();
                double queryTime = (endTime - startTime) / 1_000_000.0; // 转换为毫秒

                // 将结果存入缓存
                if (!dbData.isEmpty()) {
                    redisTemplate.opsForValue().set(cacheKey, dbData, CACHE_TTL, TimeUnit.MINUTES);
                    log.info("数据库查询并缓存 - 查询文字: {}, 结果数量: {}, 耗时: {}ms", text, dbData.size(), queryTime);
                } else {
                    log.info("数据库查询无结果 - 查询文字: {}, 耗时: {}ms", text, queryTime);
                }

                return TranslateResponseVO.fromMainServer(dbData, queryTime);
            }
        } catch (Exception e) {
            // 如果Redis出现问题，直接查询数据库
            log.warn("Redis缓存异常，直接查询数据库 - 查询文字: {}, 错误: {}", text, e.getMessage());
            List<Translate> dbData = translateMapper.getTranslation(text);
            long endTime = System.nanoTime();
            double queryTime = (endTime - startTime) / 1_000_000.0;
            return TranslateResponseVO.fromMainServer(dbData, queryTime);
        }
    }

    @Override
    @CacheEvict(value = "translate", key = "#translate.text")
    public void createTranslation(Translate translate) {
        if (translateMapper.getTranslation(translate.getText()).isEmpty()) {
            translate.setCreateTime(LocalDateTime.now());
            translate.setUpdateTime(LocalDateTime.now());
            translateMapper.createTranslation(translate);

            // 手动清除对应的缓存
            String cacheKey = CACHE_KEY_PREFIX + translate.getText();
            redisTemplate.delete(cacheKey);
            log.info("创建翻译并清除缓存 - 文字: {}", translate.getText());
        }
    }

    @Override
    @CacheEvict(value = "translate", key = "#translate.text")
    public void updateTranslation(Translate translate) {
        translate.setUpdateTime(LocalDateTime.now());
        translateMapper.updateTranslation(translate);

        // 手动清除对应的缓存
        String cacheKey = CACHE_KEY_PREFIX + translate.getText();
        redisTemplate.delete(cacheKey);
        log.info("更新翻译并清除缓存 - 文字: {}", translate.getText());
    }

    @Override
    @CacheEvict(value = "translate", key = "#text")
    public void deleteTranslation(String text) {
        translateMapper.deleteTranslation(text);

        // 手动清除对应的缓存
        String cacheKey = CACHE_KEY_PREFIX + text;
        redisTemplate.delete(cacheKey);
        log.info("删除翻译并清除缓存 - 文字: {}", text);
    }
}
