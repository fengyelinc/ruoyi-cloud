package com.iuiga.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iuiga.common.core.utils.StringUtils;
import com.iuiga.system.domain.SysOperLog;
import com.iuiga.system.mapper.SysOperLogMapper;
import com.iuiga.system.service.ISysOperLogService;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 操作日志 服务层处理
 * 
 * @author ruoyi
 */
@Service
public class SysOperLogServiceImpl extends ServiceImpl<SysOperLogMapper, SysOperLog> implements ISysOperLogService
{

    /**
     * 新增操作日志
     * 
     * @param operLog 操作日志对象
     * @return 结果
     */
    @Override
    public int insertOperlog(SysOperLog operLog)
    {
        operLog.setOperTime(new Date());
        return baseMapper.insert(operLog);
    }

    /**
     * 查询系统操作日志集合
     * 
     * @param operLog 操作日志对象
     * @return 操作日志集合
     */
    @Override
    public List<SysOperLog> selectOperLogList(SysOperLog operLog)
    {
        Map<String, Object> params = operLog.getParams();
        return list(new LambdaQueryWrapper<SysOperLog>()
                .like(StringUtils.isNotBlank(operLog.getTitle()), SysOperLog::getTitle, operLog.getTitle())
                .eq(operLog.getBusinessType()!=null, SysOperLog::getBusinessType, operLog.getBusinessType())
                .in(operLog.getBusinessTypes()!=null, SysOperLog::getBusinessType, operLog.getBusinessTypes()!=null? Arrays.asList(operLog.getBusinessTypes()): new ArrayList<>())
                .eq(operLog.getStatus()!=null, SysOperLog::getStatus, operLog.getStatus())
                .like(StringUtils.isNotBlank(operLog.getOperName()), SysOperLog::getOperName, operLog.getOperName())
                .ge(params.get("beginTime")!=null, SysOperLog::getOperTime, params.get("beginTime"))
                .le(params.get("endTime")!=null, SysOperLog::getOperTime, params.get("endTime"))
                .orderByDesc(SysOperLog::getOperId));
    }

    /**
     * 批量删除系统操作日志
     * 
     * @param operIds 需要删除的操作日志ID
     * @return 结果
     */
    @Override
    public int deleteOperLogByIds(Long[] operIds)
    {
        return baseMapper.deleteBatchIds(Arrays.asList(operIds));
    }

    /**
     * 查询操作日志详细
     * 
     * @param operId 操作ID
     * @return 操作日志对象
     */
    @Override
    public SysOperLog selectOperLogById(Long operId)
    {
        return getById(operId);
    }

    /**
     * 清空操作日志
     */
    @Override
    public void cleanOperLog()
    {
        remove(new LambdaQueryWrapper<>());
    }
}
