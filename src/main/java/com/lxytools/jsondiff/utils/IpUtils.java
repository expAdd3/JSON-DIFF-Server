package com.lxytools.jsondiff.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class IpUtils {
    private static final String[] IP_HEADER_NAMES = {
            "X-Forwarded-For",
            "X-Real-IP",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_CLIENT_IP",
            "HTTP_X_FORWARDED_FOR"
    };

    /**
     * 获取客户端真实IP地址
     */
    public static String getClientIpAddress(HttpServletRequest request) {
        // 先尝试从请求头获取
        for (String headerName : IP_HEADER_NAMES) {
            String ip = request.getHeader(headerName);
            if (isValidIp(ip)) {
                return ip.split(",")[0].trim();
            }
        }

        String localIp = request.getRemoteAddr();

        // 如果是私有IP，尝试获取公网IP
        if (isPrivateIp(localIp)) {
            String publicIp = getPublicIp();
            return publicIp != null ? publicIp : localIp;
        }

        return localIp;
    }

    /**
     * 验证IP是否有效
     */
    private static boolean isValidIp(String ip) {
        return ip != null && !ip.isEmpty() && !"unknown".equalsIgnoreCase(ip);
    }

    /**
     * 判断是否为私有IP
     */
    private static boolean isPrivateIp(String ip) {
        if (ip == null || ip.isEmpty()) return false;

        return ip.startsWith("192.168.") ||
                ip.startsWith("10.") ||
                ip.startsWith("172.") ||
                ip.equals("127.0.0.1") ||
                ip.equals("0:0:0:0:0:0:0:1") ||
                ip.equals("::1");
    }

    /**
     * 获取公网IP
     */
    private static String getPublicIp() {
        try {
            RestTemplate restTemplate = new RestTemplate();

            // 使用 icanhazip.com
            try {
                String response = restTemplate.getForObject("https://icanhazip.com ", String.class);
                if (response != null && !response.trim().isEmpty()) {
                    return response.trim();
                }
            } catch (Exception e) {
                System.err.println("icanhazip.com 获取失败: " + e.getMessage());
            }

        } catch (Exception e) {
            System.err.println("获取公网IP失败: " + e.getMessage());
        }
        return null;
    }

}