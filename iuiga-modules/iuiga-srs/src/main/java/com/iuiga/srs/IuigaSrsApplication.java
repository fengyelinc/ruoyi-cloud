package com.iuiga.srs;

import com.iuiga.common.security.annotation.EnableCustomConfig;
import com.iuiga.common.security.annotation.EnableIuigaFeignClients;
import com.iuiga.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 直播模块
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableIuigaFeignClients
@SpringBootApplication
public class IuigaSrsApplication {
    public static void main(String[] args)
    {
        SpringApplication.run(IuigaSrsApplication.class, args);
        System.out.println("直播服务模块启动成功");
    }
}
