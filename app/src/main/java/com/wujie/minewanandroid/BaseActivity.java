package com.wujie.minewanandroid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.wujie.minewanandroid.presenter.BasePresenter;
import com.wujie.minewanandroid.view.IBaseView;

import butterknife.ButterKnife;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by HuangBin on 2018/11/16 20:34.
 * Description：
 */

public abstract class BaseActivity<P extends BasePresenter<V>, V extends IBaseView> extends SwipeBackActivity implements IBaseView {

    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
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
}
