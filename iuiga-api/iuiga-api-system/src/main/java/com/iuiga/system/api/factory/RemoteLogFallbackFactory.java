package com.iuiga.system.api.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import com.iuiga.common.core.domain.R;
import com.iuiga.system.api.RemoteLogService;
import com.iuiga.system.api.domain.SysLogininforVo;
import com.iuiga.system.api.domain.SysOperLogVo;

/**
 * 日志服务降级处理
 * 
 * @author ruoyi
 */
@Component
public class RemoteLogFallbackFactory implements FallbackFactory<RemoteLogService>
{
    private static final Logger log = LoggerFactory.getLogger(RemoteLogFallbackFactory.class);

    @Override
    public RemoteLogService create(Throwable throwable)
    {
        log.error("日志服务调用失败:{}", throwable.getMessage());
        return new RemoteLogService()
        {
            @Override
            public R<Boolean> saveLog(SysOperLogVo sysOperLogVo, String source)
            {
                return null;
            }

            @Override
            public R<Boolean> saveLogininfor(SysLogininforVo sysLogininforVo, String source)
            {
                return null;
            }
        };

    }
}
