package com.iuiga.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iuiga.common.core.constant.UserConstants;
import com.iuiga.common.core.exception.ServiceException;
import com.iuiga.common.core.utils.StringUtils;
import com.iuiga.common.core.utils.bean.BeanUtils;
import com.iuiga.common.security.utils.DictUtils;
import com.iuiga.system.api.domain.SysDictDataVo;
import com.iuiga.system.domain.SysDictData;
import com.iuiga.system.domain.SysDictType;
import com.iuiga.system.mapper.SysDictTypeMapper;
import com.iuiga.system.service.ISysDictDataService;
import com.iuiga.system.service.ISysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 字典 业务层处理
 * 
 * @author ruoyi
 */
@Service
public class SysDictTypeServiceImpl extends ServiceImpl<SysDictTypeMapper, SysDictType> implements ISysDictTypeService
{

    @Autowired
    private ISysDictDataService dictDataService;

    /**
     * 项目启动时，初始化字典到缓存
     */
    @PostConstruct
    public void init()
    {
        loadingDictCache();
    }

    /**
     * 根据条件分页查询字典类型
     * 
     * @param dictType 字典类型信息
     * @return 字典类型集合信息
     */
    @Override
    public List<SysDictType> selectDictTypeList(SysDictType dictType)
    {
        Map<String, Object> params = dictType.getParams();
        return list(new LambdaQueryWrapper<SysDictType>()
                .like(StringUtils.isNotBlank(dictType.getDictName()), SysDictType::getDictName, dictType.getDictName())
                .eq(StringUtils.isNotBlank(dictType.getStatus()), SysDictType::getStatus, dictType.getStatus())
                .like(StringUtils.isNotBlank(dictType.getDictType()), SysDictType::getDictType, dictType.getDictType())
                .ge(params.get("beginTime")!=null, SysDictType::getCreateTime, params.get("beginTime"))
                .le(params.get("endTime")!=null, SysDictType::getCreateTime, params.get("endTime")));
    }

    /**
     * 根据所有字典类型
     * 
     * @return 字典类型集合信息
     */
    @Override
    public List<SysDictType> selectDictTypeAll()
    {
        return list();
    }

    /**
     * 根据字典类型查询字典数据
     * 
     * @param dictType 字典类型
     * @return 字典数据集合信息
     */
    @Override
    public List<SysDictData> selectDictDataByType(String dictType)
    {
        List<SysDictData> dictDatas = convertData(DictUtils.getDictCache(dictType));
        if (StringUtils.isNotEmpty(dictDatas))
        {
            return dictDatas;
        }
        dictDatas = dictDataService.listByType(dictType);
        if (StringUtils.isNotEmpty(dictDatas))
        {
            DictUtils.setDictCache(dictType, convertDataVo(dictDatas));
            return dictDatas;
        }
        return null;
    }

    /**
     * 转成API VO
     * @param data
     * @return
     */
    private List<SysDictDataVo> convertDataVo(List<SysDictData> data) {
        return data!=null?data.stream().map(item -> {
            SysDictDataVo vo = new SysDictDataVo();
            BeanUtils.copyBeanProp(vo, item);
            return vo;
        }).collect(Collectors.toList()):null;
    }

    /**
     * 转成数据
     * @param data
     * @return
     */
    private List<SysDictData> convertData(List<SysDictDataVo> data) {
        return data!=null?data.stream().map(item -> {
            SysDictData vo = new SysDictData();
            BeanUtils.copyBeanProp(vo, item);
            return vo;
        }).collect(Collectors.toList()):null;
    }

    /**
     * 根据字典类型ID查询信息
     * 
     * @param dictId 字典类型ID
     * @return 字典类型
     */
    @Override
    public SysDictType selectDictTypeById(Long dictId)
    {
        return getById(dictId);
    }

    /**
     * 根据字典类型查询信息
     * 
     * @param dictType 字典类型
     * @return 字典类型
     */
    @Override
    public SysDictType selectDictTypeByType(String dictType)
    {
        return getOne(new LambdaQueryWrapper<SysDictType>().eq(SysDictType::getDictType, dictType));
    }

    /**
     * 批量删除字典类型信息
     * 
     * @param dictIds 需要删除的字典ID
     */
    @Override
    public void deleteDictTypeByIds(Long[] dictIds)
    {
        for (Long dictId : dictIds)
        {
            SysDictType dictType = selectDictTypeById(dictId);
            if (dictDataService.count(new LambdaQueryWrapper<SysDictData>().eq(SysDictData::getDictType, dictType.getDictType())) > 0)
            {
                throw new ServiceException(String.format("%1$s已分配,不能删除", dictType.getDictName()));
            }
            removeById(dictId);
            DictUtils.removeDictCache(dictType.getDictType());
        }
    }

    /**
     * 加载字典缓存数据
     */
    @Override
    public void loadingDictCache()
    {
        Map<String, List<SysDictData>> dictDataMap = dictDataService.list(new LambdaQueryWrapper<SysDictData>().eq(SysDictData::getStatus, "0")).stream().collect(Collectors.groupingBy(SysDictData::getDictType));
        for (Map.Entry<String, List<SysDictData>> entry : dictDataMap.entrySet())
        {
            DictUtils.setDictCache(entry.getKey(), convertDataVo(entry.getValue().stream().sorted(Comparator.comparing(SysDictData::getDictSort)).collect(Collectors.toList())));
        }
    }

    /**
     * 清空字典缓存数据
     */
    @Override
    public void clearDictCache()
    {
        DictUtils.clearDictCache();
    }

    /**
     * 重置字典缓存数据
     */
    @Override
    public void resetDictCache()
    {
        clearDictCache();
        loadingDictCache();
    }

    /**
     * 新增保存字典类型信息
     * 
     * @param dict 字典类型信息
     * @return 结果
     */
    @Override
    public int insertDictType(SysDictType dict)
    {
        dict.setCreateTime(new Date());
        int row = baseMapper.insert(dict);
        if (row > 0)
        {
            DictUtils.setDictCache(dict.getDictType(), null);
        }
        return row;
    }

    /**
     * 修改保存字典类型信息
     * 
     * @param dict 字典类型信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updateDictType(SysDictType dict)
    {
        SysDictType oldDict = getById(dict.getDictId());
        List<SysDictData> dataList = dictDataService.listByType(oldDict.getDictType());
        for (SysDictData data : dataList) {
            data.setDictType(dict.getDictType());
        }
        dictDataService.updateBatchById(dataList);
        dict.setUpdateTime(new Date());
        int row = baseMapper.updateById(dict);
        if (row > 0)
        {
            List<SysDictData> dictDatas = dictDataService.listByType(oldDict.getDictType());
            DictUtils.setDictCache(dict.getDictType(), convertDataVo(dictDatas));
        }
        return row;
    }

    /**
     * 校验字典类型称是否唯一
     * 
     * @param dict 字典类型
     * @return 结果
     */
    @Override
    public String checkDictTypeUnique(SysDictType dict)
    {
        Long dictId = StringUtils.isNull(dict.getDictId()) ? -1L : dict.getDictId();
        SysDictType dictType = getOne(new LambdaQueryWrapper<SysDictType>().eq(SysDictType::getDictType, dict.getDictType()).last(" limit 1"));
        if (StringUtils.isNotNull(dictType) && dictType.getDictId().longValue() != dictId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }
}
