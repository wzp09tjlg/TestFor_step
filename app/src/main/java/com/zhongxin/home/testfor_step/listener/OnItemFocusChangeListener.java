package com.zhongxin.home.testfor_step.listener;

import android.view.View;

/**
 * Created by Walter on 2015/11/4.
 */
public interface OnItemFocusChangeListener<T> {
    void onItemFocusChangeListener(View view,int position,T t,boolean hasFocus);
}
