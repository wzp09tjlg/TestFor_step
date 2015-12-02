package com.zhongxin.home.testfor_step.listener;

import android.view.View;

/**
 * Created by Wu on 2015/10/28.
 */
public interface OnItemClickListener<T> {
    void onItemClickListener(View view,int position,T t);
}
