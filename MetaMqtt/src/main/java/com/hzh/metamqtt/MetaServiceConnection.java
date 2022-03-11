package com.hzh.metamqtt;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

/**
 * @author coder HzH
 * @time 2022/03/10
 */
public abstract class MetaServiceConnection implements ServiceConnection {
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
    }
    @Override
    public void onServiceDisconnected(ComponentName name) {
    }
}
