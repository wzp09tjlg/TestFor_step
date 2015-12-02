package com.zhongxin.home.testfor_step.view.motion;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

import com.zhongxin.home.testfor_step.R;
import com.zhongxin.home.testfor_step.utils.MotionUtil;

/**
 * Created by Walter on 2015/11/29.
 */
public class MotionActivity extends Activity {
    private static final String TAG = "MotionActivity";
    //widget
    //data
    //interface

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motion);
        findView();
        initView();
    }

    private void findView(){}

    private void initView(){}


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        MotionUtil.showMotionEventType(ev, "Activity", "dispatchTouchEvent");
        boolean result = super.dispatchTouchEvent(ev);

        MotionUtil.showReturnValue(result, "Activity", "dispatchTouchEvent");
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        MotionUtil.showMotionEventType(event, "Activity", "onTouchEvent");
        boolean result = super.onTouchEvent(event);
        MotionUtil.showReturnValue(result, "Activity", "onTouchEvent");
        return result;
    }
}
