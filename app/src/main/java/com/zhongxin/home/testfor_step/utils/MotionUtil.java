package com.zhongxin.home.testfor_step.utils;

import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by Wu on 2015/11/29.
 */
public class MotionUtil {
    public static void showMotionEventType(MotionEvent event, String logTag,
                                           String methodName) {

        final int action = event.getActionMasked();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.e(logTag, methodName + ": " + action + ": ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(logTag, methodName + ": " + action + ": ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(logTag, methodName + ": " + action + ": ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e(logTag, methodName + ": " + action + ": ACTION_CANCEL");
                break;

            default:
                break;
        }

    }

    public static void showReturnValue(boolean returnValue, String logTag,
                                       String methodName) {
        Log.e(logTag, methodName + " return: " + returnValue);
    }

    public static void showInfo(String info, String logTag, String methodName) {
        Log.e(logTag, methodName + " info: " + info);
    }
}
