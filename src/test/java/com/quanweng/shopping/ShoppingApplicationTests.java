package com.quanweng.shopping;

import com.google.zxing.WriterException;
import com.quanweng.shopping.utils.BarcodeUtils;
import com.quanweng.shopping.utils.QRCodeUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class ShoppingApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void Test1() throws IOException, WriterException {
        QRCodeUtils.generateQRCode("https://github.com");
        System.out.println("生成图片成功");
    }

    @Test
    public void test2() throws IOException, WriterException {
        BarcodeUtils.generateCode128Barcode("https://github.com","E://barcode.png",300,300);
        System.out.println("生成条形码成功");
    }

}
