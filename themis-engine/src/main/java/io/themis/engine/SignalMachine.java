package io.themis.engine;

/**
 * @program: themis
 * @description: 信号机
 * @author: yangzhi
 * @create: 2019-12-21 12:03
 */
public abstract class SignalMachine {

    /**
     * 接收
     */
    public abstract void receive();

    /**
     * 发送
     */
    public abstract void send();

}