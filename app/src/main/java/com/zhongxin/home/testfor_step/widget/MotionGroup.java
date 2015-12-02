package com.zhongxin.home.testfor_step.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.zhongxin.home.testfor_step.utils.MotionUtil;

/**
 * Created by Walter on 2015/11/29.
 */
public class MotionGroup extends LinearLayout {
    private static final String TAG = "MotionGroup";
    public MotionGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MotionGroup(Context context) {
        super(context);
        init();
    }

    private void init() {
        setOnClickListener(mOnClickListener);
        setOnLongClickListener(mOnLongClickListener);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        MotionUtil.showMotionEventType(event, "ViewGroup", "onInterceptTouchEvent");
        boolean returnValue = super.onInterceptTouchEvent(event);

        // This method JUST determines whether we want to intercept the motion.
        // If we return true, onTouchEvent will be called

        MotionUtil.showReturnValue(returnValue, "ViewGroup", "onInterceptTouchEvent");
        return returnValue;

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        MotionUtil.showMotionEventType(event, "ViewGroup", "dispatchTouchEvent");
        boolean returnValue = super.dispatchTouchEvent(event);
        MotionUtil.showReturnValue(returnValue, "ViewGroup", "dispatchTouchEvent");
        return returnValue;
    }

    // ViewGroup自己的Touch事件处理，如果在onInterceptTouchEvent返回true，则会到这里处理，不传入child
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        MotionUtil.showMotionEventType(event, "ViewGroup", "onTouchEvent");

        boolean returnValue = super.onTouchEvent(event);
        MotionUtil.showReturnValue(returnValue, "ViewGroup", "onTouchEvent");
        return returnValue;
    }

    private OnClickListener mOnClickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            MotionUtil.showInfo("onClick", "ViewGroup", "mOnClickListener");
            // onClick是ACTION_UP后调用的

        }
    };

    private OnLongClickListener mOnLongClickListener = new OnLongClickListener() {

        @Override
        public boolean onLongClick(View v) {
            // onLongClick按下到一定的时间就调用了
            MotionUtil.showInfo("onLongClick", "ViewGroup", "mOnLongClickListener");
            // 如果返回false，则长按结束的ACTION_UP调用onClick
            // 如果返回true，onLongClick后不再调用onClick
            return true;
        }
    };
}
