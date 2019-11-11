package com.pluginandaspectj.util;

import android.widget.Toast;

import com.pluginandaspectj.App;


/**
 * 创建者：wanglei
 * <p>时间：2019-11-10 21:48
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class ToastUtil {
    public static void showToast(String str){
        Toast.makeText(App.context,str,Toast.LENGTH_LONG).show();
    }
}
