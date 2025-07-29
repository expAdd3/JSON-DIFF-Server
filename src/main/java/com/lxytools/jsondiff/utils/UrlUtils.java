package com.lxytools.jsondiff.utils;

import com.lxytools.jsondiff.entity.UrlRecords;

import java.net.MalformedURLException;
import java.net.URL;

public class UrlUtils {
    /**
     * 解析URL信息并设置到记录对象中
     */
    public static void parseUrl(UrlRecords record, String urlString) {
        try {
            URL url = new URL(urlString);
            record.setDomain(url.getHost());
            record.setPath(url.getPath());
            record.setQueryParams(url.getQuery());
        } catch (MalformedURLException e) {
            // 解析失败默认处理
            record.setDomain("unknown");
            record.setPath(urlString);
            record.setQueryParams(null);
        }
    }
}
