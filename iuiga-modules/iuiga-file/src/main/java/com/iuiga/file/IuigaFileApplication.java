package com.iuiga.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import com.iuiga.common.swagger.annotation.EnableCustomSwagger2;

/**
 * 文件服务
 * 
 * @author ruoyi
 */
@EnableCustomSwagger2
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class IuigaFileApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(IuigaFileApplication.class, args);
        System.out.println("文件服务模块启动成功");
    }
}
