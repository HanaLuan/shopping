package com.quanweng.shopping.controller;

import com.quanweng.shopping.pojo.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/cache")
public class CacheTestController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/keys")
    public Result getCacheKeys(@RequestParam(defaultValue = "*") String pattern) {
        try {
            Set<String> keys = redisTemplate.keys(pattern);
            log.info("查询到Redis缓存键: {}", keys);
            return Result.success(keys);
        } catch (Exception e) {
            log.error("获取缓存键失败", e);
            return Result.error("获取缓存键失败: " + e.getMessage());
        }
    }

    @GetMapping("/clear")
    public Result clearCache(@RequestParam String key) {
        try {
            Boolean deleted = redisTemplate.delete(key);
            log.info("删除缓存键 {}: {}", key, deleted);
            return Result.success("缓存清除" + (deleted ? "成功" : "失败"));
        } catch (Exception e) {
            log.error("清除缓存失败", e);
            return Result.error("清除缓存失败: " + e.getMessage());
        }
    }

    @GetMapping("/info")
    public Result getCacheInfo() {
        try {
            // 获取所有translate相关的缓存键
            Set<String> translateKeys = redisTemplate.keys("shopping:translate:*");
            return Result.success("翻译缓存键数量: " + translateKeys.size() + ", 键列表: " + translateKeys);
        } catch (Exception e) {
            log.error("获取缓存信息失败", e);
            return Result.error("获取缓存信息失败: " + e.getMessage());
        }
    }
} 