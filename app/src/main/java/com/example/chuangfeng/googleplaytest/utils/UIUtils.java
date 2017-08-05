package com.example.chuangfeng.googleplaytest.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.View;

import com.example.chuangfeng.googleplaytest.global.BaseApplication;

/**
 * Created by Administrator on 2017/8/5.
 */

public class UIUtils {

    public static Context getContext() {
        return BaseApplication.getContext();
    }

    public static Handler getHandler() {
        return BaseApplication.getHandler();
    }

    public static int getMainThreadId() {
        return BaseApplication.getMainThreadId();
    }

    //加载字符串
    public static String getString(int id) {
        return getContext().getResources().getString(id);
    }

    //加载字符串数组
    public static String[] getStringArray(int id) {
        return getContext().getResources().getStringArray(id);
    }

    //加载图片
    public static Drawable getDrawable(int id) {
        return getContext().getResources().getDrawable(id);
    }

    //加载颜色
    public static int getColor(int id) {
        return getContext().getResources().getColor(id);
    }

    //加载颜色状态选择器
    public static ColorStateList getColorStateList(int id) {
        return getContext().getResources().getColorStateList(id);
    }

    //加载尺寸
    public static int getDimen(int id) {
        return getContext().getResources().getDimensionPixelSize(id);
    }

    ///////////////////// dip转px,px转dip////////////////////////////
    // dp = px / 设备密度
    public static int dip2px(float dip) {
        float density = getContext().getResources().getDisplayMetrics().density; //设备密度
        int px = (int) (dip * density + 0.5f); // 加0.5f是四舍五入效果
        return px;
    }

    public static float px2dip(int px) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return px / density;
    }

    ///////////////////通过布局文件加载布局对象/////////////////////////
    public static View inflate(int layoutId) {
        return View.inflate(getContext(), layoutId, null);
    }

    ////////////////////判断当前是否在主线程运行////////////////////////
    public static boolean isRunOnUiThread() {
        //获取当前线程id，如果当前线程id等于主线程id，那就说明当前是在主线程
        return android.os.Process.myTid() == getMainThreadId();
    }

    //运行在主线程
    public static void runOnUiThread(Runnable runnable) {
        //判断当前是否主线程，如果是，就直接运行
        if (isRunOnUiThread()) {
            //当前就是主线程
            runnable.run();
        } else {
            //不是主线程，需要运行在主线程
            //handler除了发送Message之外，也可以发送一个Runnable对象，也是运行在主线程的
            getHandler().post(runnable);
        }
    }
}
