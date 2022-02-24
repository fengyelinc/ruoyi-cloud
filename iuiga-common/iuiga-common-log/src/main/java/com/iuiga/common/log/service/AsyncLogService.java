package com.iuiga.common.log.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.iuiga.common.core.constant.SecurityConstants;
import com.iuiga.system.api.RemoteLogService;
import com.iuiga.system.api.domain.SysOperLogVo;

/**
 * 异步调用日志服务
 * 
 * @author ruoyi
 */
@Service
public class AsyncLogService
{
    @Autowired
    private RemoteLogService remoteLogService;

    /**
     * 保存系统日志记录
     */
    @Async
    public void saveSysLog(SysOperLogVo sysOperLogVo)
    {
        remoteLogService.saveLog(sysOperLogVo, SecurityConstants.INNER);
    }
}
