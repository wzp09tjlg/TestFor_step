package com.zhongxin.home.testfor_step.view.reflect;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.zhongxin.home.testfor_step.R;
import com.zhongxin.home.testfor_step.bean.PersonBean;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by Walter on 2015/10/21.
 */
public class ReflectActivity extends Activity implements
        View.OnClickListener
{
    private final String TAG = "";
    //widget
    private Button mButtonGetProperty;
    private Button mButtonGetStaticProperty;
    private Button mButtonInvokeMethod;
    private Button mButtonInvokeStaticMethod;
    private Button mButtonGetInstance;
    private Button mButtonIsInstance;
    //data
    private PersonBean bean = new PersonBean();
    //interface

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_reflect);
        findView();
        initView();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.step_btn_getProperty:
                try{
                    String info = "info";
                    Class clazz1 = PersonBean.class;
                    Field infoField = clazz1.getField(info);
                    Log.e(TAG,"infoField:" + infoField);
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case R.id.step_btn_getStaticProperty:
                try{
                    Class clazz2 = PersonBean.class;
                    Field addressFiled = clazz2.getField("address");
                    Log.e(TAG,"addressFiled:" + addressFiled);
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case R.id.step_btn_invokeMethod:
                try{
                     String methodName = "getSum";
                     Class clazz3 = PersonBean.class;
                    Method method1 = clazz3.getMethod(methodName,String.class,String.class);
                    Object obj1 = clazz3.newInstance();
                    int sum =(int) method1.invoke(obj1,"1","2");
                    Log.e(TAG,"sum:" + sum);
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case R.id.step_btn_invokeStaticMethhod:
                try{
                    PersonBean.address = "beijing shangdi";
                    String methodGetAddress = "getAddress";
                    Class clazz4 = PersonBean.class;
                    Method method2 = clazz4.getMethod(methodGetAddress,String.class);
                    Object obj2 = clazz4.newInstance();
                    String address = (String) method2.invoke(obj2,String.class);
                    Log.e(TAG,"address:" + address);
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case R.id.step_btn_getInstance:
                try{
                    Class clazz5 = PersonBean.class;
                    bean =(PersonBean) clazz5.newInstance();
                    bean.setName("beijing beijing");
                    bean.setAge(20);
                    PersonBean.address = "this is a static string value";
                    System.out.println("person:" + bean);
                }catch (Exception e){
                   e.printStackTrace();
                }

                break;
            case R.id.step_btn_isInstance:
                boolean flag = false;
                flag = PersonBean.class.isInstance(bean);
                System.out.println("flag:" + flag);
                break;
        }
    }

    private void findView(){
        mButtonGetProperty = (Button)findViewById(R.id.step_btn_getProperty);
        mButtonGetStaticProperty = (Button)findViewById(R.id.step_btn_getStaticProperty);
        mButtonInvokeMethod = (Button)findViewById(R.id.step_btn_invokeMethod);
        mButtonInvokeStaticMethod = (Button)findViewById(R.id.step_btn_invokeStaticMethhod);
        mButtonGetInstance = (Button)findViewById(R.id.step_btn_getInstance);
        mButtonIsInstance = (Button)findViewById(R.id.step_btn_isInstance);
    }

    private void initView(){
        mButtonGetProperty.setOnClickListener(this);
        mButtonGetStaticProperty.setOnClickListener(this);
        mButtonInvokeMethod.setOnClickListener(this);
        mButtonInvokeStaticMethod.setOnClickListener(this);
        mButtonGetInstance.setOnClickListener(this);
        mButtonIsInstance.setOnClickListener(this);
    }
}
