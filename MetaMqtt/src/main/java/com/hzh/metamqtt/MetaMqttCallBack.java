package com.hzh.metamqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * @author coder HzH
 * @time 2022/03/10
 * <p>
 * META MQTT BY HZH
 */
public interface MetaMqttCallBack {

    /**
     * 连接成功，可以使用返回的mqttClient发送消息
     */
    void connectSuccess(MqttClient mqttClient);

    void connectFailed();

    /**
     * 连接丢失不用理会，有自动重连机制
     */
    void connectLost();

    void messageArrived(String topic, MqttMessage message);

    void pushComplete();

    void reConnectSuccess();

    /**
     * 重连失败不用理会，有自动重连机制
     */
    void reConnectFailed();

    /**
     * client为null 此错误可能无法通过重连机制恢复 需检查是否正常配置MQTT
     */
    void connectClientError();

    /**
     * 没有连接到网络
     */
    void connectIntentError();
}
