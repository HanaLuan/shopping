package com.quanweng.shopping.pojo.common;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "quanweng.upload")
public class UploadProperties {
    private String saveUrl;
}
