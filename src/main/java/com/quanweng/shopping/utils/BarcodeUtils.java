package com.quanweng.shopping.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
@Component
public class BarcodeUtils {

    @Autowired
    private QiniuOssOperator qiniuOssOperator;
    /**
     * 生成Code128格式条形码并保存到文件
     * @param content   条形码内容
     */
    public String generateCode128Barcode(String content)
            throws WriterException, IOException {
        int width = 300;
        int height = 200;

        // 生成二维码图像
        BufferedImage qrImage = generateBarcodeImage(content, width, height);

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
     * 生成条形码并返回BufferedImage对象
     * @param content   条形码内容
     * @param width     图片宽度
     * @param height    图片高度
     * @return 生成的条形码图像
     */
    public static BufferedImage generateBarcodeImage(String content, int width, int height)
            throws WriterException {

        Code128Writer writer = new Code128Writer();
        BitMatrix bitMatrix = writer.encode(content, BarcodeFormat.CODE_128, width, height);

        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }
}
