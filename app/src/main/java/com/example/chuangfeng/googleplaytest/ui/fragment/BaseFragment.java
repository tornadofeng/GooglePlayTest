package com.example.chuangfeng.googleplaytest.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chuangfeng.googleplaytest.utils.UIUtils;

/**
 * Created by Administrator on 2017/8/5.
 */

public class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView view = new TextView(UIUtils.getContext());
        view.setText(getClass().getSimpleName());
        return view;
    }
}
