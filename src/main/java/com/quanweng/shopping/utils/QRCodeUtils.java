package com.quanweng.shopping.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.imageio.ImageIO;

@Component
public class QRCodeUtils {

    @Autowired
    private QRCodeProperties qrCodeProperties;
    @Autowired
    private AliyunOssOperator aliyunOssOperator;
    @Autowired
    private QiniuOssOperator qiniuOssOperator;


    /**
     * 生成二维码并保存到文件
     * @param content   内容（如网址）
     */
    public String generateQRCode(String content) throws Exception {
        int width = 300;
        int height = 300;

        // 生成二维码图像
        BufferedImage qrImage = generateQRCodeImage(content, width, height);

        // 转换图像为字节数组
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(qrImage, "png", os);
        byte[] bytes = os.toByteArray();

        // 生成随机文件名
        String fileName = UUID.randomUUID() + ".png";

        // 上传到OSS并返回URL
        return qiniuOssOperator.upload(bytes, fileName);
    }

    /**
     * 生成二维码返回BufferedImage对象
     */
    public static BufferedImage generateQRCodeImage(String content, int width, int height)
            throws WriterException {

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, height);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }
}
