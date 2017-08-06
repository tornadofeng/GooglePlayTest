package com.example.chuangfeng.googleplaytest.ui.fragment;

import android.view.View;
import android.widget.TextView;

import com.example.chuangfeng.googleplaytest.ui.widget.LoadingPage;
import com.example.chuangfeng.googleplaytest.utils.UIUtils;

/**
 * Created by Administrator on 2017/8/5.
 */

public class HomeFragment extends BaseFragment {

    @Override
    public View onCreateSuccessView() {
        TextView view = new TextView(UIUtils.getContext());
        view.setText(getClass().getSimpleName());
        return view;
    }

    @Override
    public LoadingPage.ResultState onLoad() {
        return LoadingPage.ResultState.LOAD_SUCCESS;
    }
}
