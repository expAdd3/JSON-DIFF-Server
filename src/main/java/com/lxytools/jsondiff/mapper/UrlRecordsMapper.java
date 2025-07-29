package com.lxytools.jsondiff.mapper;

import com.lxytools.jsondiff.entity.UrlRecords;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lixinyu
 * @since 2025-07-28
 */
@Mapper
public interface UrlRecordsMapper extends BaseMapper<UrlRecords> {

    @Insert("INSERT INTO url_records (url, http_method, ip_address, user_agent, referrer, domain, path, query_params, status_code, created_at, updated_at) " +
            "VALUES (#{url}, #{httpMethod}, #{ipAddress}, #{userAgent}, #{referrer}, #{domain}, #{path}, #{queryParams}, #{statusCode}, #{createdAt}, #{updatedAt}) " +
            "ON DUPLICATE KEY UPDATE " +
            "http_method = #{httpMethod}, " +
            "user_agent = #{userAgent}, " +
            "referrer = #{referrer}, " +
            "status_code = #{statusCode}, " +
            "updated_at = #{updatedAt}")
    int insertOrUpdateByIpAndUrl(UrlRecords record);
}
