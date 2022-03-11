package com.hzh.sample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.hzh.metamqtt.MetaMqtt;
import com.hzh.metamqtt.MetaMqttCallBack;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "TAG_MQTT";

    private TextView am_clear;
    private EditText am_push_topic, am_push_content;
    private TextView am_push_message;
    private EditText am_subscribe_topic;
    private TextView am_subscribe;
    private RecyclerView am_list;

    private List<MessageBean> data = new ArrayList<>();

    private MqttClient mqttClient;

    private String inputTopic;

    private static final String TOPIC_HZH = "app/hzh";
    private static final String TOPIC_MACHINE = "app/machine";
    private static final String TOPIC_MESSAGE = "app/+";
    private static final String CLIENT_MACHINE = "sample1";
    private static final String CLIENT_HZH = "sample2";
    private String TOPIC_PUSH = "";
    private String CLIENT_ID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 只需要改这两个，但要对应起来，要么同为HZH，要么同为MACHINE，这个涉及到订阅和发布。
        TOPIC_PUSH = TOPIC_MACHINE;
        CLIENT_ID = CLIENT_MACHINE;

        // 绑定控件
        initView();

        // 发送消息监听
        am_push_message.setOnClickListener(v -> {
            // 此步执行在连接服务器之后
            String content = am_push_content.getText().toString().trim();
//            String topic = am_push_topic.getText().toString().trim();
            if (mqttClient != null) {
                pushMessage(mqttClient, content, TOPIC_PUSH);
            } else {
                ToastUtils.showShort("MQTT服务器异常");
            }
        });

        // 订阅监听
        am_subscribe.setOnClickListener(v -> {
            if (mqttClient != null) {
                inputTopic = am_subscribe_topic.getText().toString().trim();
                // 订阅
                try {
                    mqttClient.subscribe(inputTopic, 1);
                } catch (MqttException e) {
                    e.printStackTrace();
                }
            } else {
                ToastUtils.showShort("MQTT服务器异常");
            }
        });

        // 清空消息
        am_clear.setOnClickListener(v -> {
            data.clear();
            am_list.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            am_list.setAdapter(new ListAdapter(data, MainActivity.this, TOPIC_PUSH));
        });

        // 连接到MQTT服务器
        MetaMqtt.with(this)
                .url("")
                .port("1883")  // 18911
                .client(CLIENT_ID)
                .username("hzh")  // device_user
                .password("hzh")  // d0c67428-8f97-dada-f2ca-3da8c28c548a
                .topic(TOPIC_MESSAGE)
                .timeout(10)
                .beat(20)
                .retry(5)
                .callback(new MetaMqttCallBack() {
                    @Override
                    public void connectSuccess(MqttClient client) {
                        Log.e(TAG, "connectSuccess");
                        ToastUtils.showShort("连接成功");
                        // 得到建立的连接Client
                        mqttClient = client;
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
                    public void messageArrived(String arriveTopic, MqttMessage message) {
                        Log.e(TAG, "messageArrived" + System.currentTimeMillis());
                        try {
                            ToastUtils.showShort("收到消息：" + new String(message.getPayload(), "GB2312"));
                            // 添加数据
                            MessageBean messageBean = new MessageBean();
                            messageBean.setTopic(arriveTopic);
                            // 原封文本：message.toString()
                            // 字节流：message.getPayload();
                            messageBean.setMessage(message.toString());
                            data.add(messageBean);
                            // 此处接收到信息
                            // 进行数据设置
                            runOnUiThread(() -> {
                                am_list.smoothScrollToPosition(10);
                                am_list.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                                am_list.setAdapter(new ListAdapter(data, MainActivity.this, TOPIC_PUSH));
                            });
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                            ToastUtils.showShort("-=========-");
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

    public void pushMessage(MqttClient client, String content, String topic) {
        // 发送消息 考虑是否需要转为字节流
        MqttMessage mqttMessage = new MqttMessage();
        byte[] bytes = ConvertUtils.string2Bytes(content);
        mqttMessage.setPayload(bytes);
        try {
            // 订阅
//            client.subscribe(topic, 1);
            // 推送
            client.publish(topic, mqttMessage);
        } catch (MqttException e) {
            e.printStackTrace();
            Log.e("TAG", e.toString());
        }
    }

    private void initView() {
        am_push_topic = findViewById(R.id.am_push_topic);
        am_push_content = findViewById(R.id.am_push_content);
        am_push_message = findViewById(R.id.am_push_message);
        am_subscribe_topic = findViewById(R.id.am_subscribe_topic);
        am_subscribe = findViewById(R.id.am_subscribe);
        am_list = findViewById(R.id.am_list);
        am_clear = findViewById(R.id.am_clear);
    }
}