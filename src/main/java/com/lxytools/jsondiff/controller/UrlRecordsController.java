package com.lxytools.jsondiff.controller;

import com.lxytools.jsondiff.dto.UrlHistoryResponse;
import com.lxytools.jsondiff.dto.UrlRecordRequest;
import com.lxytools.jsondiff.service.UrlRecordsService;
import com.lxytools.jsondiff.utils.IpUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lixinyu
 * @since 2025-07-28
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@Slf4j
public class UrlRecordsController {

    @Autowired
    private UrlRecordsService urlRecordsService;

    @PostMapping("/record")
    public ResponseEntity<String> recordUrlAccess(@RequestBody UrlRecordRequest request, HttpServletRequest httpRequest){
        try {
            urlRecordsService.recordUrlAccess(request, httpRequest);
            return ResponseEntity.ok("记录成功");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("记录失败: " + e.getMessage());
        }
    }

    @GetMapping("history")
    public ResponseEntity<List<UrlHistoryResponse>> getHistory(HttpServletRequest httpRequest){
        log.info("------ history 接口收到请求 ------");
        String ip = IpUtils.getClientIpAddress(httpRequest);
        List<UrlHistoryResponse> response = urlRecordsService.getHistoryByIp(ip);
        return ResponseEntity.ok(response);
    }
}
