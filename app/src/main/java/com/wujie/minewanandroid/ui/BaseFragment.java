package com.wujie.minewanandroid.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.wujie.minewanandroid.R;
import com.wujie.minewanandroid.loading.LoadingController;
import com.wujie.minewanandroid.presenter.BasePresenter;
import com.wujie.minewanandroid.view.IBaseView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by HuangBin on 2018/11/29 21:17.
 * Description：
 */
public abstract class BaseFragment<P extends BasePresenter<V>, V extends IBaseView> extends Fragment implements IBaseView {

    protected P mPresenter;
    protected LoadingController mLoadingController;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, view);
        init(view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        attachView();
    }

    private void attachView() {
        if (mPresenter != null) {
            mPresenter.attachView((V) this);
        }
    }

    private void detachView() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    protected abstract int getLayoutId();

    protected abstract P createPresenter();

    protected abstract void init(View view);

    @Override
    public void onDestroy() {
        super.onDestroy();
        detachView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    protected void initLoading(View view) {
        mLoadingController = new LoadingController.Builder(getContext(), view)
                .setLoadingImageResource(R.drawable.loading_frame_anim)
                .setLoadingMessage("加载中...")
                .setErrorMessage("网络不给力")
//                .setEmptyViewImageResource(getEmptyImg())
//                .setErrorImageResoruce(R.mipmap.net_error)
                .setEmptyMessage(getEmptyMsg())
                .setOnNetworkErrorRetryClickListener(() -> {

                })
                .setOnErrorRetryClickListener("点我重试", () -> retry())
                .setOnEmptyTodoClickListener(getEmptyTodoText(), () -> emptyTodo())
                .build();
    }

    protected void retry(){}

    protected String getEmptyMsg() {
        return "未找到相关内容";
    }

    protected String getEmptyTodoText() {
        return null;
    }

    protected void emptyTodo() {}

    @Override
    public void showLoading(String msg) {
        mLoadingController.showLoading();
    }

    @Override
    public void hideLoading() {
        mLoadingController.dismissLoading();
    }

    @Override
    public void showError() {
        mLoadingController.showError();
    }

    @Override
    public void showFailure(String msg) {
        mLoadingController.showNetworkError();
    }

    @Override
    public void showEmpty() {
        mLoadingController.showEmpty();
    }
}
