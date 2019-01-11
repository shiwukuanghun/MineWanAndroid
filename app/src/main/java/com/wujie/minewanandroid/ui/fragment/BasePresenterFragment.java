package com.wujie.minewanandroid.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.wujie.minewanandroid.presenter.BasePresenter;
import com.wujie.minewanandroid.ui.BaseFragment;
import com.wujie.minewanandroid.view.IBaseView;


/**
 * Created by HuangBin on 2018/11/29 21:27.
 * Description：
 */
public abstract class BasePresenterFragment<P extends BasePresenter<V>, V extends IBaseView> extends BaseFragment implements IBaseView {

    protected P mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        attachView();
    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        mPresenter = createPresenter();
//        attachView();
//    }

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

    protected abstract P createPresenter();

    @Override
    public void onDestroy() {
        super.onDestroy();
        detachView();
    }


    @Override
    public void showLoading(String msg) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showFailure(String msg) {

    }

    @Override
    public void showEmpty() {

    }
}
