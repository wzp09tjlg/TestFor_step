package com.zhongxin.home.testfor_step.view.viewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.zhongxin.home.testfor_step.R;
import com.zhongxin.home.testfor_step.view.viewpager.fragment.Fragment1;
import com.zhongxin.home.testfor_step.view.viewpager.fragment.Fragment2;
import com.zhongxin.home.testfor_step.view.viewpager.fragment.Fragment3;
import com.zhongxin.home.testfor_step.view.viewpager.fragment.Fragment4;
import com.zhongxin.home.testfor_step.view.viewpager.fragment.Fragment5;

import java.util.ArrayList;

/**
 * Created by Wu on 2015/11/20.
 */
public class ViewPagerActivity extends FragmentActivity implements
   View.OnClickListener
{
    private static final String TAG = "ViewPagerActivity";
    //widget
    private ViewPager mViewPager;
    //data
    private ArrayList<Fragment> mList;
//    private MyAdapter mMyAdapter;
    private MyFragmentPagerAdapter myAdapter;
    private MyFragmentStatePageAdapter myStateAdapter;
    private Button mButton;
    private Fragment fragment1;
    private Fragment fragment2;
    private Fragment fragment3;
    private Fragment fragment4;
    private Fragment fragment5;
    //interface
    @Override
    public void onClick(View v) {
     if(v.getId() == R.id.step_btn_vp){
         Toast.makeText(this,"this is goto someActivity ",Toast.LENGTH_LONG).show();
     }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_viewpager);
        findView();
        initView();
    }

    private void findView(){
        mViewPager = (ViewPager)this.findViewById(R.id.step_vp_viewpager);
    }

    private void initView(){
        getData();
        myAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        myStateAdapter = new MyFragmentStatePageAdapter(getSupportFragmentManager());
//        mMyAdapter = new MyAdapter();
//        mViewPager.setAdapter(mMyAdapter);
//        mViewPager.setAdapter(myAdapter);
        mViewPager.setAdapter(myStateAdapter);
    }

    private void getData(){
        mList = new ArrayList<Fragment>();
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();
        fragment4 = new Fragment4();
        fragment5 = new Fragment5();
//        View view1= LayoutInflater.from(this).inflate(R.layout.view_viewpager1,null);
//        View view2= LayoutInflater.from(this).inflate(R.layout.view_viewpager1,null);
//        View view3= LayoutInflater.from(this).inflate(R.layout.view_viewpager1,null);
//        mButton = (Button)view3.findViewById(R.id.step_btn_vp);
//        mButton.setOnClickListener(this);
//        mButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(),"this is will enter other activity",Toast.LENGTH_LONG).show();
//            }
//        });
//        mList.add(view1);
//        mList.add(view2);
//        mList.add(view3);
        mList.add(fragment1);
        mList.add(fragment2);
        mList.add(fragment3);
        mList.add(fragment4);
        mList.add(fragment5);
    }
//
//    class MyAdapter extends PagerAdapter{  //some base viewpager adapter
//        @Override
//        public int getCount() {
//            return mList.size();
//        }
//
//        @Override
//        public boolean isViewFromObject(View view, Object object) {
//            return view == object;
//        }
//
//        @Override
//        public Object instantiateItem(ViewGroup container, int position) {
//            ((ViewPager)container).addView(mList.get(position));
//            return mList.get(position);
//        }
//
//        @Override
//        public void destroyItem(ViewGroup container, int position, Object object) {
//            ((ViewPager)container).removeView(mList.get(position));
//        }
//    }

    class MyFragmentPagerAdapter extends FragmentPagerAdapter{  //这个viewpager adapter 是将所有的fragment都放在内存中，不被释放，是比较占用内存的。少数的fragment 可以使用这种处理方式
          //但是有大量的fragment时  这种方式显然就不行。需要使用fragmenntstatepageradapter

        public MyFragmentPagerAdapter(FragmentManager manager){
            super(manager);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return  mList.get(position);
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((Fragment)object).getView();
        }
//
//        @Override
//        public Object instantiateItem(ViewGroup container, int position) {
//            return super.instantiateItem(container, position);
//        }
//
//        @Override
//        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
//        }
    }


    class MyFragmentStatePageAdapter extends FragmentStatePagerAdapter{
        public MyFragmentStatePageAdapter(FragmentManager manager){
           super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mList.get(position);
        }

        @Override
        public int getCount() {
            return mList.size();
        }
    }
}
