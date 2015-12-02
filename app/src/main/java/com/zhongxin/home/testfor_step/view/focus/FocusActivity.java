package com.zhongxin.home.testfor_step.view.focus;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.zhongxin.home.testfor_step.R;
import com.zhongxin.home.testfor_step.widget.ShadowView;


/**
 * Created by Wu on 2015/10/30.
 */
public class FocusActivity extends Activity implements
        View.OnClickListener
{
    private final String TAG = "FocusActivity";
    //widget
    private Button mButtonSetFocus;
    private RelativeLayout mLayoutLeft;
    private RelativeLayout mLayoutRight;
    private ShadowView mImgCover;
    //data
    private int count = 0;
    private int mTopOffset;
    private int mLeftOffset;
    //interface

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_focus);
        findView();
        initView();
    }


//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.activity_focus);
//        findView();
//        initView();
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.step_btn_set_focus:
                if(count++ % 2  == 0)
//                    move(mLayoutLeft);
                    doSomeMethod();
                else
//                   move(mLayoutRight);
                    doSomeMethod();
                break;
            case R.id.step_layout_focusLeft:
                break;
            case R.id.step_layout_focusRight:
                break;
        }
    }

    private void findView(){
       mButtonSetFocus = (Button)this.findViewById(R.id.step_btn_set_focus);
        mLayoutLeft = (RelativeLayout)this.findViewById(R.id.step_layout_focusLeft);
        mLayoutRight = (RelativeLayout)this.findViewById(R.id.step_layout_focusRight);
        mImgCover = (ShadowView)this.findViewById(R.id.step_img_cover);
    }

    private void initView(){
        mButtonSetFocus.setOnClickListener(this);
//        mTopOffset = getResources().getDimensionPixelSize(R.dimen.dimen_210dp);
//        mLeftOffset = getResources().getDimensionPixelSize(R.dimen.dimen_96dp);
    }

    private void move(View view){
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mImgCover
                .getLayoutParams();
        params.width = view.getMeasuredWidth() + 48;
        params.height = view.getMeasuredHeight() + 48;
        mImgCover.setLayoutParams(params);
        mImgCover.setX(view.getX() - 25 + mLeftOffset);
        mImgCover.setY(view.getY() - 25 + mTopOffset);
        mImgCover.setVisibility(View.VISIBLE);
    }

    private void doSomeMethod(){
        //通过AnimatiorSet来设计同步执行的多个属性动画
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(mLayoutLeft, "translationX", 0F, 360F);//X轴平移旋转
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(mLayoutLeft, "translationY", 0F, 360F);//Y轴平移旋转
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(mLayoutLeft, "rotation", 0F, 360F);//360度旋转
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(mLayoutLeft, "alpha", 0f, 1f,0.0f, 1.0f, 0.0f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                2.0f);
//        anim.setDuration(1000);
//        anim.start();
        AnimatorSet set = new AnimatorSet();
//        set.playSequentially(animator1, animator2, animator3);//分步执行
//        set.playTogether(animator1, animator2, animator3);//同步执行   playSequentially 和 playTogetger 不能同时使用 同时使用时会在调用的消息队列中报错

        //属性动画的执行顺序控制
        // 先同步执行动画animator2和animator3,然后再执行animator1  set的play 方法是可以将with 和 after 同时执行的
//        set.play(animator3).with(animator1).with(animator4);
        set.play(animator2).after(animator3).after(animator4);

        set.setDuration(3000);
        set.start();
    }
}
