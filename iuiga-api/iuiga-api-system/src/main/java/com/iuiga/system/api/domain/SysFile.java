package com.iuiga.system.api.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 文件信息
 * 
 * @author ruoyi
 */
@Data
public class SysFile
{
    /**
     * 文件名称
     */
    private String name;

    /**
     * 文件地址
     */
    private String url;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("name", getName())
            .append("url", getUrl())
            .toString();
    }
}
