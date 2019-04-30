package com.wujie.minewanandroid;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wujie.minewanandroid.loading.LoadingController;
import com.wujie.minewanandroid.loading.LoadingInterface;
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
    protected LoadingController mLoadingController;

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

    protected void initLoading(View view) {
        mLoadingController = new LoadingController.Builder(this, view)
//                .setLoadingImageResource(R.drawable.common_loading_frame_anim)
                .setLoadingMessage("加载中...")
                .setErrorMessage("网络不给力")
//                .setEmptyViewImageResource(getEmptyImg())
//                .setErrorImageResoruce(R.mipmap.net_error)
                .setEmptyMessage(getEmptyMsg())
                .setOnNetworkErrorRetryClickListener(new LoadingInterface.OnClickListener() {
                    @Override
                    public void onClick() {

                    }
                })
                .setOnErrorRetryClickListener("点我重试", new LoadingInterface.OnClickListener() {
                    @Override
                    public void onClick() {
                        retry();
                    }
                }) .setOnEmptyTodoClickListener(getEmptyTodoText(), new LoadingInterface.OnClickListener() {
                    @Override
                    public void onClick() {
                        emptyTodo();
                    }
                })
                .build();
    }

    protected void retry() {}

    protected String getEmptyMsg() {
        return "未找到相关内容";
    }

//    protected int getEmptyImg(){
//        return R.mipmap.search_empty;
//    }

    protected String getEmptyTodoText() {
        return null;
    }

    protected void emptyTodo() {}

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
