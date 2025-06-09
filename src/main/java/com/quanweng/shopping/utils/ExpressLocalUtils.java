package com.quanweng.shopping.utils;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Component
public class ExpressLocalUtils {
    /**
     * 根据快递单号解析快递公司
     * @param trackingNumber 快递单号
     * @return 快递公司名称，如不匹配返回"未知"
     */
    public String recognizeExpressCompany(String trackingNumber) {
        if (trackingNumber == null || trackingNumber.isEmpty()) {
            return "未知";
        }

        // 国内快递公司识别
        if (isMatch(trackingNumber, "^SF\\d{12}$")) return "顺丰速运";
        if (isMatch(trackingNumber, "^[0-9]{12}$") && trackingNumber.startsWith("77")) return "京东快递";
        if (isMatch(trackingNumber, "^YT\\d{10,13}$")) return "圆通快递";
        if (isMatch(trackingNumber, "^ZTO\\d{9,12}$")) return "中通快递";
        if (isMatch(trackingNumber, "^[0-9]{12}$") && trackingNumber.startsWith("73")) return "申通快递";
        if (isMatch(trackingNumber, "^[0-9]{14}$")) return "韵达快递";
        if (isMatch(trackingNumber, "^[A-Z]{2}\\d{10}CN$")) return "中国邮政EMS";
        if (isMatch(trackingNumber, "^[0-9]{13}$")) return "百世快递";
        if (isMatch(trackingNumber, "^[0-9]{12}$") && trackingNumber.startsWith("66")) return "德邦快递";
        if (isMatch(trackingNumber, "^JD[A-Z0-9]{15}$")) return "京东物流";
        if (isMatch(trackingNumber, "^[0-9]{10}$")) return "天天快递";
        if (isMatch(trackingNumber, "^[0-9]{9}01$")) return "苏宁物流";

        // 国际快递公司识别
        if (isMatch(trackingNumber, "^1Z[A-Z0-9]{16}$")) return "UPS";
        if (isMatch(trackingNumber, "^\\d{12}$") && trackingNumber.startsWith("000")) return "DHL";
        if (isMatch(trackingNumber, "^\\d{15}$")) return "FedEx联邦快递";
        if (isMatch(trackingNumber, "^[A-Z]{2}\\d{9}[A-Z]{2}$")) return "TNT";
        if (isMatch(trackingNumber, "^LX\\d{10}HK$")) return "香港邮政";

        return "未知";
    }

    // 正则匹配工具方法
    private static boolean isMatch(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

}
