package com.quanweng.shopping.utils;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "quanweng.qrcode")
public class QRCodeProperties {
    private String filePath;
    private String width;
    private String height;
}
