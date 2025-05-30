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


    /**
     * 生成二维码并保存到文件
     * @param content   内容（如网址）
     */
    public static String generateQRCode(String content)
            throws WriterException, IOException {
        int width = 300;
        int height = 300;

        String fileName = UUID.randomUUID() + ".png";
        String filePath = "/var/www/shopImg/upload/" + fileName;
        File uploadDir = new File(filePath);

        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, "L"); // 纠错级别 L/M/Q/H

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);

        // 直接保存为文件
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", new File(filePath).toPath());

        return filePath;
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
