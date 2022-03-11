## metamqtt
一个MQTT的库，极大方便操作连接MQTT以及消息收发 。-------------------------------a dependence for mqtt to connect and get or send message☺☺

## 使用教程
### 如果你的gradle版本在7.0之上请在settings.gradle添加maven地址
### 请一定记得添加网络权限
```
<uses-permission android:name="android.permission.INTERNET" />
```
### 第一步 在项目的build.gradle添加maven地址（gradle版本<7.0）
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
### 第二步 在app的build.gradle添加依赖项
```
dependencies {
	    implementation 'com.github.hzhdeveloper:metamqtt:1.0.1'
}
```
## 用法
### 随便在Activity/Fragment的OnCreate方法甚至在全局Application中使用(如在全局使用，自己处理消息回调)
```
MetaMqtt.with(this)
                .url("XXX")
                .port("XXX")
                .client("mqtttest")
                .username("XXX")
                .password("XXX")
                .topic("test")
                .timeout(10)
                .beat(20)
                .callback(new MetaMqttCallBack(){})
		.start();
```
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
