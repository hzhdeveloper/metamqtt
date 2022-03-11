## <p align="center"> MetaMqtt</p>
### <p align="center"> Author:Hzh | contact:28162830(QQ) | [![](https://jitpack.io/v/hzhdeveloper/metamqtt.svg)](https://jitpack.io/#hzhdeveloper/metamqtt)</p>
#### ◆ 一个MQTT的库，极大方便操作连接MQTT以及消息收发 。
#### ◆ a dependence for mqtt to connect and get or send message☺☺
#### ◆ 具备在断网来网、重进APP(安卓高版本APP退出到主页面回调无法执行UI操作，进入APP即可)等情况下自动重连，并可设置重连时间，默认10S


## ○ 使用教程
### 如果你的gradle版本在7.0之上请在settings.gradle添加maven地址
### ◆ 请一定记得添加网络权限
```Java
<uses-permission android:name="android.permission.INTERNET" />
```
### 第一步 在项目的build.gradle添加maven地址（gradle版本<7.0）
```Groovy
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```
### 第二步 在app的build.gradle添加依赖项
```Groovy
dependencies {
	implementation 'com.github.hzhdeveloper:metamqtt:1.0.1-new'
}
```
## ○ 用法
### 随便在Activity/Fragment的OnCreate方法甚至在全局Application中使用(如在全局使用，自己处理消息回调)
```Java
MetaMqtt.with(this)			 // 上下文Context
                .url("XXX") 		 // 服务器URL
                .port("XXX")		 // 服务器端口
                .client("mqtttest")	 // MQTT客户端ID，默认为mqttApp，非必须，但多端不能重复，否则导致无限重连
                .username("XXX")	 // MQTT 用户名
                .password("XXX")	 // MQTT 密码
                .topic("test")		 // MQTT 订阅的主题，不能为空
                .timeout(10)		 // MQTT 超时时间，默认10S，非必须
                .beat(20)		 // MQTT 心跳时间，默认20S，非必须
		.retry(10)		 // MQTT 重试时间，默认10S，非必须
                .callback(new MetaMqttCallBack(){})
		.start();		 // 开始，let's do it
```
### 其中 callback 可以接收到的回调包括：连接成功、连接失败、连接丢失、收到消息、发送消息成功、重连成功、重连失败、所连接的MqttClient异常等回调
## ○ 注意事项
#### 1.订阅的Topic主题如果要使用/ 或者 + 这两个通配符，那么 / 或者 + 的前后一定不能相同，建议这样使用：订阅的主题都为app/+或者app/#，然后发送消息时所用的主题建议为app/xxx
#### 2.clientId一定不能重复，虽然说可以不设置，但还是尽可能的知道clientId，保证每个设备的clientId不一样，如果一样则会导致很多问题。
#### 3.建议参考项目内的app demo实现
## ○ 原理
#### 通过Service进行MQTT的连接，并在适当的时侯进行数据回调。
## ○ 后续
#### 可能存在部分问题，如多端同时连接同一IP，可能可能可能可能可能会导致无限重连，但不影响使用，等等其他，在后面版本修复。
## License
```
Copyright 2022 - 2022 hzhdeveloper

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
