package io.themis.engine.impl;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import io.themis.engine.SignalMachine;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @program: themis
 * @description: MQ实现下的信号机，目前特指rabbitmq
 * @author: yangzhi
 * @create: 2019-12-21 13:40
 */
@Component
@RabbitListener(queues = "themisSignalBus")
public class MqSignalMachine {
    @Autowired
    private AmqpTemplate rabbitTemplate;


    /**
     * 接收
     */
    @RabbitHandler
    public void receive(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) {

    }

    /**
     * 发送
     */
    public void send(String queue, Map<String, Object> contentMap) {
        if (null == contentMap) {
            throw new IllegalArgumentException("contentMap can not null!!!");
        }
        rabbitTemplate.convertAndSend(queue, JSON.toJSONString(contentMap));
    }
}