package com.zhongxin.home.testfor_step.utils;

import android.util.Log;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Walter on 2015/10/21.
 */
public final class ThreadPool {
    private static final String TAG = "ThreadPool";
    private static ThreadPoolExecutor executor = null;  //饿汉模式 和 饱汉模式 区别是是否在一开始进行赋值

    public static void initialize(){
        //核心线程3个 最大线程5个如果一个线程执行时间超过5秒就终止
        executor = new ThreadPoolExecutor(3,5,5, TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>());
        //调用了allowCoreThreadTimeOut(boolean)方法，在线程池中的线程数不大于corePoolSize时，keepAliveTime参数也会起作用，直到线程池中的线程数为0
        executor.allowCoreThreadTimeOut(true);
    }

    public static void execute(Runnable runnable){
        if(executor == null)
            initialize();
        Log.e(TAG,"线程池中线程的数目：" + executor.getPoolSize() + " 队列中等待的执行任务的数目："
                + executor.getQueue().size() + " 已经执行完的任务数目：" + executor.getCompletedTaskCount());
        executor.execute(runnable);
    }

    public void shutDown(){
        if(executor != null)
            executor.shutdown();
    }

    /*
    *     private static final String TAG = "ThreadPool";
	private static ThreadPoolExecutor executor = null;

    public static void initialize() {
        //核心线程数5个，最大线程数10个，如果一个线程空闲超过30秒则终止
        executor = new ThreadPoolExecutor(5, 10,
                30, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

        executor.allowCoreThreadTimeOut(true);
    }

    /**
     * 往线程池中添加任务
     * @param runnable 任务

    public static void execute(Runnable runnable) {
        Log.i(TAG, "线程池中线程数目：" + executor.getPoolSize() + "，队列中等待执行的任务数目：" +
                executor.getQueue().size() + "，已执行完别的任务数目：" + executor.getCompletedTaskCount());
        executor.execute(runnable);
    }

    public static void shutdown() {
        executor.shutdown();
    }
    * */
}
