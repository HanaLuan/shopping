package com.quanweng.shopping.utils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Component
public class ExpressUtils {
// TODO 逆天快递鸟
    // 配置参数
    private static final String E_BUSINESS_ID = "1889430";
    private static final String API_KEY = "32855fcd-706e-4546-8a30-cf421c9591fe";
    private static final String API_URL = "https://api.kdniao.com/api/dist";
    private static final String REQUEST_TYPE = "2002";

    public static List<String> getExpressCompany(String logisticCode) {
        List<String> companyNames = new ArrayList<>();
        System.out.println("===== 开始识别快递单号: " + logisticCode + " =====");

        try {
            // 1. 创建原始请求数据
            JSONObject requestData = new JSONObject();
            requestData.put("LogisticCode", logisticCode);
            String rawData = requestData.toString();
            System.out.println("原始请求数据: " + rawData);

            // 2. Base64编码请求数据
            String base64Data = Base64.getEncoder().encodeToString(rawData.getBytes(StandardCharsets.UTF_8));
            System.out.println("Base64编码后的请求数据: " + base64Data);

            // 3. 生成签名（使用快递鸟推荐的算法）
            String dataSign = generateSignature(rawData);
            System.out.println("生成签名: " + dataSign);

            // 4. 准备请求参数（严格按文档顺序）
            String params = "RequestData=" + URLEncoder.encode(base64Data, "UTF-8")
                    + "&EBusinessID=" + URLEncoder.encode(E_BUSINESS_ID, "UTF-8")
                    + "&RequestType=" + URLEncoder.encode(REQUEST_TYPE, "UTF-8")
                    + "&DataSign=" + URLEncoder.encode(dataSign, "UTF-8")
                    + "&DataType=2";

            System.out.println("完整请求参数: " + params);

            // 5. 发送HTTP请求
            String response = sendPostRequest(API_URL, params);
            System.out.println("API原始响应: " + response);

            // 6. 解析返回结果
            companyNames = parseApiResponseWithFastJson(response);
        } catch (Exception e) {
            System.err.println("快递公司识别失败: " + e.getMessage());
            e.printStackTrace();
        }

        return companyNames;
    }

    /**
     * 严格按快递鸟规范生成签名
     */
    private static String generateSignature(String rawRequestData) throws Exception {
        // 1. 拼接原始请求数据和API密钥
        String signString = rawRequestData + API_KEY;

        // 2. MD5加密
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] digestBytes = md.digest(signString.getBytes(StandardCharsets.UTF_8));

        // 3. 转换为32位小写十六进制字符串
        StringBuilder hexString = new StringBuilder();
        for (byte b : digestBytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        String md5Lower = hexString.toString().toLowerCase();

        // 4. Base64编码（去掉末尾可能出现的等号）
        String base64Sign = Base64.getEncoder().encodeToString(md5Lower.getBytes(StandardCharsets.UTF_8));
        base64Sign = base64Sign.replaceAll("=+$", "");  // 去掉Base64填充字符

        // 5. URL编码
        return URLEncoder.encode(base64Sign, "UTF-8");
    }

    /**
     * 发送HTTP POST请求
     */
    private static String sendPostRequest(String urlString, String params) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        // 设置请求头（增加Content-Length）
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("User-Agent", "Apifox/1.0.0 (https://apifox.com)");
        conn.setRequestProperty("Accept", "*/*");
        conn.setRequestProperty("Connection", "keep-alive");
        conn.setDoOutput(true);

        // 设置Content-Length
        byte[] postData = params.getBytes(StandardCharsets.UTF_8);
        conn.setRequestProperty("Content-Length", String.valueOf(postData.length));

        // 发送请求体
        try (OutputStream os = conn.getOutputStream()) {
            os.write(postData);
        }

        // 处理响应
        StringBuilder response = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } finally {
            conn.disconnect();
        }

        return response.toString();
    }

    /**
     * 解析API返回的JSON数据
     */
    private static List<String> parseApiResponseWithFastJson(String jsonResponse) {
        List<String> validCompanies = new ArrayList<>();

        try {
            JSONObject responseJson = JSON.parseObject(jsonResponse);

            if (responseJson == null || !responseJson.getBooleanValue("Success")) {
                System.err.println("API调用失败: " + responseJson.getString("Reason"));
                return validCompanies;
            }

            JSONArray shippers = responseJson.getJSONArray("Shippers");
            if (shippers != null) {
                for (int i = 0; i < shippers.size(); i++) {
                    JSONObject shipper = shippers.getJSONObject(i);
                    String companyName = shipper.getString("ShipperName");
                    if (companyName != null && !companyName.isEmpty() && !"null".equalsIgnoreCase(companyName)) {
                        validCompanies.add(companyName);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("解析API响应时出错: " + e.getMessage());
        }

        return validCompanies;
    }

    public static void main(String[] args) {
        // 测试
        testRecognition("3967950525457");
    }

    private static void testRecognition(String trackingNumber) {
        System.out.println("\n===== 测试单号识别: " + trackingNumber + " =====");
        List<String> companies = getExpressCompany(trackingNumber);

        if (companies.isEmpty()) {
            System.out.println("未识别到快递公司");
        } else {
            System.out.println("识别结果:");
            companies.forEach(System.out::println);
        }
        System.out.println("===== 测试结束 =====");
    }
}
