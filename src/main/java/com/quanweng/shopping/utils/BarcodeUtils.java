package com.quanweng.shopping.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BarcodeUtils {

    /**
     * 生成Code128格式条形码并保存到文件
     * @param content   条形码内容
     */
    public static String generateCode128Barcode(String content)
            throws WriterException, IOException {

        int width = 300;
        int height = 200;

        String fileName = UUID.randomUUID() + ".png";
        String filePath = "/var/www/shopImg/upload/" + fileName;
        File uploadDir = new File(filePath);

        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.MARGIN, 1); // 设置条码边距

        Code128Writer writer = new Code128Writer();
        BitMatrix bitMatrix = writer.encode(content, BarcodeFormat.CODE_128, width, height, hints);

        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

        return filePath;
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
