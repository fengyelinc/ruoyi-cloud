package com.iuiga.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 网关启动程序
 * 
 * @author ruoyi
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class IuigaGatewayApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(IuigaGatewayApplication.class, args);
        System.out.println("网关服务模块启动成功");
    }
}
