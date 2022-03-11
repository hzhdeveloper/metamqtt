package com.hzh.metamqtt;

import android.content.Context;

/**
 * @author coder HzH
 * @time 2022/03/10
 * <p>
 * META MQTT BY HZH
 */
public class MetaDriver {
    private static MetaDriver INSTANCE;
    private final MetaManager manager = new MetaManager();

    /**
     * 链式顺序调用，暂不考虑同步锁
     *
     * @return MetaDriver
     */
    public static MetaDriver getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MetaDriver();
        }
        return INSTANCE;
    }

    public MetaManager setContext(Context context) {
        return manager.setContext(context);
    }

}
