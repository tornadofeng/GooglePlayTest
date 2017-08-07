package com.example.chuangfeng.googleplaytest.ui.fragment;

import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.chuangfeng.googleplaytest.R;
import com.example.chuangfeng.googleplaytest.ui.adapter.MyBaseAdapter;
import com.example.chuangfeng.googleplaytest.ui.holder.BaseHolder;
import com.example.chuangfeng.googleplaytest.ui.holder.HomeHolder;
import com.example.chuangfeng.googleplaytest.ui.widget.LoadingPage;
import com.example.chuangfeng.googleplaytest.utils.UIUtils;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/8/5.
 */

public class HomeFragment extends BaseFragment {

    private ArrayList<String> mList = new ArrayList<>();

    //网络数据加载成功之后才调用该方法
    @Override
    public View onCreateSuccessView() {
        ListView view = new ListView(UIUtils.getContext());

        initData();

        view.setAdapter(new HomeAdapter(mList));

        return view;
    }

    @Override
    public LoadingPage.ResultState onLoad() {
        return LoadingPage.ResultState.LOAD_SUCCESS;
    }

    /**
     * 初始化数据
     */
    private void initData() {
        for (int i = 0; i < 20; i++) {
            mList.add("测试数据" + i);
        }

    }

    class HomeAdapter extends MyBaseAdapter<String> {

        public HomeAdapter(ArrayList<String> list) {
            super(list);
        }

//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            ViewHolder holder;
//            if (convertView == null) {
//                //1. 加载item的布局对象
//                convertView = UIUtils.inflate(R.layout.list_item_home);
//
//                //2. findviewbyid，初始化各控件
//                holder = new ViewHolder();
//                holder.tvContent = (TextView) convertView.findViewById(R.id.tv_content);
//
//                //3. 设置tag
//                convertView.setTag(holder);
//            } else {
//                holder = (ViewHolder) convertView.getTag();
//            }
//
//            //4. 刷新控件数据
//            holder.tvContent.setText(getItem(position));
//
//            return convertView;
//        }

        @Override
        public BaseHolder<String> getHolder(int position) {
            return new HomeHolder();
        }

        @Override
        public ArrayList<String> onLoadMore() {
            ArrayList<String> moreData = new ArrayList<String>();
            for (int i = 0; i < 20; i++) {
                moreData.add("更多测试数据" + i);
            }
            SystemClock.sleep(2000);
            return moreData;
        }
    }

//    static class ViewHolder {
//        public TextView tvContent;
//    }

}
