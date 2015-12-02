package com.zhongxin.home.testfor_step.utils;

/**
 * Created by Walter on 2015/11/4.
 */
public class StaticUtil {

    private final String TAG = "StaticUtil";

    public static class SubStaticUtil{

        public void someMethod(){
            System.out.println("StaticUtil ---> SubStaticUtil  -->someMethod( this method is not static )");
        }

        public static void someStaticMethod(){
            System.out.println("StatisUtil ---> SubStaticUtil --> someStaticMethod(this method is static methood)");
        }

        protected  void someProtectedMethod(){
            System.out.println("StaticUtil ---> SubStaticUtil  -->someProtectedMethod( this method is not static )");
        }

        protected  static void someProtectedStaticMethod(){
            System.out.println("StatisUtil ---> SubStaticUtil --> someProtectedStaticMethod(this method is static methood)");
        }

        private void somePrivateMethod(){
            System.out.println("StaticUtil ---> SubStaticUtil  -->somePrivateMethod( this method is not static )");
        }

        private static void somePrivateStaticMethod(){
            System.out.println("StatisUtil ---> SubStaticUtil --> somePrivateStaticMethod(this method is static methood)");
        }
    }
}
