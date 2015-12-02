package com.zhongxin.home.testfor_step.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.zhongxin.home.testfor_step.utils.MotionUtil;

/**
 * Created by Walter on 2015/11/29.
 */
public class MotionView extends TextView {
    private static final String TAG  = "MotionView";
    public MotionView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public MotionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MotionView(Context context) {
        super(context);
    }

    private void init() {

        setOnClickListener(mOnClickListener);
        setOnLongClickListener(mOnLongClickListener);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        MotionUtil.showMotionEventType(event, "View", "dispatchTouchEvent");
        boolean returnValue = super.dispatchTouchEvent(event);
        MotionUtil.showReturnValue(returnValue, "View", "dispatchTouchEvent");
        return returnValue;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        MotionUtil.showMotionEventType(event, "View", "onTouchEvent");
        boolean returnValue = super.onTouchEvent(event);
        MotionUtil.showReturnValue(returnValue, "View", "onTouchEvent");

        return returnValue;
    }

    private OnClickListener mOnClickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            MotionUtil.showInfo("onClick", "View", "mOnClickListener");

        }
    };

    private OnLongClickListener mOnLongClickListener = new OnLongClickListener() {

        @Override
        public boolean onLongClick(View v) {
            MotionUtil.showInfo("onLongClick", "View", "mOnLongClickListener");

            // 如果返回false，则长按结束的ACTION_UP调用onClick
            return false;
        }
    };
}
