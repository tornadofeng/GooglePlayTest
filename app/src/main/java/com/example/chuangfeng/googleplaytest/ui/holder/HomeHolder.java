package com.example.chuangfeng.googleplaytest.ui.holder;

import android.view.View;
import android.widget.TextView;

import com.example.chuangfeng.googleplaytest.R;
import com.example.chuangfeng.googleplaytest.utils.UIUtils;

/**
 * Created by Administrator on 2017/8/6.
 */

public class HomeHolder extends BaseHolder<String> {

    private TextView tvContent;

    @Override
    public View initView() {
        View view = UIUtils.inflate(R.layout.list_item_home);
        tvContent = (TextView) view.findViewById(R.id.tv_content);
        return view;
    }

    @Override
    public void refreshView(String data) {
        tvContent.setText(data);
    }
}
