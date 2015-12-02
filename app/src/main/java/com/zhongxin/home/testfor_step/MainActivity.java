package com.zhongxin.home.testfor_step;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.zhongxin.home.testfor_step.view.encrypt.EncryptActivity;
import com.zhongxin.home.testfor_step.view.focus.FocusActivity;
import com.zhongxin.home.testfor_step.view.genericity.GenericityActivity;
import com.zhongxin.home.testfor_step.view.gesture.GestureActivity;
import com.zhongxin.home.testfor_step.view.gridItemAnimator.GridItemAnimatorActivity;
import com.zhongxin.home.testfor_step.view.io.IOActivity;
import com.zhongxin.home.testfor_step.view.json.JsonActivity;
import com.zhongxin.home.testfor_step.view.motion.MotionActivity;
import com.zhongxin.home.testfor_step.view.okHttp.OKHttpActivity;
import com.zhongxin.home.testfor_step.view.reflect.ReflectActivity;
import com.zhongxin.home.testfor_step.view.switcher.SwitcherActivity;
import com.zhongxin.home.testfor_step.view.viewpager.ViewPagerActivity;
import com.zhongxin.home.testfor_step.view.viewstub.ViewStubActivity;
import com.zhongxin.home.testfor_step.view.volley.VolleyActivity;
import com.zhongxin.home.testfor_step.view.xml.XmlActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MainActivity extends Activity implements
    View.OnClickListener
{
    private final  String TAG = "MainActivity";
    //widget
    private Button mButtonHandler;                    //handler
    private Button mButtonThreadPool;                 //threadPool
    private Button mButtonRelfect;                    //reflect
    private Button mButtonVolley;                     //volley
    private Button mButtonOkHttp;                     //OkHttp
    private Button mButtonIO;                         //IO
    private Button mButtonJson;                       //Json
    private Button mButtonXml;                        //xml
    private Button mButtonGenericity;                 //genericity
    private Button mButtonAnimator;                   //Animator
    private Button mButtonFocus;                      //Focus
    private Button mButtonSwitcher;                   //switcher
    private Button mButtonViewStub;                   //viewStub
    private Button mButtonViewPager;                  //viewPager
    private Button mButtonMotion;                     //Motion
    private Button mButtonGesture;                    //gesture
    private Button mButtonEncrypt;                    //encrypt
    //data
    private List<String> mList;
    private int count = 0;
    //interface
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findView();
        initView();
    }

    @Override
    public void onClick(View v) {
      switch (v.getId()){
          case R.id.step_btn_handler:
              //判断是否是同一天之内的处理

              String pattern = "yyy-MM-dd"; //首先定义时间格式
              SimpleDateFormat sdf = new SimpleDateFormat(pattern);
              String oldTime = "" + (System.currentTimeMillis());
              System.out.println(sdf.format(Long.parseLong(oldTime)));
              Date oldDate = null;
              try{
                   oldDate = sdf.parse(sdf.format(Long.parseLong(oldTime)));
              }catch (Exception e){}

              String newTime = "" + (System.currentTimeMillis() - 2000000000l);
              try{
                  System.out.println(sdf.format(Long.parseLong(newTime)));
                  Date newDate = sdf.parse(sdf.format(Long.parseLong(newTime)));
                  if(newDate.after(oldDate))
                      System.out.println("true");
                  else
                      System.out.println("false");

                  int result = newDate.compareTo(oldDate);
                  System.out.println("result:" + result);

                  System.out.println("newDate" + newDate.getDate() + "    oldDate:" + oldDate.getDate());
              }catch (Exception e){

              }

//              mList.add("Value:" + count);
//              count ++;
//              System.out.println("count:" + count + " list.size():" + mList.size());
              break;
          case R.id.step_btn_threadPool:
              String str = mList.get(100);   //这里会报 超出边界的错
              if(str == null)
                  System.out.println(" str is null");
              else
                  System.out.println(" str isnot null");
//              Intent intentThreadPool = new Intent(MainActivity.this, ThreadPoolActivity.class);
//              startActivity(intentThreadPool);
              break;
          case R.id.step_btn_Reflect:
              Intent intentReflect = new Intent(MainActivity.this, ReflectActivity.class);
              startActivity(intentReflect);
              break;
          case R.id.step_btn_volley:
              Intent intentVolley = new Intent(MainActivity.this, VolleyActivity.class);
              startActivity(intentVolley);
              break;
          case R.id.step_btn_okhttp:
              Intent intentOkHttp = new Intent(MainActivity.this, OKHttpActivity.class);
              startActivity(intentOkHttp);
              break;
          case R.id.step_btn_io:
              Intent intentIO = new Intent(MainActivity.this, IOActivity.class);
              startActivity(intentIO);
              break;
          case R.id.step_btn_json:
              Intent intentJson = new Intent(MainActivity.this,JsonActivity.class);
              startActivity(intentJson);
              break;
          case R.id.step_btn_xml:
              Intent intentXml = new Intent(MainActivity.this, XmlActivity.class);
              startActivity(intentXml);
              break;
          case R.id.step_btn_genericity:
              Intent intentGenericity = new Intent(MainActivity.this, GenericityActivity.class);
              startActivity(intentGenericity);
              break;
          case R.id.step_btn_itemAnimator:
              Intent intentAnimator = new Intent(MainActivity.this, GridItemAnimatorActivity.class);
              startActivity(intentAnimator);
              break;
          case R.id.step_btn_focus:
              Intent intentFocus = new Intent(MainActivity.this, FocusActivity.class);
              startActivity(intentFocus);
              break;
          case R.id.step_btn_switcher:
              Intent intentSwitcher = new Intent(MainActivity.this, SwitcherActivity.class);
              startActivity(intentSwitcher);
              break;
          case R.id.step_btn_viewStub:
              Intent intentViewStub = new Intent(MainActivity.this, ViewStubActivity.class);
              startActivity(intentViewStub);
              break;
          case R.id.step_btn_viewpager:
              Intent intentViewPager = new Intent(MainActivity.this, ViewPagerActivity.class);
              startActivity(intentViewPager);
              break;
          case R.id.step_btn_motion:
              Intent intentMotion = new Intent(MainActivity.this, MotionActivity.class);
              startActivity(intentMotion);
              break;
          case R.id.step_btn_gesture:
              Intent intentGesture = new Intent(MainActivity.this, GestureActivity.class);
              startActivity(intentGesture);
              break;
          case R.id.step_btn_encrypt:
              Intent intentEncrypt = new Intent(MainActivity.this, EncryptActivity.class);
              startActivity(intentEncrypt);
              break;
      }
    }

    private <T extends View> T $(int id){
        return (T)super.findViewById(id);
    }

    private void findView(){
        mButtonHandler = (Button)this.findViewById(R.id.step_btn_handler);
        mButtonThreadPool = (Button)this.findViewById(R.id.step_btn_threadPool);
        mButtonRelfect = (Button)this.findViewById(R.id.step_btn_Reflect);
        mButtonVolley  = (Button)this.findViewById(R.id.step_btn_volley);
        mButtonOkHttp = (Button)this.findViewById(R.id.step_btn_okhttp);
        mButtonIO = (Button)this.findViewById(R.id.step_btn_io);
        mButtonJson = (Button) this.findViewById(R.id.step_btn_json);
        mButtonXml = (Button)this.findViewById(R.id.step_btn_xml);
        mButtonGenericity = (Button)this.findViewById(R.id.step_btn_genericity);
        mButtonAnimator = (Button)this.findViewById(R.id.step_btn_itemAnimator);
        mButtonFocus = (Button)this.findViewById(R.id.step_btn_focus);
        mButtonSwitcher = (Button)this.findViewById(R.id.step_btn_switcher);
        mButtonViewStub = (Button)this.findViewById(R.id.step_btn_viewStub);
        mButtonViewPager = (Button)this.findViewById(R.id.step_btn_viewpager);
        mButtonMotion = (Button)this.findViewById(R.id.step_btn_motion);
        mButtonGesture = (Button)this.findViewById(R.id.step_btn_gesture);
        mButtonEncrypt  = (Button)this.findViewById(R.id.step_btn_encrypt);
    }

    private void initView(){
        mList = new ArrayList<>();
        mButtonHandler.setOnClickListener(this);
        mButtonThreadPool.setOnClickListener(this);
        mButtonRelfect.setOnClickListener(this);
        mButtonVolley.setOnClickListener(this);
        mButtonOkHttp.setOnClickListener(this);
        mButtonIO.setOnClickListener(this);
        mButtonJson.setOnClickListener(this);
        mButtonXml.setOnClickListener(this);
        mButtonGenericity.setOnClickListener(this);
        mButtonAnimator.setOnClickListener(this);
        mButtonFocus.setOnClickListener(this);
        mButtonSwitcher.setOnClickListener(this);
        mButtonViewStub.setOnClickListener(this);
        mButtonViewPager.setOnClickListener(this);
        mButtonMotion.setOnClickListener(this);
        mButtonGesture.setOnClickListener(this);
        mButtonEncrypt.setOnClickListener(this);
    }

}
