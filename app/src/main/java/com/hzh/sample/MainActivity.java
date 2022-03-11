package com.hzh.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.blankj.utilcode.util.ToastUtils;
import com.hzh.metamqtt.MetaMqtt;
import com.hzh.metamqtt.MetaMqttCallBack;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "TAG_MQTT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MetaMqtt.with(this)
                .url("XXX")
                .port("XXX")
                .client("sample")
                .username("XXX")
                .password("XXX")
                .topic("test")
                .timeout(10)
                .beat(20)
                .callback(new MetaMqttCallBack() {
                    @Override
                    public void connectSuccess(MqttClient client) {
                        Log.e(TAG, "connectSuccess");
                        ToastUtils.showShort("连接成功");
                    }

                    @Override
                    public void connectFailed() {
                        Log.e(TAG, "connectFailed");
                    }

                    @Override
                    public void connectLost() {
                        Log.e(TAG, "connectLost");
                    }

                    @Override
                    public void messageArrived(String topic, MqttMessage message) {
                        Log.e(TAG, "messageArrived");
                        try {
                            ToastUtils.showShort("收到消息：" + new String(message.getPayload(), "GB2312"));
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void pushComplete() {
                        Log.e(TAG, "pushComplete");
                    }

                    @Override
                    public void reConnectSuccess() {
                        Log.e(TAG, "reConnectSuccess");
                    }

                    @Override
                    public void reConnectFailed() {
                        Log.e(TAG, "reConnectFailed");
                    }

                    @Override
                    public void connectClientError() {
                        Log.e(TAG, "connectClientError");
                    }
                })
                .start();
    }
}