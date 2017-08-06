package com.example.chuangfeng.googleplaytest.ui.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.chuangfeng.googleplaytest.R;
import com.example.chuangfeng.googleplaytest.utils.UIUtils;

/**
 * Created by Administrator on 2017/8/6.
 * 根据当前状态来加载不同的布局
 * - 未加载
 * - 正在加载
 * - 加载失败
 * - 加载成功
 */

public abstract class LoadingPage extends FrameLayout {

    public static final int STATE_LOAD_UNDO = 0;//未加载
    public static final int STATE_LOAD_LOADING = 1;//正在加载
    public static final int STATE_LOAD_EMPTY = 2;//数据为空
    public static final int STATE_LOAD_ERROR = 3;//加载失败
    public static final int STATE_LOAD_SUCCESS = 4;//加载成功

    private int mCurrentState = STATE_LOAD_UNDO; //当前状态，默认未加载

    private View mLoadingView;
    private View mEmptyView;
    private View mErrorView;
    private View mSuccessView;

    public LoadingPage(@NonNull Context context) {
        super(context);
        initView();
    }

    public void initView() {
        //加载中的布局
        if (mLoadingView == null) {
            mLoadingView = onCreateLoadingView();
            addView(mLoadingView);
        }

        //数据为空的布局
        if (mEmptyView == null) {
            mEmptyView = onCreateEmptyView();
            addView(mEmptyView);
        }

        //加载失败的布局
        if (mErrorView == null) {
            mErrorView = onCreateErrorView();
            addView(mErrorView);
        }

        showRightPage();
    }

    private void showRightPage() {
//        if (mCurrentState == STATE_LOAD_LOADING ||
//                mCurrentState == STATE_LOAD_UNDO) {
//            mLoadingView.setVisibility(VISIBLE);
//        } else {
//            mLoadingView.setVisibility(GONE);
//        }

        if (mLoadingView != null) {
            mLoadingView.setVisibility(mCurrentState == STATE_LOAD_LOADING
                    || mCurrentState == STATE_LOAD_UNDO ? VISIBLE : GONE);
        }

        if (mEmptyView != null) {
            mEmptyView.setVisibility(mCurrentState == STATE_LOAD_EMPTY
                    ? VISIBLE : GONE);
        }

        if (mErrorView != null) {
            mErrorView.setVisibility(mCurrentState == STATE_LOAD_ERROR
                    ? VISIBLE : GONE);
        }

        if (mSuccessView == null && mCurrentState == STATE_LOAD_SUCCESS) {
            mSuccessView = onCreateSuccessView();
            if (mSuccessView != null) {
                addView(mSuccessView);
            }
        }

        if (mSuccessView != null) {
            mSuccessView.setVisibility(mCurrentState == STATE_LOAD_SUCCESS
                    ? VISIBLE : GONE);
        }
    }

    /**
     * 正在加载的布局对象
     * @return
     */
    public View onCreateLoadingView() {
        return UIUtils.inflate(R.layout.layout_loading);
    }

    /**
     * 数据为空的布局对象
     * @return
     */
    public View onCreateEmptyView() {
        return UIUtils.inflate(R.layout.layout_empty);
    }

    /**
     * 加载失败的布局对象
     * @return
     */
    public View onCreateErrorView() {

        View view = UIUtils.inflate(R.layout.layout_error);
        Button btnRetry = (Button) view.findViewById(R.id.btn_retry);
        btnRetry.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //重新加载网络数据
                loadData();
            }
        });
        return view;
    }

    /**
     * 加载成功的布局对象，必须由子类实现
     * @return
     */
    public abstract View onCreateSuccessView();

    /**
     * 加载网络数据
     */
    public void loadData() {
        //状态清零（数据为空/加载失败/加载成功）
        if (mCurrentState == STATE_LOAD_EMPTY || mCurrentState == STATE_LOAD_ERROR
                || mCurrentState == STATE_LOAD_SUCCESS || mCurrentState == STATE_LOAD_UNDO) {

        }

        if (mCurrentState == STATE_LOAD_UNDO) {
            //异步加载网络数据
            new Thread(){
                public void run() {
                    final ResultState result = onLoad();

                    UIUtils.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (result != null) {
                                //根据当前状态来显示正确的页面
                                mCurrentState = result.getState();
                                showRightPage();
                            }
                        }
                    });
                }
            }.start();
        }
    }

    /**
     * 加载网络数据，必须由子类实现
     * 1. 加载成功
     * 2. 数据为空
     * 3. 加载失败
     */
    public abstract ResultState onLoad();

    //枚举
    public enum ResultState {
        LOAD_SUCCESS(STATE_LOAD_SUCCESS), LOAD_EMPTY(STATE_LOAD_EMPTY), LOAD_ERROR(STATE_LOAD_ERROR);

        private int state;

        private ResultState(int state) {
            this.state = state;
        }

        public int getState() {
            return state;
        }
    }

}
