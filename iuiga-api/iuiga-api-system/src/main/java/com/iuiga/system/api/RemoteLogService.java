package com.iuiga.system.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import com.iuiga.common.core.constant.SecurityConstants;
import com.iuiga.common.core.constant.ServiceNameConstants;
import com.iuiga.common.core.domain.R;
import com.iuiga.system.api.domain.SysLogininforVo;
import com.iuiga.system.api.domain.SysOperLogVo;
import com.iuiga.system.api.factory.RemoteLogFallbackFactory;

/**
 * 日志服务
 * 
 * @author ruoyi
 */
@FeignClient(contextId = "remoteLogService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteLogFallbackFactory.class)
public interface RemoteLogService
{
    /**
     * 保存系统日志
     *
     * @param sysOperLogVo 日志实体
     * @param source 请求来源
     * @return 结果
     */
    @PostMapping("/operlog")
    public R<Boolean> saveLog(@RequestBody SysOperLogVo sysOperLogVo, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    /**
     * 保存访问记录
     *
     * @param sysLogininforVo 访问实体
     * @param source 请求来源
     * @return 结果
     */
    @PostMapping("/logininfor")
    public R<Boolean> saveLogininfor(@RequestBody SysLogininforVo sysLogininforVo, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);
}
