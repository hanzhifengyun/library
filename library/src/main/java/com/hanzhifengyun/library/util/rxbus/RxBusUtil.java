package com.hanzhifengyun.library.util.rxbus;


import com.hanzhifengyun.rxbus.RxBus;

/**
 * RxBusUtil
 */

public class RxBusUtil {


    /**
     * 注册事件接收者
     * @param subscriber 事件接收者
     */
    public static void register(Object subscriber) {
        RxBus.getInstance().register(subscriber);
    }

    /**
     * 注销事件接收者
     * @param subscriber 事件接收者
     */
    public static void unRegister(Object subscriber) {
        RxBus.getInstance().unRegister(subscriber);
    }


    /**
     * 发送数据
     * @param data 数据
     */
    public static void send(Object data) {
        RxBus.getInstance().send(data);
    }

    /**
     * 发送数据
     * @param code code
     * @param data 数据
     */
    public static void send(int code, Object data) {
        RxBus.getInstance().send(code, data);
    }

    /**
     * 发送数据
     * @param code code
     */
    public static void send(int code) {
        RxBus.getInstance().send(code);
    }

}
