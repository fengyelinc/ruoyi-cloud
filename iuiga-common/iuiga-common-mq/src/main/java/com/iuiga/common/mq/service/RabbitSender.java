package com.iuiga.common.mq.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public abstract class RabbitSender implements RabbitTemplate.ConfirmCallback
{
    private static final Logger log = LoggerFactory.getLogger(RabbitSender.class);

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        if(b) {
            log.info("MQ Producer Message ID: "+correlationData+", Callback Success, Cause: "+ s);
            callback(correlationData);
        } else {
            log.info("MQ Producer Message ID: "+correlationData+", Callback Fail, Cause: "+ s);
        }
    }

    public void callback(CorrelationData correlationData) {

    }
}
