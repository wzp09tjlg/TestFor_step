package com.zhongxin.home.testfor_step.view.gesture;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.zhongxin.home.testfor_step.R;

/**
 * Created by Wu on 2015/12/1.
 */
public class GestureActivity extends Activity implements
        View.OnClickListener,
        View.OnTouchListener
{
    private static final String TAG = "GestureActivity";
    private static final int RIGHT = 0X1001;
    private static final int LEFT  = 0X1002;
    private static final int TOP   = 0X1003;
    private static final int BOTTOM = 0X1004;
    //widget
    private Button mButtonDetector;
    private View mViewGesture;
    //data
    private GestureDetector mDetector;
    private int type = 0;//0监听横屏的动作 1监听竖屏的动作
    //interface
    @Override
    public void onClick(View v) {
      switch (v.getId()){
          case R.id.step_btn_start_gestureDetector:
              type = (++type) % 2;
              break;
      }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) { //监听触摸事件，重写这个方法
        return mDetector.onTouchEvent(event);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        mDetector.onTouchEvent(event);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesturedetector);
        findView();
        initView();
    }

    private  <T extends View>T $(int id){
        return (T)super.findViewById(id);
    }

    private void findView(){
        mButtonDetector = $(R.id.step_btn_start_gestureDetector);
        mViewGesture = $(R.id.step_view_detecotr);
    }

    private void initView(){
        mButtonDetector.setOnClickListener(this);
        mDetector = new GestureDetector(this,mListener);
//        mViewGesture.setOnTouchListener(this);
    }

    private GestureDetector.OnGestureListener mListener = new GestureDetector.SimpleOnGestureListener(){
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Log.e(TAG,"OnGestureListener -->onDoubleTap");
            return super.onDoubleTap(e);
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            Log.e(TAG,"OnGestureListener -->onDoubleTapEvent");
            return super.onDoubleTapEvent(e);
        }

        @Override
        public boolean onDown(MotionEvent e) {
            Log.e(TAG,"OnGestureListener -->onDown");
            return super.onDown(e);
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.e(TAG,"OnGestureListener -->onFling");
            float x = e2.getX() - e1.getX();
            float y = e2.getY() - e1.getY();

            if(type == 0){ //监听横屏的滑动处理
                if(x>0)
                    doSomeThingOnresultTouch(RIGHT);
                else
                    doSomeThingOnresultTouch(LEFT);
            }else{//监听竖屏的滑动处理
               if(y>0)
                   doSomeThingOnresultTouch(BOTTOM);
                else
                   doSomeThingOnresultTouch(TOP);
            }
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.e(TAG,"OnGestureListener -->onScroll");
            return super.onScroll(e1, e2, distanceX, distanceY);
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            Log.e(TAG,"OnGestureListener -->onSingleTapConfirmed");
            return super.onSingleTapConfirmed(e);
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            Log.e(TAG,"OnGestureListener -->onSingleTapUp");
            return super.onSingleTapUp(e);
        }

        @Override
        public void onLongPress(MotionEvent e) {
            Log.e(TAG,"OnGestureListener -->onLongPress");
            super.onLongPress(e);
        }

        @Override
        public void onShowPress(MotionEvent e) {
            Log.e(TAG,"OnGestureListener -->onShowPress");
            super.onShowPress(e);
        }
    };

    private void doSomeThingOnresultTouch(int id){
        switch (id){
            case RIGHT:
                 Log.e(TAG,"RIGHT");
                break;
            case LEFT:
                Log.e(TAG,"LEFT");
                break;
            case TOP:
                Log.e(TAG,"TOP");
                break;
            case BOTTOM:
                Log.e(TAG,"BOTTOM");
                break;
        }
    }

//    private GestureDetector.OnGestureListener onGestureListener =
//            new GestureDetector.SimpleOnGestureListener() {
//                @Override
//                public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
//                                       float velocityY) {
//                    float x = e2.getX() - e1.getX();
//                    float y = e2.getY() - e1.getY();
//
//                    if (x > 0) {
//                        doResult(RIGHT);
//                    } else if (x < 0) {
//                        doResult(LEFT);
//                    }
//                    return true;
//                }
//            };
}
