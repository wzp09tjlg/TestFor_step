package com.zhongxin.home.testfor_step.view.switcher;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import com.zhongxin.home.testfor_step.R;
import com.zhongxin.home.testfor_step.adapter.SwitcherAdapter;
import com.zhongxin.home.testfor_step.widget.gridview.VerticalGridView;

import java.util.Random;

/**
 * Created by Walter on 2015/11/6.
 */
public class SwitcherActivity extends Activity  implements
        View.OnClickListener,
        ViewSwitcher.ViewFactory
{
    private final String TAG = "SwitcherActivity";
    private final int MSG_CODE = 0X1001;
    //widget
    private Button mButtonSwitcher;
    private VerticalGridView mGrid;
    private ImageSwitcher switcher;
    //data
    private SwitcherAdapter mSwitcherAdapter;
    private Animation mAnimation;
    private Context mContext = this;
    private int count = 1;
    private Handler mHandler= new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MSG_CODE:
                    switcher.setInAnimation(mContext,R.anim.item_in);
                    switcher.setOutAnimation(mContext,R.anim.item_out);
                    count = count +1;
                    if(count % 3 ==0)
                    switcher.setImageDrawable(getResources().getDrawable(R.drawable.category_3d));
                    else if(count % 3 ==1)
                    switcher.setImageDrawable(getResources().getDrawable(R.drawable.category_life));
                    else
                        switcher.setImageDrawable(getResources().getDrawable(R.drawable.category_record));


                    SwitcherAdapter.ViewHolder viewHolder =(SwitcherAdapter.ViewHolder)
                            mGrid.findViewHolderForAdapterPosition((int)msg.obj);
//                     viewHolder.itemView.findViewById(R.id.step_img_switcher).
//                    viewHolder.itemView.findViewById(R.id.step_img_switcher).

//                    viewHolder.itemView.setAnimation(mAnimation);
//                    count = count +1;
                    if(count % 4 ==0){
                        ((ImageSwitcher)viewHolder.itemView.findViewById(R.id.step_img_switcher)).setInAnimation(mContext, R.anim.item_in);
                        ((ImageSwitcher)viewHolder.itemView.findViewById(R.id.step_img_switcher)).setOutAnimation(mContext, R.anim.item_out);
                        ((ImageSwitcher)viewHolder.itemView.findViewById(R.id.step_img_switcher)).setImageDrawable(getResources().getDrawable(R.drawable.category_3d));
                    }
                    else if(count % 4 ==1){
                        ((ImageSwitcher)viewHolder.itemView.findViewById(R.id.step_img_switcher)).setInAnimation(mContext, R.anim.item_in1);
                        ((ImageSwitcher)viewHolder.itemView.findViewById(R.id.step_img_switcher)).setOutAnimation(mContext, R.anim.item_out1);
                        ((ImageSwitcher)viewHolder.itemView.findViewById(R.id.step_img_switcher)).setImageDrawable(getResources().getDrawable(R.drawable.category_life));
                    }
                    else if(count % 4 == 2){
                        ((ImageSwitcher)viewHolder.itemView.findViewById(R.id.step_img_switcher)).setInAnimation(mContext, R.anim.item_in2);
                        ((ImageSwitcher)viewHolder.itemView.findViewById(R.id.step_img_switcher)).setOutAnimation(mContext, R.anim.item_out2);
                        ((ImageSwitcher)viewHolder.itemView.findViewById(R.id.step_img_switcher)).setImageDrawable(getResources().getDrawable(R.drawable.category_record));
                       }
                    else if(count % 4 == 3)
                    {
                        ((ImageSwitcher)viewHolder.itemView.findViewById(R.id.step_img_switcher)).setInAnimation(mContext, R.anim.item_in4);
                        ((ImageSwitcher)viewHolder.itemView.findViewById(R.id.step_img_switcher)).setOutAnimation(mContext, R.anim.item_out4);
                        ((ImageSwitcher)viewHolder.itemView.findViewById(R.id.step_img_switcher)).setImageDrawable(getResources().getDrawable(R.drawable.category_music));
                    }
//                    viewHolder.itemView.findViewById(R.id.step_img_switcher).set
                    break;
            }
        }
    };
    //interface

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_switcher_animator);
        findView();
        initView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.step_btn_switcher:
                Random random = new Random();
                int position = random.nextInt(7);
                Message msg = Message.obtain();
                msg.what = MSG_CODE;
                msg.obj = position;
                mHandler.sendMessage(msg);
                break;
        }
    }

    @Override
    public View makeView() {
        ImageView image = new ImageView(this);
//        image.setMinimumHeight(200);
//        image.setMinimumWidth(200);
        image.setScaleType(ImageView.ScaleType.FIT_CENTER);
        image.setLayoutParams(new ImageSwitcher.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        return image;
    }

    private void findView(){
        mButtonSwitcher = (Button)this.findViewById(R.id.step_btn_switcher);
        mGrid           = (VerticalGridView) this.findViewById(R.id.step_grid_animator);
        switcher        = (ImageSwitcher)this.findViewById(R.id.step_switcher);
    }

    private void initView(){
        mAnimation = AnimationUtils.loadAnimation(this,R.anim.video_list_item_do);
        mButtonSwitcher.setOnClickListener(this);
        mSwitcherAdapter = new SwitcherAdapter(this,this);
        mGrid.setAdapter(mSwitcherAdapter);
        switcher.setFactory(this);
    }
}
