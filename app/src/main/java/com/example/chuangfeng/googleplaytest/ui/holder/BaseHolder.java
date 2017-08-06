package com.example.chuangfeng.googleplaytest.ui.holder;

import android.view.View;

/**
 * Created by Administrator on 2017/8/6.
 */

public abstract class BaseHolder<T> {

    private final View mRootView;
    private T data;

    public BaseHolder() {
        mRootView = initView();

        //3. 设置tag
        mRootView.setTag(this);
    }

    /**
     * 1. 初始化item的布局
     * 2. findViewById
     */
    public abstract View initView();

    public View getRootView() {
        return mRootView;
    }

    //获取数据
    public T getData() {
        return data;
    }

    /**
     * 设置数据
     */
    public void setData(T data) {
        this.data = data;
        //一旦有了数据，就马上刷新
        refreshView(data);
    }

    /**
     * 4. 刷新控件数据
     * @param data
     */
    public abstract void refreshView(T data);
}
