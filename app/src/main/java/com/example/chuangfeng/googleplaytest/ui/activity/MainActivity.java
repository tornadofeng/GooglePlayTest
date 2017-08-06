package com.example.chuangfeng.googleplaytest.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.chuangfeng.googleplaytest.R;
import com.example.chuangfeng.googleplaytest.ui.fragment.BaseFragment;
import com.example.chuangfeng.googleplaytest.ui.fragment.FragmentFactory;
import com.example.chuangfeng.googleplaytest.ui.widget.PagerTab;
import com.example.chuangfeng.googleplaytest.utils.UIUtils;

public class MainActivity extends BaseActivity {

    private PagerTab mPagerTab;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPagerTab = (PagerTab) findViewById(R.id.pager_tab);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        mViewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        //将viewpager和页签自定义控件关联在一起（注意：此方法必须在viewpager设置完数据之后在调用）
        mPagerTab.setViewPager(mViewPager);

        //如果viewpage和PageTab绑定在一起，那么监听事件就要设置给PagerTab
        mPagerTab.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //当页面被选中，加载当前页面的数据
                BaseFragment fragment = (BaseFragment) FragmentFactory.createFragment(position);
                fragment.loadData();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    class MyAdapter extends FragmentPagerAdapter {

        private final String[] mTabNames;

        public MyAdapter(FragmentManager fm) {
            super(fm);
            mTabNames =  UIUtils.getStringArray(R.array.tab_names);
        }

        //获取页面标题
        @Override
        public CharSequence getPageTitle(int position) {
            return mTabNames[position];
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = FragmentFactory.createFragment(position);
            return fragment;
        }

        @Override
        public int getCount() {
            return mTabNames.length;
        }
    }

}
