package com.zhongxin.home.testfor_step.view.viewstub;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.zhongxin.home.testfor_step.R;

/**
 * Created by Wu on 2015/11/20.
 */
public class ViewStubActivity extends Activity
        implements View.OnClickListener
{
    private static final String TAG = "ViewStubActivity";
    private static int count = 1;
    //widget
    private Button mButton;
    private ViewStub mViewStub;
    //data
    //interface
    @Override
    public void onClick(View v) {
     if(v.getId() == R.id.step_btn_viewstub1){
         Toast.makeText(this,"viewstub1 have inflate in this layout",Toast.LENGTH_LONG).show();
     }else{
         Toast.makeText(this,"viewstub2 have inflate in this layout",Toast.LENGTH_LONG).show();
     }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_viewstub);
        findView();
        initView();
    }

    private void findView(){
        if(++count  % 2 ==0){
           mViewStub = (ViewStub)this.findViewById(R.id.viewstub1);
           mViewStub.inflate();//have effect
           mButton = (Button) this.findViewById(R.id.step_btn_viewstub1);
        }else{
            mViewStub = (ViewStub)this.findViewById(R.id.viewstub2);
            mViewStub.inflate();//have effect
            mButton = (Button) this.findViewById(R.id.step_btn_viewstub2);
        }
    }

    private void initView(){
        mButton.setOnClickListener(this);
    }
}
