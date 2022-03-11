package com.hzh.metamqtt;

import android.content.Context;

/**
 * coder HzH communicated with email(2371827579@qq.com)
 * <br/>主入口
 * <br/>main entrance And build with builder
 * <br/>look in github: <a href="https://github.com/hzhdeveloper/metamqtt">https://github.com/hzhdeveloper/metamqtt</a>
 * <br/>2022/03/10
 */
public class MetaMqtt {

    /**
     * 获取到上下文，必须
     * <br/>get the Context
     *
     * @param context 上下文
     * @return MetaMqtt
     */
    public static MetaManager with(Context context) {
        MetaDriver metaDriver = MetaDriver.getInstance();
        return metaDriver.setContext(context);
    }

}
