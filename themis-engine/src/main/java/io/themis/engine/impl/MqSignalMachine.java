package io.themis.engine.impl;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import io.themis.engine.SignalMachine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
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
    private static final Logger logger = LoggerFactory.getLogger(MqSignalMachine.class);
    @Autowired
    private AmqpTemplate rabbitTemplate;
    @Resource
    private TaskDispatcherEngine taskDispatcherEngine;


    /**
     * 接收
     */
    @RabbitHandler
    public void receive(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
        try {
            Map map = JSON.parseObject(message, Map.class);
            if (TaskType.CREATE_CHAIN.toString().equals(map.get("taskType").toString())) {
                logger.debug("received type:" + TaskType.CREATE_CHAIN.toString());
                map.get("contentList");


            }else if (TaskType.COMPLETE_TASK.toString().equals(map.get("taskType").toString())) {
                logger.debug("received type:" + TaskType.COMPLETE_TASK.toString());


            }else {
                logger.debug("没有指定的taskType！！！");
            }
        } catch (Exception e) {
            logger.error("队列消费error", e);
        } finally {
            try {
                channel.basicAck(tag, false);
            } catch (IOException e) {
                logger.error("队列消息确认error", e);
            }
        }
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

    /**
     * 任务类型
     */
    enum TaskType {
        CREATE_CHAIN(1, "CREATE_CHAIN"),
        COMPLETE_TASK(2, "COMPLETE_TASK");

        private int type;

        private String content;

        TaskType(int type, String content) {
            this.type = type;
            this.content = content;
        }

        public int getType() {
            return type;
        }

        public TaskType setType(int type) {
            this.type = type;
            return this;
        }

        public String getContent() {
            return content;
        }

        public TaskType setContent(String content) {
            this.content = content;
            return this;
        }
    }
}