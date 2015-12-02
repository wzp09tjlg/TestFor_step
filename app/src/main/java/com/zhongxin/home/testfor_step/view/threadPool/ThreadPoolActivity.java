package com.zhongxin.home.testfor_step.view.threadPool;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.zhongxin.home.testfor_step.R;
import com.zhongxin.home.testfor_step.utils.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Wu on 2015/10/20.
 */
public class ThreadPoolActivity extends Activity implements
        View.OnClickListener
{
    private final String TAG = "ThreadPoolActivity";
    //widget
    private Button mButtonCacheThreadPool;
    private Button mButtonFixedThreadPool;
    private Button mButtonScheduleThreadPool;
    private Button mButtonSingleExecutor;
    private Button mButtonOtherExecutor;
    //data
    private ExecutorService executor;
    //interface
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_thread_pool);
        findView();
        initView();
    }

    @Override
    public void onClick(View v) {
      switch (v.getId()){
          case R.id.step_btn_cacheThreadPool:
              doCacheThreadPool();
              break;
          case R.id.step_btn_fixedThreadPool:
              doFixedThreadPool();
              break;
          case R.id.step_btn_scheduleThreadPool:
              doScheduleThreadPool();
              break;
          case R.id.step_btn_singleExecutor:
              doSingleExecutor();
              break;
          case R.id.step_btn_otherExecutor:
              Log.e(TAG,"GET Thread count:" + Thread.activeCount());
              doOtherExecutorTask();
              break;
      }
    }

    private void findView(){
        mButtonCacheThreadPool = (Button) findViewById(R.id.step_btn_cacheThreadPool);
        mButtonFixedThreadPool = (Button) findViewById(R.id.step_btn_fixedThreadPool);
        mButtonScheduleThreadPool = (Button) findViewById(R.id.step_btn_scheduleThreadPool);
        mButtonSingleExecutor = (Button) findViewById(R.id.step_btn_singleExecutor);
        mButtonOtherExecutor = (Button) findViewById(R.id.step_btn_otherExecutor);
    }

    private void initView(){
        mButtonCacheThreadPool.setOnClickListener(this);
        mButtonFixedThreadPool.setOnClickListener(this);
        mButtonScheduleThreadPool.setOnClickListener(this);
        mButtonSingleExecutor.setOnClickListener(this);
        mButtonOtherExecutor.setOnClickListener(this);
    }

    //线程不够，可以增加。当线程叨叨一定数量时，不再增加。如果
    private void doCacheThreadPool(){
        executor = Executors.newCachedThreadPool();
        for(int i = 0;i<50;i++){
            final int index = i;
            try{
                Thread.sleep(100);
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("active couunt:" + Thread.activeCount() +"  index:" + index);
                    try{
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            });

        }
    }

    private void doFixedThreadPool(){
        executor = Executors.newFixedThreadPool(3);
        for(int i = 0;i<50;i++){
            final int index = i;
            try{
                Thread.sleep(100);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }

            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try{
                        System.out.println("index:" + index + " thread count:" + Thread.activeCount());
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private void doScheduleThreadPool(){
       ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);
        for(int i=0;i<50;i++){
           final int index = i;
            try{
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            executor.schedule(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("index:" + index + "  thread count:" + Thread.activeCount());
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, 10, TimeUnit.SECONDS);
//            executor.scheduleAtFixedRate(new Runnable() {
//                @Override
//                public void run() {
//                    try{
//                        System.out.println("index:" + index + " thread count:" + Thread.activeCount());
//                        Thread.sleep(100);
//                    }catch (InterruptedException e){
//                        e.printStackTrace();
//                    }
//                }
//            },1,3,TimeUnit.SECONDS);
        }


    }

    private void doSingleExecutor(){
        executor = Executors.newSingleThreadExecutor();
        for(int i=0;i<50;i++){
            final int index = i;
            try{
                Thread.sleep(100);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
            executor.execute(new Runnable() {
                @Override
                public void run() {
                  try{
                      System.out.println("index:" + index + "  thread count:" + Thread.activeCount());
                      Thread.sleep(1000);
                  }
                  catch (InterruptedException e){
                      e.printStackTrace();
                  }
                }
            });
        }
    }

    private void doOtherExecutorTask(){
        MyRunnable1 myRunnable1 = new MyRunnable1("zhangsan");
        MyRunnable2 myRunnable2 = new MyRunnable2("lisi");
        MyRunnable3 myRunnable3 = new MyRunnable3("wangwu");
        MyRunnable4 myRunnable4 = new MyRunnable4("zhaoliu");
        MyRunnable5 myRunnable5 = new MyRunnable5("sunqi");

        ThreadPool.initialize();
        ThreadPool.execute(myRunnable1);
        ThreadPool.execute(myRunnable2);
        ThreadPool.execute(myRunnable3);
        ThreadPool.execute(myRunnable4);
//        ThreadPool.execute(myRunnable5);
    }

    class MyRunnable1 implements Runnable{
        private String name = "";

        public MyRunnable1(String name){
            this.name = name;
        }

        @Override
        public void run() {
            for(int i=0;i<4;i++){
                try{
                    System.out.println("MyRunnable1   name :" + name + "  i :" + i +"  totalPoolSize :" + Thread.activeCount());
                    Thread.sleep(2000);
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
            }

        }
    }

    class MyRunnable5 implements Runnable{
        private String name = "";

        public MyRunnable5(String name){
            this.name = name;
        }

        @Override
        public void run() {
            for(int i=0;i<20;i++){
                try{
                    System.out.println("MyRunnable5   name :" + name + "  i :" + i +"  totalPoolSize :" + Thread.activeCount());
                    Thread.sleep(3000);
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
            }

        }
    }

    class MyRunnable2 implements Runnable{
        private String name = "";

        public MyRunnable2(String name){
            this.name = name;
        }

        @Override
        public void run() {
            for(int i=0;i<4;i++){
                try{
                    System.out.println("MyRunnable2   name :" + name + "  i :" + i +"  totalPoolSize :" + Thread.activeCount());
                    Thread.sleep(3000);
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
            }

        }
    }

    class MyRunnable3 implements Runnable{
        private String name = "";

        public MyRunnable3(String name){
            this.name = name;
        }

        @Override
        public void run() {
            for(int i=0;i<4;i++){
                try{
                    System.out.println("MyRunnable3   name :" + name + "  i :" + i +"  totalPoolSize :" + Thread.activeCount());
                    Thread.sleep(1000);
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
            }

        }
    }

    class MyRunnable4 implements Runnable{
        private String name = "";

        public MyRunnable4(String name){
            this.name = name;
        }

        @Override
        public void run() {
            for(int i=0;i<4;i++){
                try{
                    System.out.println("MyRunnable4   name :" + name + "  i :" + i +"  totalPoolSize :" + Thread.activeCount());
                    Thread.sleep(1500);
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
            }

        }
    }
}
