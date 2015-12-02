package com.zhongxin.home.testfor_step.view.encrypt;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.zhongxin.home.testfor_step.R;

import java.security.MessageDigest;

/**
 * Created by Wu on 2015/12/2.
 */
public class EncryptActivity extends Activity implements
        View.OnClickListener
{
    private static final String TAG = "EncryptActivity";
    //widget
    private Button mButtonEncryptMd5;
    private Button mButtonUnencryptMd5;
    private Button mButtonEncryptRsa;
    private Button mButtonUnencryptRsa;
    //data
    char hexDigits[] = { '0', '1', '2', '3', '4',
            '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F' };
    //inyterface
    @Override
    public void onClick(View v) {
      switch (v.getId()){
          case R.id.step_btn_EncryptMd5:
              String result = getMd5("loveyou");
              Log.e(TAG,"MD5:" + result);
              break;
          case R.id.step_btn_unEncryptMd5:
              break;
          case R.id.step_btn_RsaEncrypt:
              break;
          case R.id.step_btn_RsaUnEncrypt:
              break;
      }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_encrypt);
        findView();
        initView();
    }

    private <T extends View> T $(int id){
        return (T)super.findViewById(id);
    }

    private void findView(){
         mButtonEncryptMd5 = $(R.id.step_btn_EncryptMd5);
        mButtonUnencryptMd5 = $(R.id.step_btn_unEncryptMd5);
        mButtonEncryptRsa = $(R.id.step_btn_RsaEncrypt);
        mButtonUnencryptRsa = $(R.id.step_btn_RsaUnEncrypt);
    }

    private void initView(){
        mButtonEncryptMd5.setOnClickListener(this);
        mButtonUnencryptMd5.setOnClickListener(this);
        mButtonEncryptRsa.setOnClickListener(this);
        mButtonUnencryptRsa.setOnClickListener(this);
    }

    private String getMd5(String value){
        if(value.equals("")) return "";
        String temp = null;
        try{
            temp = new String(value);
            MessageDigest digest = MessageDigest.getInstance("MD5");
            //进行转换得到
            byte[] md = digest.digest(temp.getBytes());
            //把密文转换成十六进制的字符串形式
            int j = md.length;
            Log.e(TAG,"J:" + j);
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                Log.e(TAG,"BYTE0:"  + byte0);
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                Log.e(TAG,"1------>"  + hexDigits[byte0 >>> 4 & 0xf]);
                str[k++] = hexDigits[byte0 & 0xf];
                Log.e(TAG,"2------>"  + hexDigits[byte0 & 0xf]);
            }
            return new String(str);
        }catch (Exception e){}

        return "";
    }
}
