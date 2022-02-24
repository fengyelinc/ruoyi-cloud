package com.iuiga.common.mq.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class RabbitUtil {

    private static final Map<String, DirectExchange> directExchangeMap = new HashMap<>();

    private static final Map<String, TopicExchange> topicExchangeMap = new HashMap<>();

    private static final Map<String, FanoutExchange> fanoutExchangeMap = new HashMap<>();

    private static final Map<String, HeadersExchange> headersExchangeMap = new HashMap<>();

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public <T> T getExchange(String exchangeName, String type) {
        T resultT = null;
        Object result = null;
        switch (type) {
            case ExchangeTypes.DIRECT: result = directExchangeMap.get(exchangeName);break;
            case ExchangeTypes.TOPIC: result = topicExchangeMap.get(exchangeName);break;
            case ExchangeTypes.FANOUT: result = fanoutExchangeMap.get(exchangeName);break;
            case ExchangeTypes.HEADERS: result = headersExchangeMap.get(exchangeName);break;
            default: return null;
        }
        if(result==null) {
            try {
                switch (type) {
                    case ExchangeTypes.DIRECT: directExchangeMap.put(exchangeName, new DirectExchange(exchangeName));result = directExchangeMap.get(exchangeName);break;
                    case ExchangeTypes.TOPIC: topicExchangeMap.put(exchangeName, new TopicExchange(exchangeName));result = topicExchangeMap.get(exchangeName);break;
                    case ExchangeTypes.FANOUT: fanoutExchangeMap.put(exchangeName, new FanoutExchange(exchangeName));result = fanoutExchangeMap.get(exchangeName);break;
                    case ExchangeTypes.HEADERS: headersExchangeMap.put(exchangeName, new HeadersExchange(exchangeName));result = headersExchangeMap.get(exchangeName);break;
                    default: return null;
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            resultT = (T)result;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return resultT;
    }

    /**
     * 发送headers模式队列
     * @param exchangeName
     * @param messageBody
     * @param headers
     */
    public void sendHeadersQueue(String exchangeName, Object messageBody, Map<String, Object> headers) {
        MessagePostProcessor processor = new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                if(headers.size()>0) {
                    for (String key : headers.keySet()) {
                        Object value = headers.get(key);
                        message.getMessageProperties().setHeader(key, value);
                    }
                    return message;
                } else {
                    return null;
                }
            }
        };
        rabbitTemplate.convertAndSend(exchangeName, null, messageBody, processor);
    }

    /**
     * 发送topic模式队列
     * @param exchangeName
     * @param messageBody
     * @param routingKey
     */
    public void sendDirectQueue(String exchangeName, Object messageBody, String routingKey) {
        rabbitTemplate.convertAndSend(exchangeName, routingKey, JSON.toJSONString(messageBody));
    }

    /**
     * 发送普通队列
     * @param queueName
     * @param obj
     */
    public void sendNormalQueue(String queueName, Object obj) {
        rabbitTemplate.convertAndSend(queueName, JSON.toJSONString(obj));
    }
}
