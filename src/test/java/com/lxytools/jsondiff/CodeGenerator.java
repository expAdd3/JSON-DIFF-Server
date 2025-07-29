package com.lxytools.jsondiff;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.util.Collections;

public class CodeGenerator {
    public static void main(String[] args) {
        // 数据库连接配置
        String url = "jdbc:mysql://localhost:3306/JsonDiff?useSSL=false&serverTimezone=UTC";
        String username = "root";
        String password = "lxy200222";

        // 输出目录（生成的代码存放路径）
        String outputDir = System.getProperty("user.dir") + "/src/main/java";

        FastAutoGenerator.create(url, username, password)
                // 全局配置
                .globalConfig(builder -> {
                    builder.author("lixinyu")                    // 设置作者
                            .enableSwagger()                       // 开启 Swagger 模式（可选）
                            .outputDir(outputDir);                 // 指定输出目录
                })
                // 包配置
                .packageConfig(builder -> {
                    builder.parent("com.lxytools.jsondiff")            // 设置父包名
                            .moduleName("")                        // 设置父包模块名（可为空）
                            .pathInfo(Collections.singletonMap(OutputFile.xml,
                                    System.getProperty("user.dir") + "/src/main/resources/mapper")); // Mapper XML 文件路径
                })
                // 策略配置
                .strategyConfig(builder -> {
                    builder.addInclude("url_records")           // 设置需要生成的表名（多个表用逗号分隔）
                            .entityBuilder()
                            .enableLombok()                       // 开启 Lombok 模式
                            .enableTableFieldAnnotation()        // 开启生成实体时生成字段注解
                            .controllerBuilder()
                            .enableRestStyle()                    // 开启生成 @RestController 控制器
                            .mapperBuilder()
                            .enableMapperAnnotation()             // 开启 @Mapper 注解
                            .serviceBuilder()
                            .formatServiceFileName("%sService");  // 格式化 Service 接口文件名称
                })
                // 使用 Velocity 引擎模板，默认的是 Velocity 引擎模板
                .templateEngine(new VelocityTemplateEngine())
                // 执行
                .execute();
    }
}