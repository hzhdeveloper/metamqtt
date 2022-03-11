package com.hzh.metamqtt;

import static android.content.Context.BIND_AUTO_CREATE;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

/**
 * @author coder HzH
 * @time 2022/03/10
 * <p>
 * META MQTT BY HZH
 */
public class MetaManager {

    /**
     * 上下文
     */
    private Context mContext;
    /**
     * 服务器地址
     */
    private String serverUrl;
    /**
     * 服务器端口
     */
    private String serverPort;
    /**
     * MQTT 客户端ID
     */
    private String clientId;
    /**
     * MQTT 账号
     */
    private String username;
    /**
     * MQTT 密码
     */
    private String password;
    /**
     * MQTT 主题
     */
    private String topic;
    /**
     * MQTT 超时时间
     */
    private Integer timeout = 0;
    /**
     * MQTT 心跳时间
     */
    private Integer beatTime = 0;
    /**
     * MQTT 重连时间 单位S 非必须，默认10S
     */
    private Integer reConnectTime = 10;
    /**
     * MQTT 遗嘱，1.0.1版本不用
     */
    private String will;
    /**
     * service binder
     */
    private MetaMqttService.MsgBinder msgBinder;
    /**
     * 回调接口
     */
    private MetaMqttCallBack mCallBack;

    /**
     * 设置上下文
     *
     * @param mContext 上下文
     * @return MetaManager
     */
    public MetaManager setContext(Context mContext) {
        this.mContext = mContext;
        return this;
    }

    /**
     * 设置服务器地址，必须
     * <br/>get the Url
     *
     * @param url 地址
     * @return MetaMqtt
     */
    public MetaManager url(String url) {
        this.serverUrl = url;
        return this;
    }

    /**
     * 设置服务器端口，必须
     * <br/>get the Port
     *
     * @param port 端口
     * @return MetaMqtt
     */
    public MetaManager port(String port) {
        this.serverPort = port;
        return this;
    }

    /**
     * 设置MQTT客户端ID，非必须，默认为"mqttApp"
     * <br/>get the client
     *
     * @param client 客户端ID
     * @return MetaMqtt
     */
    public MetaManager client(String client) {
        this.clientId = client;
        return this;
    }

    /**
     * 设置MQTT 用户名，必须
     * <br/>get the username
     *
     * @param username 用户名
     * @return MetaMqtt
     */
    public MetaManager username(String username) {
        this.username = username;
        return this;
    }

    /**
     * 设置MQTT 密码，必须
     * <br/>get the password
     *
     * @param password 密码
     * @return MetaMqtt
     */
    public MetaManager password(String password) {
        this.password = password;
        return this;
    }

    /**
     * 设置MQTT 主题，必须
     *
     * @param topic 主题
     * @return MetaMqtt
     */
    public MetaManager topic(String topic) {
        this.topic = topic;
        return this;
    }

    /**
     * 设置MQTT超时时间，非必须，默认10S
     *
     * @param timeout 超时时间
     * @return MetaMqtt
     */
    public MetaManager timeout(Integer timeout) {
        this.timeout = timeout;
        return this;
    }

    /**
     * 设置MQTT超时时间，非必须，默认10S
     *
     * @param reConnectTime 重连时间
     * @return MetaMqtt
     */
    public MetaManager retry(Integer reConnectTime) {
        this.reConnectTime = reConnectTime;
        return this;
    }

    /**
     * 设置MQTT心跳时间，非必须，默认20S
     *
     * @param beatTime 心跳时间
     * @return MetaMqtt
     */
    public MetaManager beat(Integer beatTime) {
        this.beatTime = beatTime;
        return this;
    }

    /**
     * 接收到数据回调，必须
     *
     * @param callBack 回调
     * @return MetaMqtt
     */
    public MetaManager callback(MetaMqttCallBack callBack) {
        this.mCallBack = callBack;
        return this;
    }

    /**
     * 开启MQTT服务
     */
    public void start() {
        Intent serviceIntent = new Intent(mContext, MetaMqttService.class);
        serviceIntent.putExtra("serverUrl", "tcp://" + serverUrl + ":" + serverPort);
        serviceIntent.putExtra("clientId", clientId == null ? "mqttApp" : clientId);
        serviceIntent.putExtra("username", username);
        serviceIntent.putExtra("password", password);
        serviceIntent.putExtra("timeout", timeout == 0 ? 10 : timeout);
        serviceIntent.putExtra("beatTime", beatTime == 0 ? 20 : timeout);
        serviceIntent.putExtra("topic", topic);
        serviceIntent.putExtra("reConnectTime", reConnectTime);
        mContext.bindService(serviceIntent, mqttServiceConnect, BIND_AUTO_CREATE);
        mContext.startService(serviceIntent);
    }

    private ServiceConnection mqttServiceConnect = new MetaServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            msgBinder = (MetaMqttService.MsgBinder) service;
            msgBinder.getService().setCallBackListener(mCallBack);
        }
    };

}
