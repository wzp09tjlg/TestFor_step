package com.zhongxin.home.testfor_step.view.touchEvent;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.zhongxin.home.testfor_step.R;

/**
 * Created by Walter on 2015/11/16.
 */
public class TouchEventActivity extends Activity {
    private static final String TAG = "TouchEventActivity";
    //widget
    //data
    //interface
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_touchevent);
    }
}
