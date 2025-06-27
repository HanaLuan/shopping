package com.quanweng.shopping;

import com.google.zxing.WriterException;
import com.quanweng.shopping.utils.BarcodeUtils;
import com.quanweng.shopping.utils.QRCodeUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

@SpringBootTest
class ShoppingApplicationTests {

    @Autowired
    QRCodeUtils qrCodeUtils;

    @Test
    void contextLoads() {
    }

    @Test
    void Test1() throws Exception {
        qrCodeUtils.generateQRCode("https://github.com");
        System.out.println("生成图片成功");
    }


    @Test
    public void test(){

    }

    @Test
    public void test3(){

    }

    @Test
    public void test4(){}

}
