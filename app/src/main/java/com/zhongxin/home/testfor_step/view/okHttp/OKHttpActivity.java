package com.zhongxin.home.testfor_step.view.okHttp;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.zhongxin.home.testfor_step.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Walter on 2015/10/22.
 */
public class OKHttpActivity extends Activity implements
        View.OnClickListener
{
    private final String TAG = "OKHttpActivity";
    private final String URL_TEXT = "http://publicobject.com/helloworld.txt";
    private final String URL_IMG  = "https://www.baidu.com/img/bdlogo.png";
    private final String URL_FUSHISHAN = "http://baike.baidu.com/picture/6211/5039292/0/bd315c6034a85edf894558394f540923dc5475da.html?fr=lemma&ct=single#aid=0&pic=bd315c6034a85edf894558394f540923dc5475da";
    private final int MSG_IMG1 = 0X1101;
    private final int MSG_IMG2 = 0X1102;
    private final int MSG_IMG3 = 0X1103;
    private final int MSG_IMG4 = 0X1104;
    private final int MSG_IMG5 = 0X1105;
    //widget
    private Button mButtonSync;
    private Button mButtonAsync;
    private ImageView mImageView;
    //data
    private OkHttpClient okHttp;
    private GetTextAsyncCallBack getTextAsyncCallBack;
    private GetImgAsyncCallBack mGetImgAsyncCallBack;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.what){
                case MSG_IMG1:
                    Bitmap bitmap1 = (Bitmap) msg.obj;
                    mImageView.setImageBitmap(bitmap1);
                    break;
                case MSG_IMG2:
                    Bitmap bitmap2 = (Bitmap) msg.obj;
                    mImageView.setImageBitmap(bitmap2);
                    break;
            }
        }
    };
    //interface

    class GetTextAsyncCallBack implements Callback{
        @Override
        public void onFailure(Request request, IOException e) {
            Log.e(TAG,"Failured: " + request.body().toString() );
            Log.e(TAG,"Exception: " + e.toString());
        }

        @Override
        public void onResponse(Response response) throws IOException {
               Log.e(TAG,"Successful: " + response.body().toString());
        }
    }

    class GetImgAsyncCallBack implements Callback{
        @Override
        public void onFailure(Request request, IOException e) {
           Log.e(TAG,"Reques: " + request.body().toString());
            e.printStackTrace();
          mImageView.setImageDrawable(getResources().getDrawable(R.drawable.bg_vertical));
        }

        @Override
        public void onResponse(Response response) throws IOException {
             byte[] bytes = response.body().bytes();
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
            Message msg = Message.obtain();
            msg.what = MSG_IMG2;
            msg.obj = bitmap;
            handler.sendMessage(msg);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_okhttp_framework);
        findView();
        initView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.step_btn_Sync:
//                doSyncGetTextFromNetwork(URL_TEXT);
                doSyncImgFromNetwork(URL_FUSHISHAN);
                break;
            case R.id.step_btn_Async:
//                doAsyncTextFromNetwork(URL_TEXT);
                doAsyncImgFromNetwork(URL_FUSHISHAN);
                break;
        }
    }

    private void findView(){
        mButtonSync = (Button) this.findViewById(R.id.step_btn_Sync);
        mButtonAsync = (Button) this.findViewById(R.id.step_btn_Async);
        mImageView = (ImageView)this.findViewById(R.id.step_img_okhttp1);
    }

    private void initView(){
        mButtonSync.setOnClickListener(this);
        mButtonAsync.setOnClickListener(this);
    }

    private void doSyncGetTextFromNetwork(String url){//无论是同步还是异步操作，都应该在子线程中访问网络
        okHttp = new OkHttpClient();
        final Request request = new  Request.Builder().url(url).build();

            new Thread(){
                @Override
                public void run() {
                    super.run();
                    try{
                    Response response = okHttp.newCall(request).execute();
                    if(response.isSuccessful())
                        Log.e(TAG,"Successful: " + response.body().toString());
                    else
                        Log.e(TAG,"Failured: " + response.body().toString());
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }.start();
    }

    private void doAsyncTextFromNetwork(String url){ //异步操作包含了一个子线程，所以能够执行
        okHttp = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        getTextAsyncCallBack = new GetTextAsyncCallBack();
        okHttp.newCall(request).enqueue(getTextAsyncCallBack);
    }

    private void doSyncImgFromNetwork(String url){ //同步操作都得需要在子线程中操作，不然就会报异常
        okHttp = new OkHttpClient();
        final Request request = new Request.Builder().url(url).build();

            new Thread(){
                @Override
                public void run() {
                    super.run();
                    try{
                    Response response = okHttp.newCall(request).execute();
                        InputStream is = response.body().byteStream();
                    byte[] bytes = readStream(is);
                        Log.e(TAG,"BYTES SIZE: " + bytes.length);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                    Message msg = Message.obtain();
                    msg.what = MSG_IMG1;
                    msg.obj = bitmap;
                    handler.sendMessage(msg);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }.start();
    }

    public static byte[] readStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while( (len=inStream.read(buffer)) != -1){
            outStream.write(buffer, 0, len);
        }
        outStream.close();
        inStream.close();
        return outStream.toByteArray();
    }

    private void doAsyncImgFromNetwork(String url){ //因为是异步操作，所以在异步中存在子线程进行操作
        okHttp = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        mGetImgAsyncCallBack = new GetImgAsyncCallBack();
        okHttp.newCall(request).enqueue(mGetImgAsyncCallBack);
    }
}
