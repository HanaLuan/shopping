package com.quanweng.shopping.controller.user;

import com.quanweng.shopping.service.PicRecognizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PicRecognizeUserController {
    @Autowired
    private PicRecognizeService picRecognizeService;


}
