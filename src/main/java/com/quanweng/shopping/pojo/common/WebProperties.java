package com.quanweng.shopping.pojo.common;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "set.web")
public class WebProperties {
    private String webAddress;
}
