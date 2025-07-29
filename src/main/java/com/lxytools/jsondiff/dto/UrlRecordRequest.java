package com.lxytools.jsondiff.dto;

import lombok.Data;

@Data
public class UrlRecordRequest {
    private String url;
    private String method;
    private String statusCode;
}
