package com.lxytools.jsondiff.mapper;

import com.lxytools.jsondiff.entity.UrlRecords;
import com.lxytools.jsondiff.service.UrlRecordsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MapperTest {

    @Autowired
    private UrlRecordsMapper urlRecordsMapper;

    @Autowired
    private UrlRecordsService urlRecordsService;

    /**
     * select test
     */
    @Test
    void selectAll(){
        System.out.println("------ select all ------");
        // null 代表：查询所有
        List<UrlRecords> list = urlRecordsMapper.selectList(null);
        list.forEach(record -> {
            System.out.println("id: " + record.getId() +
                    "\nurl: " + record.getUrl());
        });
    }

    /**
     * 插入测试
     */
    @Test
    void insertTest(){
        System.out.println("------ insert ------");
        UrlRecords record = new UrlRecords();
        record.setUrl("https://www.baidu.com");
        record.setDomain("baidu.com");
        record.setHttpMethod("GET");
        record.setIpAddress("185.220.238.43");
        record.setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");
        record.setStatusCode((short) 200);
        int result = urlRecordsMapper.insert(record);
        System.out.println("SQL执行结果：" + result);
    }
}
