package com.zhongxin.home.testfor_step.view.gridItemAnimator;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.zhongxin.home.testfor_step.R;
import com.zhongxin.home.testfor_step.adapter.ItemAnimatorAdapter;
import com.zhongxin.home.testfor_step.bean.AnimatorBean;
import com.zhongxin.home.testfor_step.listener.GridItemAnimator;
import com.zhongxin.home.testfor_step.listener.OnItemClickListener;
import com.zhongxin.home.testfor_step.listener.OnItemFocusChangeListener;
import com.zhongxin.home.testfor_step.utils.FocusZoomUtil;
import com.zhongxin.home.testfor_step.widget.gridview.VerticalGridView;

import java.util.ArrayList;

/**
 * Created by Wu on 2015/10/27.
 */
public class GridItemAnimatorActivity  extends Activity implements
        View.OnClickListener,
        OnItemClickListener<AnimatorBean>,
        OnItemFocusChangeListener<AnimatorBean>
{
    private final String TAG = "GridItemAnimatorActivity";
    private final int MSG_CODE = 0X1101;
    //widget
    private Button mButtonItemAdd;
    private Button mButtonItemDelete;
    private VerticalGridView mGrid;
    private ImageView mCoverImg;
    //data
    private Context mContext = this;
    private ItemAnimatorAdapter animatorAdapter;
    private GridItemAnimator mGridItemAnimator;
    private HandlerThread handlerThread;
    private Handler handler;
    private Looper looper;
    private boolean isRunning = false;
    private ItemAnimationRunnable mItemAnimationRunnable;
    private FocusZoomUtil focusZoomUtil;
    int i =1;
    //interface
    @Override
    public void onItemClickListener(View view, int position, AnimatorBean animatorBean) {
//        Toast.makeText(this,"position: " + position ,Toast.LENGTH_LONG).show();
//        FocusZoomUtil.clickScaleAnimation(view);
//        StaticUtil.SubStaticUtil.someStaticMethod();
    }

    @Override
    public void onItemFocusChangeListener(View view, int position, AnimatorBean animatorBean, boolean hasFocus) {
//        FocusZoomUtil.clickScaleAnimation(view);
//        FocusMoveUtil.focusMove(mCoverImg,view,300);
        focusZoomUtil.onItemFocused(view,hasFocus);  //来实现动画，里边有TimeAnimator.TimeListener的接口,这个接口是系统来回调的
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recycler_itemanimator);
        findView();
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        isRunning = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isRunning = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(mItemAnimationRunnable);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.step_btn_grid_itemAnimator_add:
//                animatorAdapter.addItem();
//                animatorAdapter.notifyItemChanged(0);
//                animatorAdapter.setAnimation(true);
//                animatorAdapter.notifyItemChanged( (31 * 2 * i) % 13);
//                i = (i + 3) * (i + 2);
                doSomeLogWork();
                break;
            case R.id.step_btn_grid_itemAnimator_delete:
                animatorAdapter.deleteItem();
                animatorAdapter.notifyItemChanged(0);
                break;
        }
    }

    private void findView(){
        mButtonItemAdd = (Button)this.findViewById(R.id.step_btn_grid_itemAnimator_add);
        mButtonItemDelete = (Button)this.findViewById(R.id.step_btn_grid_itemAnimator_delete);
        mGrid = (VerticalGridView)this.findViewById(R.id.step_grid);
        mCoverImg = (ImageView) this.findViewById(R.id.step_img_cover);
        focusZoomUtil = new FocusZoomUtil(FocusZoomUtil.ZOOM_FACTOR_LLARGE);
    }

    private void initView(){
        mButtonItemAdd.setOnClickListener(this);
        mButtonItemDelete.setOnClickListener(this);
        animatorAdapter = new ItemAnimatorAdapter(this,this,this,getData());
        mGrid.setAdapter(animatorAdapter);
//        mGridItemAnimator = new GridItemAnimator();
//        mGrid.setItemAnimator(mGridItemAnimator);

        handlerThread = new HandlerThread(TAG);
        handlerThread.start();
        looper = handlerThread.getLooper();
        handler = new Handler(looper){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what == MSG_CODE){
                    handler.removeMessages(MSG_CODE);
                    animatorAdapter.setAnimation(true);
                    animatorAdapter.notifyItemChanged( (31 * 2 * i) % 13);
                    i = (i + 3) * (i + 2);
                    int delay = 3 * 1000;
                    handler.sendEmptyMessageDelayed(MSG_CODE,delay);
                }
            }
        };
//        handler.sendEmptyMessage(MSG_CODE);
    }

    private ArrayList<AnimatorBean> getData(){
       ArrayList<AnimatorBean> data= new ArrayList<>();
        for(int i=0;i<100;i++){
            AnimatorBean bean = new AnimatorBean();
            data.add(bean);
        }
        return data;
    }

    private void doSomeLogWork(){
        StackTraceElement st[]= Thread.currentThread().getStackTrace();
        for(int i=0;i<st.length;i++)
            System.out.println(i+":"+st[i]);
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("-------------------------------------------------------------------------");
        Log.wtf(TAG, "this a fake erro");
        /*
        * StackTraceElement st[]= Thread.currentThread().getStackTrace();
for(int i=0;i<st.length;i++)
System.out.println(i+":"+st[i]);
这样你就把此处代码此时的调用层次(调用栈)打印出来了。
第二种，使用android.util.Log.wtf()
我们可以直接使用android提供的android.util.Log.wtf()系列函数来输出一个日志.在输出日志的同时，它会把此处代码此时的执行路径(调用栈)打印出来。
示例2：
Log.wtf(tag, "this a fake erro");

        * */
    }

    class ItemAnimationRunnable implements Runnable{  ////重写一个ruunnable 随机设置item的动画
        @Override
        public void run() {
            while(isRunning){
                Log.e(TAG,"B1");
                int position  = ((int) (Math.random() * 100) + 1) % 12;
                Log.e(TAG,"B2  position:" + position);
                Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.slide_right);
                AnimationSet set = new AnimationSet(true);
                set.addAnimation(animation);
                ItemAnimatorAdapter.ViewHolder viewHolder = (ItemAnimatorAdapter.ViewHolder) mGrid.findViewHolderForAdapterPosition(position);//mGrid.findViewHolderForLayoutPosition(position);
                //mGrid.findViewHolderForLayoutPosition(position).itemView.setAnimation(animation);
                if(viewHolder == null)
                    Log.e(TAG,"viewHolder IS NULL");
                else
                    Log.e(TAG,"viewHolder IS NOT NULL");

                if(viewHolder.itemView == null)
                    Log.e(TAG,"itemView IS NULL");
                else
                    Log.e(TAG,"itemView IS NOT NULL");

                if(animation == null)
                    Log.e(TAG,"animation IS NULL");
                else
                    Log.e(TAG,"animation IS NOT NULL");

                viewHolder.getImg().setAnimation(set);
                set.start();

                Log.e(TAG,"B3");
                try{
                    Log.e(TAG,"B4");
                    Thread.currentThread().sleep((long)(Math.random() * 10) * 1000);//10秒中的随机处理
                }catch (Exception e){}
//                animatorAdapter.notifyDataSetChanged();//更新一下，不更新就没有动画
                handler.sendEmptyMessage(MSG_CODE);
            }
        }
    }
}
