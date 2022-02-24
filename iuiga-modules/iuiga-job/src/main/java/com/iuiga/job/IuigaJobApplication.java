package com.iuiga.job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.iuiga.common.security.annotation.EnableCustomConfig;
import com.iuiga.common.security.annotation.EnableIuigaFeignClients;
import com.iuiga.common.swagger.annotation.EnableCustomSwagger2;

/**
 * 定时任务
 * 
 * @author ruoyi
 */
@EnableCustomConfig
@EnableCustomSwagger2   
@EnableIuigaFeignClients
@SpringBootApplication
public class IuigaJobApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(IuigaJobApplication.class, args);
        System.out.println("定时任务模块启动成功");
    }
}
