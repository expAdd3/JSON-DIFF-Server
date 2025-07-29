package com.lxytools.jsondiff.service.impl;

import com.lxytools.jsondiff.dto.UrlHistoryResponse;
import com.lxytools.jsondiff.dto.UrlRecordRequest;
import com.lxytools.jsondiff.entity.UrlRecords;
import com.lxytools.jsondiff.mapper.UrlRecordsMapper;
import com.lxytools.jsondiff.service.UrlRecordsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxytools.jsondiff.utils.IpUtils;
import com.lxytools.jsondiff.utils.UrlUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lixinyu
 * @since 2025-07-28
 */
@Service
public class UrlRecordsServiceImpl extends ServiceImpl<UrlRecordsMapper, UrlRecords> implements UrlRecordsService {

    @Override
    public void recordUrlAccess(UrlRecordRequest request, HttpServletRequest httpRequest) {

        UrlRecords record = new UrlRecords();
        record.setUrl(request.getUrl());
        record.setHttpMethod(request.getMethod());
        record.setStatusCode(Short.valueOf(request.getStatusCode()));
        record.setIpAddress(IpUtils.getClientIpAddress(httpRequest));
        record.setUserAgent(httpRequest.getHeader("User-Agent"));
        record.setReferrer(httpRequest.getHeader("Referer"));
        LocalDateTime now = LocalDateTime.now();
        record.setCreatedAt(now);
        record.setUpdatedAt(now);

        UrlUtils.parseUrl(record, request.getUrl());

        int result = this.baseMapper.insertOrUpdateByIpAndUrl(record);
        System.out.println("SQL执行结果：" + result);
    }

    @Override
    public List<UrlHistoryResponse> getHistoryByIp(String ip) {

        // 查询特定ip下面最近的10条记录，按照最后访问时间降序
        List<UrlRecords> records = this.lambdaQuery()
                .eq(UrlRecords::getIpAddress, ip)
                .orderByDesc(UrlRecords::getUpdatedAt)
                .last("LIMIT 10")
                .list();

        return records.stream()
                .map(record -> {
                    UrlHistoryResponse response = new UrlHistoryResponse();
                    response.setUrl(record.getUrl());
                    response.setStatusCode(Integer.valueOf(record.getStatusCode()));
                    return response;
                })
                .collect(Collectors.toList());
    }
}
