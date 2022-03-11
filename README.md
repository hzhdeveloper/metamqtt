## metamqtt
一个MQTT的库，极大方便操作连接MQTT以及消息收发 。-------------------------------a dependence for mqtt to connect and get or send message☺☺

## 使用教程
### 第一步 在项目的build.gradle添加maven地址
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

### License
```
Copyright 2014 - 2020 Henning Dodenhof

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
