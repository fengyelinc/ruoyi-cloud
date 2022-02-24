package com.iuiga.common.mq.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("作业任务VO")
public class JobMqVo extends MqVo {
    @ApiModelProperty("作业服务")
    private String service;

    @ApiModelProperty("作业数据")
    private String data;
}
