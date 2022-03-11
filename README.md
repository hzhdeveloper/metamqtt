# metamqtt
一个MQTT的库，极大方便操作连接MQTT以及消息收发 。-------------------------------a dependence for mqtt to connect and get or send message☺☺

## 使用教程
# 第一步 在项目的build.gradle添加maven地址
'''
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
'''
# 第二步 在app的build.gradle添加依赖项
'''
dependencies {
	    implementation 'com.github.hzhdeveloper:metamqtt:1.0.1'
}
'''
