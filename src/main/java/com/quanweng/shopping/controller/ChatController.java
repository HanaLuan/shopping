package com.quanweng.shopping.controller;

import com.quanweng.shopping.pojo.common.Result;
import com.quanweng.shopping.service.impl.SiliconFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    @Autowired
    private SiliconFlowService siliconFlowService;

    @PostMapping("/chat")
    public String chat(
            @RequestParam String imageUrl) {
        return siliconFlowService.chatWithImage(imageUrl);
    }
}