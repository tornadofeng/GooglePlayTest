package com.example.chuangfeng.googleplaytest.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.chuangfeng.googleplaytest.ui.holder.BaseHolder;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/8/6.
 */

public abstract class MyBaseAdapter<T> extends BaseAdapter {

    private ArrayList<T> list;

    public MyBaseAdapter(ArrayList<T> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public T getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        BaseHolder holder;
        if (convertView == null) {
            //1. 加载item的布局对象
            //2. findViewById，初始化各个控件
            //3. 设置tag
            holder = getHolder(position);
        } else {
            holder = (BaseHolder) convertView.getTag();
        }

        //4. 刷新控件数据
        holder.setData(getItem(position));
        return holder.getRootView();
    }

    /**
     * 返回holder对象，必须由子类实现
     * @return
     */
    public abstract BaseHolder<String> getHolder(int position);
}
