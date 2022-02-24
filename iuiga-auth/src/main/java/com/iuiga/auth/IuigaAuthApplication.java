package com.iuiga.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import com.iuiga.common.security.annotation.EnableIuigaFeignClients;

/**
 * 认证授权中心
 * 
 * @author ruoyi
 */
@EnableIuigaFeignClients
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class IuigaAuthApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(IuigaAuthApplication.class, args);
        System.out.println("用户认证服务模块启动成功");
    }
}
