package com.zhongxin.home.testfor_step.view.volley;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.LruCache;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.zhongxin.home.testfor_step.R;
import com.zhongxin.home.testfor_step.bean.PersonBean;

import org.json.JSONObject;

/**
 * Created by Walter on 2015/10/22.
 */
public class VolleyActivity extends Activity implements
        View.OnClickListener
{
    private final String TAG = "VolleyActivity";
    private final String URL_JSON = "http://www.weather.com.cn/data/sk/101010100.html";
    private final String URL_IMG  = "http://images.cnblogs.com/cnblogs_com/liuning8023/588559/o_Android.jpg";
    private final int MSG_CALLBACK = 0X1101;
    private final int MSG_OTHER    = 0X1102;
    //widget
    private Button mButtonGetJson;
    private Button mButtonGetImg;
    private ImageView mImageView1;
    private ImageView mImageView2;
    private NetworkImageView mImageView3;
    //data
    private RequestQueue queue;  //请求队列  一般只需要一个，如果创建多个的法 耗费资源
    private JsonObjectRequest objectRequest;//简单的object的请求
    private ImageRequest imageRequest;   //图片的request请求
    private GetJsonErrResponse getJsonErrResponse; //getJson非正常的返回回调
    private GetJsonResponse getJsonResponse;   //getJson正常的返回回调
    private GetImgResponse getImgResponse;    //getImg正常返回回调
    private GetImgErrResponse getImgErrResponse; //getImg非正常返回回调
    private int maxWidth;
    private int maxHeght;
    private final PersonBean bean = new PersonBean();
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MSG_CALLBACK:
                    mImageView1.setImageBitmap((Bitmap)msg.obj);
                    break;
                case MSG_OTHER:
                    break;
            }
        }
    };
    //interface
    class  GetJsonResponse implements Response.Listener<JSONObject>{
        @Override
        public void onResponse(JSONObject jsonObject) {
            Log.e(TAG,"Response:" + jsonObject.toString());
        }
    }

    class GetJsonErrResponse implements Response.ErrorListener{
        @Override
        public void onErrorResponse(VolleyError volleyError) {
            Log.e(TAG,"ErrResponse:" + volleyError.toString());
        }
    }

    class GetImgResponse implements Response.Listener<android.graphics.Bitmap>{
        @Override
        public void onResponse(Bitmap bitmap) {
          Message msg = Message.obtain();
          msg.what = MSG_CALLBACK;
          msg.obj = bitmap;
          handler.sendMessage(msg);
        }
    }

    class GetImgErrResponse implements Response.ErrorListener{
        @Override
        public void onErrorResponse(VolleyError volleyError) {
            Log.e(TAG,"ErrResponse:" + volleyError.toString());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_volley_framework);
        findView();
        initView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.step_btn_getJson:
                getJsonFromNetwork(URL_JSON);
                break;
            case R.id.step_btn_getImg:
                getImgFromNetworkByCallBack(URL_IMG);//  通过回调来处理图片
                getImageFromNetworkByImageLoader(URL_IMG);// 使用imageLoader来处理图片
                getImageFromNetworkByNetworkImageView(URL_IMG);//使用networkImageView 来显示图片
                break;
        }
    }

    private void findView(){
        mButtonGetJson = (Button) findViewById(R.id.step_btn_getJson);
        mButtonGetImg  = (Button) findViewById(R.id.step_btn_getImg);
        mImageView1 = (ImageView) findViewById(R.id.step_img_volley_data1);
        mImageView2 = (ImageView) findViewById(R.id.step_img_volley_data2);
        mImageView3 = (NetworkImageView) findViewById(R.id.step_img_volley_data3);
    }

    private void initView(){
        mButtonGetJson.setOnClickListener(this);
        mButtonGetImg.setOnClickListener(this);
    }

    private void getJsonFromNetwork(String url){
      queue = Volley.newRequestQueue(this);
        getJsonResponse = new GetJsonResponse();
        getJsonErrResponse = new GetJsonErrResponse();
      objectRequest = new JsonObjectRequest(Request.Method.GET,url,getJsonResponse,getJsonErrResponse);
      queue.add(objectRequest);
    }

    private void getImgFromNetworkByCallBack(String url){
        queue = Volley.newRequestQueue(this);
        getImgResponse = new GetImgResponse();
        getImgErrResponse = new GetImgErrResponse();
        imageRequest = new ImageRequest(url,getImgResponse,maxWidth,maxHeght, ImageView.ScaleType.CENTER_CROP, Bitmap.Config.ARGB_8888,getImgErrResponse);
        queue.add(imageRequest);
    }

    private void getImageFromNetworkByImageLoader(String url){
       queue = Volley.newRequestQueue(this);
        final LruCache<String,Bitmap> mImageCache = new LruCache<String,Bitmap>(20);
        ImageCache imageCache =new ImageCache() {
            @Override
            public Bitmap getBitmap(String s) {
                return mImageCache.get(s);
            }

            @Override
            public void putBitmap(String s, Bitmap bitmap) {
                mImageCache.put(s,bitmap);
            }
        };

        ImageLoader imageLoader = new ImageLoader(queue,imageCache);
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(mImageView2,R.drawable.bg_horizonal,R.drawable.bg_vertical);
        imageLoader.get(url,listener);
    }

    private void getImageFromNetworkByNetworkImageView(String url){
        queue = Volley.newRequestQueue(this);
        final LruCache<String,Bitmap> mImageCache = new LruCache<String,Bitmap>(20);
        ImageCache imageCache =new ImageCache() {
            @Override
            public Bitmap getBitmap(String s) {
                return mImageCache.get(s);
            }

            @Override
            public void putBitmap(String s, Bitmap bitmap) {
                mImageCache.put(s,bitmap);
            }
        };

        ImageLoader imageLoader = new ImageLoader(queue,imageCache);
        mImageView3.setTag("url");
        mImageView3.setImageUrl(url,imageLoader);
    }

    private void doSomeRemeberImageView(String url){
        //使用callback回调方式去访问数据
      /*  queue = Volley.newRequestQueue(this);//什么请求队列
        imageRequest = new ImageRequest(url,getImgResponse,maxWidth,maxWidth, ImageView.ScaleType.CENTER_CROP, Bitmap.Config.ARGB_8888,getImgErrResponse);
        queue.add(imageRequest); */

        //使用ImageLoader的方式去加载数据
       /* queue = Volley.newRequestQueue(this); //请求的队列
        final LruCache<String,Bitmap> lruCache = new LruCache<String,Bitmap>(10); //图片缓存的map
        ImageCache imageCache = new ImageCache() {  //图片缓存
            @Override
            public Bitmap getBitmap(String s) {
                return lruCache.get(s);
            }

            @Override
            public void putBitmap(String s, Bitmap bitmap) {
               lruCache.put(s,bitmap);
            }
        };
        ImageLoader imageLoader = new ImageLoader(queue,imageCache); //图片加载loader
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(mImageView2,R.drawable.bg_horizonal,R.drawable.bg_horizonal);//创建一个图片加载回调
        imageLoader.get(url,listener); //进行图片加载加载处理 */

        //使用NetworkImageView 来加载图片
       /* queue = Volley.newRequestQueue(this); //请求队列
        final LruCache<String,Bitmap> lruCache = new LruCache<String,Bitmap>(10);//缓存map
        ImageCache imageCache = new ImageCache() { //图片缓存
            @Override
            public Bitmap getBitmap(String s) {
                return lruCache.get(s);
            }

            @Override
            public void putBitmap(String s, Bitmap bitmap) {
               lruCache.put(s,bitmap);
            }
        };
        ImageLoader imageLoader = new ImageLoader(queue,imageCache);
        mImageView3.setTag("url");
        mImageView3.setImageUrl(url,imageLoader);*/
    }
}
