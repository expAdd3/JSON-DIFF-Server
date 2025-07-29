package com.lxytools.jsondiff.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author lixinyu
 * @since 2025-07-28
 */
@Getter
@Setter
@TableName("url_records")
public class UrlRecords implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @Schema(description = "自增id")
    private Long id;

    @TableField("url")
    @Schema(description = "完整url")
    private String url;

    @TableField("domain")
    @Schema(description = "域名")
    private String domain;

    @TableField("path")
    @Schema(description = "请求路径")
    private String path;

    @TableField("query_params")
    @Schema(description = "查询参数")
    private String queryParams;

    @TableField("ip_address")
    @Schema(description = "公网ip")
    private String ipAddress;

    @TableField("user_agent")
    @Schema(description = "设备信息")
    private String userAgent;

    @TableField("referrer")
    @Schema(description = "来源页面url")
    private String referrer;

    @TableField("http_method")
    @Schema(description = "请求方法")
    private String httpMethod;

    @TableField("is_malicious")
    @Schema(description = "是否是恶意url")
    private Boolean isMalicious;

    @TableField("status_code")
    @Schema(description = "状态码")
    private Short statusCode;

    @TableField("created_at")
    @Schema(description = "创建时间")
    private LocalDateTime createdAt;

    @TableField("updated_at")
    @Schema(description = "更新时间")
    private LocalDateTime updatedAt;
}
