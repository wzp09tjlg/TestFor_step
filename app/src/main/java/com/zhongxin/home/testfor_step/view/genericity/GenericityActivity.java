package com.zhongxin.home.testfor_step.view.genericity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.zhongxin.home.testfor_step.R;
import com.zhongxin.home.testfor_step.listener.GenericityInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Walter on 2015/10/25.
 */
public class GenericityActivity extends Activity implements
        View.OnClickListener,
        GenericityInterface<String,Integer>
{
    private final String TAG = "GenericityActivity";
    //widget
    private Button mButtonGenericityClass;
    private Button mButtonGenericityInterface;
    private Button mButtonGenericityMethod;
    private Button mButtonGenericityUpBound;
    private Button mButtonGenericityDownBound;
    //data
    private GenericityActivity genericityActivity;
    private Apple apple;
    private Fruit fruit;
    private SmallApple smallApple;
    //interface
    @Override
    public void doSomeInterfaceMethod(String s, Integer integer) {
       Log.e(TAG,"THIS IS A INTERFACE :  s:" +s + "  integer:" + integer);
    }

    class GenericityClass<T,V>{
        private T t;
        private V v;

        public T getT() {
            return t;
        }

        public void setT(T t) {
            this.t = t;
        }

        public V getV() {
            return v;
        }

        public void setV(V v) {
            this.v = v;
        }

        //在自己创建构造函数的同事一定需要记住 创建一个空的构造函数。因为自己创建了构造函数 系统自带的构造函数就不会自动创建了
        public GenericityClass(){}

        public GenericityClass(T t,V v){
            this.t = t;
            this.v = v;
        }

        @Override
        public String toString() {
            return "T:" + t.toString() + "  V:" +  v.toString();
        }
    }

    class Fruit{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Fruit(){}

        public Fruit(String name){
            this.name = name;
        }

        @Override
        public String toString() {
            return "Fruit: name:" + this.name;
        }
    }

    class Apple extends Fruit{
        private String color;

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public Apple(){}

        public Apple(String name,String color){
            super(name);
            this.color = color;
        }

        @Override
        public String toString() {
            return "Apple: name:" + getName() + "  color:" +this.color;
        }
    }

    class SmallApple extends Apple{
        private int size;

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public SmallApple(){}

        public SmallApple(String name,String color,int size){
            super(name,color);
            this.size = size;
        }

        @Override
        public String toString() {
            return super.toString() + "  size:" + size;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_genericity);
        findView();
        initView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.step_btn_genericity_class:
                GenericityClass<String,Integer> genericityClass = new GenericityClass<>();
                genericityClass.setT("fruitfruitfruit");
                genericityClass.setV(123123);
                Log.e(TAG,"GenericityClass: " + genericityClass.toString());
                break;
            case R.id.step_btn_genericity_interface:
                genericityActivity = new GenericityActivity();
                genericityActivity.doSomeInterfaceMethod("interfaceinterface",10000);
                break;
            case R.id.step_btn_genericity_method:
                testSomeGenericityMethod("methodmethod",10086);
                break;
            case R.id.step_btn_genericity_up_bound:  //向上转型
                List<SmallApple> listSmallApple = new ArrayList<>();
                smallApple = new SmallApple();
                listSmallApple.add(smallApple);
                List<? extends Apple> list = listSmallApple;  //这里是对上的转型
//                apple = new Apple();
//                fruit = new Fruit();
//                smallApple = new SmallApple();
//                list.add(apple);  //向上转 是不允许修改的
//                list.add(smallApple);
//                list.add(fruit);
                break;
            case R.id.step_btn_genericity_down_bound: //向下转型
                List<? super Apple> list1 = new ArrayList<>();  //这里存放的是 Apple 的子类 向下的转型
                apple = new Apple();
                fruit = new Fruit();
                smallApple = new SmallApple();
                list1.add(apple);
//                list1.add(fruit);   //
                list1.add(smallApple);
                break;
        }
    }

    private void findView(){
       mButtonGenericityClass = (Button)findViewById(R.id.step_btn_genericity_class);
        mButtonGenericityInterface = (Button)findViewById(R.id.step_btn_genericity_interface);
        mButtonGenericityMethod = (Button)findViewById(R.id.step_btn_genericity_method);
        mButtonGenericityUpBound = (Button)findViewById(R.id.step_btn_genericity_up_bound);
        mButtonGenericityDownBound = (Button)findViewById(R.id.step_btn_genericity_down_bound);
    }

    private void initView(){
        mButtonGenericityClass.setOnClickListener(this);
        mButtonGenericityInterface.setOnClickListener(this);
        mButtonGenericityMethod.setOnClickListener(this);
        mButtonGenericityUpBound.setOnClickListener(this);
        mButtonGenericityDownBound.setOnClickListener(this);
    }

    private <T,V> void testSomeGenericityMethod(T t,V v){ //泛型的方法 是在前边声明泛型的，泛型类是在后边声明的，这是区别，应该记住的
        Log.e(TAG,"GenericityMethod: t:" + t.toString() + "  v:" + v.toString());
    }
}
