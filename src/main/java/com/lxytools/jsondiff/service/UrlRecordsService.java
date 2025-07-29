package com.lxytools.jsondiff.service;

import com.lxytools.jsondiff.dto.UrlHistoryResponse;
import com.lxytools.jsondiff.dto.UrlRecordRequest;
import com.lxytools.jsondiff.entity.UrlRecords;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lixinyu
 * @since 2025-07-28
 */
public interface UrlRecordsService extends IService<UrlRecords> {

    /**
     * 记录URL访问信息
     * @param request 前端请求对象
     * @param httpRequest HTTP请求对象
     */
    void recordUrlAccess(UrlRecordRequest request, HttpServletRequest httpRequest);

    /**
     * 根据ip获取历史访问记录
     * @param ip 客户端ip
     * @return 历史记录列表: url statusCode
     */
    List<UrlHistoryResponse> getHistoryByIp(String ip);
}
