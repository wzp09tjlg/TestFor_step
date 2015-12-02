package com.zhongxin.home.testfor_step.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.zhongxin.home.testfor_step.R;

/**
 * Created by Walter on 2015/11/3.
 */
public class Top2DelView extends RelativeLayout {
    private final String TAG = "Top2DelView";
    //widget
    private ImageView mImg;
    //data


    public Top2DelView(Context context) {
        super(context);
        initView(context);
    }

    public Top2DelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context){
        View view = LayoutInflater.from(context).inflate(R.layout.item_top2delete,null);
        mImg = (ImageView)view.findViewById(R.id.step_img_top2delete);
        addView(view);
        setLisneter();
    }

    private void setLisneter(){
        mImg.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                final boolean singleClick = event.getRepeatCount() == 1 && event.getAction() == KeyEvent.ACTION_DOWN ;
                if(!singleClick)
                    return false;
                if(keyCode == KeyEvent.KEYCODE_DPAD_UP && event.getAction() == KeyEvent.ACTION_DOWN){
                     mImg.setImageLevel(0);
                }else if(keyCode == KeyEvent.KEYCODE_DPAD_DOWN && event.getAction() == KeyEvent.ACTION_DOWN){
                    mImg.setImageLevel(1);
                }else if((keyCode == KeyEvent.KEYCODE_DPAD_CENTER || keyCode == KeyEvent.KEYCODE_ENTER )&& event.getAction() == KeyEvent.ACTION_DOWN){
                    //
                    Toast.makeText(getContext(),"OK",Toast.LENGTH_LONG).show();
                }else if(keyCode == KeyEvent.KEYCODE_BACK  && event.getAction() == KeyEvent.ACTION_DOWN){
                   //back
                    Toast.makeText(getContext(),"BACK",Toast.LENGTH_LONG).show();
                }
                return true;
            }
        });
    }
}
