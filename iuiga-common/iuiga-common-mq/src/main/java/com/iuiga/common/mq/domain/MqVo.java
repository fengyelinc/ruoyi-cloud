package com.iuiga.common.mq.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("MQ消息模板")
public class MqVo implements Serializable {
    @ApiModelProperty("MQ消息类型")
    private String service;
}
