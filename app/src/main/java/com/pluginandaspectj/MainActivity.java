package com.pluginandaspectj;

import android.app.Activity;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.pluginandaspectj.test.MyTest;
import com.pluginandaspectj.util.ToastUtil;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button but = findViewById(R.id.but);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                new MyTest().text();
                Log.d("mLifeCycleController",isMainThread()+"11111");
            }
        });
    }
    public boolean isMainThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }
}
