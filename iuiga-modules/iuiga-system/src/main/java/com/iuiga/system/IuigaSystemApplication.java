package com.iuiga.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.iuiga.common.security.annotation.EnableCustomConfig;
import com.iuiga.common.security.annotation.EnableIuigaFeignClients;
import com.iuiga.common.swagger.annotation.EnableCustomSwagger2;

/**
 * 系统模块
 * 
 * @author ruoyi
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableIuigaFeignClients
@SpringBootApplication
public class IuigaSystemApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(IuigaSystemApplication.class, args);
        System.out.println("系统模块启动成功");
    }
}
