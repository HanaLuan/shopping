package com.quanweng.shopping.service.impl;

import com.quanweng.shopping.mapper.PicRecognizeMapper;
import com.quanweng.shopping.service.PicRecognizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PicRecognizeServiceImpl implements PicRecognizeService {
    @Autowired
    private PicRecognizeMapper picRecognizeMapper;
}
