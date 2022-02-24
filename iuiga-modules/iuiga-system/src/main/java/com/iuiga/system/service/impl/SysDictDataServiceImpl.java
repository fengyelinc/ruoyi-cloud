package com.iuiga.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iuiga.common.core.utils.StringUtils;
import com.iuiga.common.core.utils.bean.BeanUtils;
import com.iuiga.common.security.utils.DictUtils;
import com.iuiga.system.api.domain.SysDictDataVo;
import com.iuiga.system.domain.SysDictData;
import com.iuiga.system.mapper.SysDictDataMapper;
import com.iuiga.system.service.ISysDictDataService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 字典 业务层处理
 * 
 * @author ruoyi
 */
@Service
public class SysDictDataServiceImpl extends ServiceImpl<SysDictDataMapper, SysDictData> implements ISysDictDataService
{

    /**
     * 根据条件分页查询字典数据
     * 
     * @param dictData 字典数据信息
     * @return 字典数据集合信息
     */
    @Override
    public List<SysDictData> selectDictDataList(SysDictData dictData)
    {
        return list(new LambdaQueryWrapper<SysDictData>()
                .eq(StringUtils.isNotBlank(dictData.getDictType()), SysDictData::getDictType, dictData.getDictType())
                .like(StringUtils.isNotBlank(dictData.getDictLabel()), SysDictData::getDictLabel, dictData.getDictLabel())
                .eq(StringUtils.isNotBlank(dictData.getStatus()), SysDictData::getStatus, dictData.getStatus())
                .orderByAsc(SysDictData::getDictSort));
    }

    /**
     * 根据字典类型和字典键值查询字典数据信息
     * 
     * @param dictType 字典类型
     * @param dictValue 字典键值
     * @return 字典标签
     */
    @Override
    public String selectDictLabel(String dictType, String dictValue)
    {
        SysDictData data = getOne(new LambdaQueryWrapper<SysDictData>()
                .eq(SysDictData::getDictType, dictType)
                .eq(SysDictData::getDictValue, dictValue));
        return data!=null?data.getDictLabel():null;
    }

    /**
     * 根据字典数据ID查询信息
     * 
     * @param dictCode 字典数据ID
     * @return 字典数据
     */
    @Override
    public SysDictData selectDictDataById(Long dictCode)
    {
        return getById(dictCode);
    }

    /**
     * 转换成API VO
     * @param data
     * @return
     */
    private List<SysDictDataVo> convertVo(List<SysDictData> data) {
        return data!=null?data.stream().map(item -> {
            SysDictDataVo vo = new SysDictDataVo();
            BeanUtils.copyBeanProp(vo, item);
            return vo;
        }).collect(Collectors.toList()):null;
    }

    /**
     * 根据字典类型找字典列表
     * @param dictType
     * @return
     */
    public List<SysDictData> listByType(String dictType) {
        return list(new LambdaQueryWrapper<SysDictData>().eq(SysDictData::getDictType, dictType));
    }
    
    /**
     * 批量删除字典数据信息
     * 
     * @param dictCodes 需要删除的字典数据ID
     */
    @Override
    public void deleteDictDataByIds(Long[] dictCodes)
    {
        for (Long dictCode : dictCodes)
        {
            SysDictData data = selectDictDataById(dictCode);
            removeById(dictCode);
            List<SysDictData> dictDatas = listByType(data.getDictType());
            DictUtils.setDictCache(data.getDictType(), convertVo(dictDatas));
        }
    }

    /**
     * 新增保存字典数据信息
     * 
     * @param data 字典数据信息
     * @return 结果
     */
    @Override
    public int insertDictData(SysDictData data)
    {
        data.setCreateTime(new Date());
        int row = baseMapper.insert(data);
        if (row > 0)
        {
            List<SysDictData> dictDatas = listByType(data.getDictType());
            DictUtils.setDictCache(data.getDictType(), convertVo(dictDatas));
        }
        return row;
    }

    /**
     * 修改保存字典数据信息
     * 
     * @param data 字典数据信息
     * @return 结果
     */
    @Override
    public int updateDictData(SysDictData data)
    {
        data.setUpdateTime(new Date());
        int row = baseMapper.updateById(data);
        if (row > 0)
        {
            List<SysDictData> dictDatas = listByType(data.getDictType());
            DictUtils.setDictCache(data.getDictType(), convertVo(dictDatas));
        }
        return row;
    }
}
