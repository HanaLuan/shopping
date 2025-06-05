package com.quanweng.shopping.controller.admin;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quanweng.shopping.pojo.UserTraceReqInfo;
import com.quanweng.shopping.pojo.common.Result;
import com.quanweng.shopping.service.UserTraceReqInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/admin/userTraceReqInfo")
public class UserTraceReqInfoController {

    @Autowired
    private UserTraceReqInfoService userTraceReqInfoService;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/{requestSessionID}")
    public Result getByRequestSessionID(@PathVariable String requestSessionID) {
        log.info("查询请求 {} 的附属信息", requestSessionID);
        UserTraceReqInfo info = userTraceReqInfoService.getByRequestSessionID(requestSessionID);

        try {
            info.getReqHeader();
            String jsonText = new String(Base64.getDecoder().decode(info.getReqHeader()), StandardCharsets.UTF_8);
            Map<String, String> map = objectMapper.readValue(jsonText, new TypeReference<>() {
            });
            String pretty = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
            info.setReqHeader(pretty);
        } catch (Exception e) {
            log.warn("無法解碼 reqHeader：{}", e.getMessage());
            return Result.error("NO_DATA");
        }

        return Result.success(info);
    }
}
