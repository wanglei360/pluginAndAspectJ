package com.pluginandaspectj;

import android.app.Application;
import android.content.Context;

/**
 * 创建者：wanglei
 * <p>时间：2019-11-10 21:49
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class App extends Application {
    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
}
