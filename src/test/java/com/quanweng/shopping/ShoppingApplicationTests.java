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
    public void test(){
        int n = 10225;
        int a = 1;
        int b = 0;
        int num = 0;
        while(n > 10){
            num = n % 10;
            n /= 10;
            a *= num;
            b += num;
        }
        a *= n;
        b += n;
        System.out.println(a - b);
    }

    @Test
    public void test3(){
        int x = 3;
        int y = 6;
        
        System.out.println(x);
        System.out.println(y);
    }

}
