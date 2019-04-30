package com.wujie.minewanandroid;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.wujie.minewanandroid.presenter.BasePresenter;
import com.wujie.minewanandroid.view.IBaseView;

import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by HuangBin on 2018/11/16 20:34.
 * Description：
 */

public abstract class BaseActivity<P extends BasePresenter<V>, V extends IBaseView> extends SwipeBackActivity implements IBaseView {

    protected P mPresenter;
    protected Context mContext;
    protected CompositeDisposable mCompositeDisposable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        mContext = this;
        setSwipeBackEnable(true);
        mPresenter = createPresenter();
        if (null != mPresenter) {
            mPresenter.attachView((V) this);
        }
        init();
    }

    protected abstract int getLayoutId();

    protected abstract P createPresenter();

    protected abstract void init();

    protected void addDisposable(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    protected void clearDisposable() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        clearDisposable();
        if (null != mPresenter) {
            mPresenter.detachView();
            mPresenter.detachView();
        }
    }


    @Override
    public void showLoading(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showFailure(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showEmpty() {

    }

    protected boolean isEmpty(TextView textView) {
        return TextUtils.isEmpty(textView.getText().toString().trim());
    }

    protected String getText(TextView textView) {
        return textView.getText().toString().trim();
    }
}
