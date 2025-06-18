package com.quanweng.shopping.utils;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Component
@Data
@ConfigurationProperties(prefix = "qiniu")
public class QiniuOssOperator {
    private String accessKey;
    private String secretKey;
    private String bucket;
    private String domain; // 存储空间绑定的域名

    public String upload(byte[] fileBytes, String originalFilename) throws QiniuException {
        String datePath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM"));

        // 生成唯一文件名
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileName = UUID.randomUUID().toString() + extension;

        // 完整的存储路径 key
        String key = datePath + "/" + fileName;

        // 创建配置对象（自动识别区域）
        Configuration cfg = new Configuration(Region.autoRegion());
        UploadManager uploadManager = new UploadManager(cfg);

        // 生成上传凭证
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);

        // 执行上传
        Response response = uploadManager.put(fileBytes, key , upToken);

        // 解析结果
        DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        return domain + "/" + putRet.key;
    }
}
