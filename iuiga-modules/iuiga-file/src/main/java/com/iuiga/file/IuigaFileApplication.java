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
public class DemoFileApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(DemoFileApplication.class, args);
        System.out.println("文件服务模块启动成功");
    }
}
