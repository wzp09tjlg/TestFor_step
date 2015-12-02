package com.zhongxin.home.testfor_step.view.viewpager.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.zhongxin.home.testfor_step.R;
import com.zhongxin.home.testfor_step.view.viewstub.ViewStubActivity;

/**
 * Created by Walter on 2015/11/21.
 */
public class Fragment5 extends Fragment {
    private static final String TAG = "Fragment5";
    //widget
    private Button mButtonEnter;
    //data
    //interface

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewpager5,container,false);
        mButtonEnter = (Button)view.findViewById(R.id.step_btn_fragment_viewpager);
        mButtonEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 startActivity(new Intent(getActivity(), ViewStubActivity.class));
                getActivity().finish();
            }
        });
        return view;
    }
}
