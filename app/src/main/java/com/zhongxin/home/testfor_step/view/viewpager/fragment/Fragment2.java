package com.zhongxin.home.testfor_step.view.viewpager.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhongxin.home.testfor_step.R;

/**
 * Created by Walter on 2015/11/21.
 */
public class Fragment2 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewpager2,container,false);
        return view;
    }
}
