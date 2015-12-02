package com.zhongxin.home.testfor_step.utils;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by Walter on 2015/10/21.
 */
public class ReflectUtil {
    private static final String TAG = "ReflectUtil";

    //获得某对象的公共属性
    public static Object getProperty(Object owner,String fieldName) throws Exception{
        Class clazz = owner.getClass();
        Field field = clazz.getField(fieldName);
        Object property = field.get(clazz);
        return property;
    }

    //获取静态的公共属性
    public static Object getStaticProperty(Object owner,String fieldName) throws Exception{
        Class clazz = owner.getClass();
        Field field = clazz.getField(fieldName);
        Object property = field.get(field);
        return property;
    }

    //调用公共的方法
    public static Object invokeMethod(Object owner,String methodName,Object[] args) throws  Exception{
        Class clazz = owner.getClass();
        Class[] argsClass = new Class[args.length];
        for(int i=0;i<args.length;i++){
            argsClass[i] = args[i].getClass();
        }
        Method method = clazz.getMethod(methodName,argsClass);
        return method.invoke(owner,args);
    }

    //调用静态的方法
    public static Object invokeStaticMethod(Object owner,String methodName,Object[] args)throws Exception {
        Class clazz = owner.getClass();
        Class[] argsClass = new Class[args.length];
        for(int i=0;i<argsClass.length;i++){
            argsClass[i] = args[i].getClass();
        }
        Method method = clazz.getMethod(methodName,argsClass);
        return method.invoke(owner,args);
    }

    //创建一个实例
    public static Object getInstance(String className,Object[] args)throws  Exception {
        Class clazz = Class.forName(className);
        Class[] argsClass = new Class[args.length];
        for(int i=0;i<argsClass.length;i++){
            argsClass[i] = args[i].getClass();
        }
        Constructor cons = clazz.getConstructor(argsClass);
        return cons.newInstance(args);
    }

    //是不是某个类的实例
    public static boolean isInstance(Object obj,Class cls){
        return cls.isInstance(obj);
    }

    //得到数组中的某个元素
    public static Object getByArray(Object array,int index){
        return Array.get(array,index);
    }
}
