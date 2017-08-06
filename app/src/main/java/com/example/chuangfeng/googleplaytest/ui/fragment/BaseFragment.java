package com.example.chuangfeng.googleplaytest.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chuangfeng.googleplaytest.ui.widget.LoadingPage;
import com.example.chuangfeng.googleplaytest.utils.UIUtils;

/**
 * Created by Administrator on 2017/8/5.
 */

public abstract class BaseFragment extends Fragment {

    private LoadingPage mLoadingPage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        TextView view = new TextView(UIUtils.getContext());
//        view.setText(getClass().getSimpleName());
        mLoadingPage = new LoadingPage(UIUtils.getContext()) {
            @Override
            public View onCreateSuccessView() {
                return BaseFragment.this.onCreateSuccessView();
            }

            @Override
            public ResultState onLoad() {
                return BaseFragment.this.onLoad();
            }
        };
        return mLoadingPage;
    }

    //该方法初始化加载成功的布局，由子类去实现
    public abstract View onCreateSuccessView();

    //加载网络数据，由子类实现
    public abstract LoadingPage.ResultState onLoad();

    public void loadData() {
        if (mLoadingPage != null) {
            mLoadingPage.loadData();
        }
    }
}
