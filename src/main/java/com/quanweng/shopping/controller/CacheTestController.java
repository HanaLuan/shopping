package com.quanweng.shopping.controller;

import com.quanweng.shopping.pojo.common.Result;
import com.quanweng.shopping.pojo.VO.TranslateResponseVO;
import com.quanweng.shopping.service.TranslateService;
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
    
    @Autowired
    private TranslateService translateService;

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

    @GetMapping("/clear-all-translate")
    public Result clearAllTranslateCache() {
        try {
            // 清除所有可能的翻译缓存键格式
            Set<String> keys1 = redisTemplate.keys("shopping:translate:*");
            Set<String> keys2 = redisTemplate.keys("translate:*");
            
            int deletedCount = 0;
            if (!keys1.isEmpty()) {
                deletedCount += redisTemplate.delete(keys1);
            }
            if (!keys2.isEmpty()) {
                deletedCount += redisTemplate.delete(keys2);
            }
            
            log.info("清除所有翻译缓存，删除了 {} 个缓存键", deletedCount);
            return Result.success("成功清除 " + deletedCount + " 个翻译缓存");
        } catch (Exception e) {
            log.error("清除所有翻译缓存失败", e);
            return Result.error("清除所有翻译缓存失败: " + e.getMessage());
        }
    }

    @GetMapping("/info")
    public Result getCacheInfo() {
        try {
            // 获取所有translate相关的缓存键 - 支持多种可能的键格式
            Set<String> translateKeys1 = redisTemplate.keys("shopping:translate:*");
            Set<String> translateKeys2 = redisTemplate.keys("translate:*");
            Set<String> allKeys = redisTemplate.keys("*");
            
            StringBuilder info = new StringBuilder();
            info.append("shopping:translate:* 格式键数量: ").append(translateKeys1.size()).append(", 键列表: ").append(translateKeys1).append("\n");
            info.append("translate:* 格式键数量: ").append(translateKeys2.size()).append(", 键列表: ").append(translateKeys2).append("\n");
            info.append("所有缓存键数量: ").append(allKeys.size()).append(", 键列表: ").append(allKeys);
            
            return Result.success(info.toString());
        } catch (Exception e) {
            log.error("获取缓存信息失败", e);
            return Result.error("获取缓存信息失败: " + e.getMessage());
        }
    }
    
    @GetMapping("/test-translate")
    public Result testTranslatePerformance(@RequestParam String text, 
                                         @RequestParam(defaultValue = "3") int times) {
        try {
            StringBuilder result = new StringBuilder();
            result.append("翻译性能测试 - 查询文字: ").append(text).append("\n\n");
            
            for (int i = 1; i <= times; i++) {
                TranslateResponseVO response = translateService.getTranslationWithMetadata(text);
                result.append("第").append(i).append("次查询: ")
                      .append("数据源=").append(response.getDataSource())
                      .append(", 查询时间=").append(String.format("%.6f", response.getQueryUsageTime())).append("ms")
                      .append(", 结果数量=").append(response.getData() != null ? response.getData().size() : 0)
                      .append("\n");
                
                // 短暂延迟避免测试过快
                Thread.sleep(100);
            }
            
            return Result.success(result.toString());
        } catch (Exception e) {
            log.error("翻译性能测试失败", e);
            return Result.error("翻译性能测试失败: " + e.getMessage());
        }
    }
} 