package com.wujie.minewanandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.RelativeLayout;

import com.wujie.minewanandroid.adapter.HomeAdapter;
import com.wujie.minewanandroid.bean.BannerBean;
import com.wujie.minewanandroid.bean.BaseBean;
import com.wujie.minewanandroid.bean.HomeBean;
import com.wujie.minewanandroid.bean.PageListDataBean;
import com.wujie.minewanandroid.http.ApiServer;
import com.wujie.minewanandroid.http.RxRetrofit;
import com.wujie.minewanandroid.util.Constant;
import com.wujie.minewanandroid.util.RxHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    @BindView(R.id.rv)
    RecyclerView mRv;
    @BindView(R.id.activity_home)
    RelativeLayout mActivityHome;

    private List<HomeBean> mDataList;
    private HomeAdapter mHomeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        mDataList = new ArrayList<>();

        mRv.setLayoutManager(new LinearLayoutManager(this));
        mRv.setHasFixedSize(true);
//        mHomeAdapter = new HomeAdapter(mDataList);
        mRv.setAdapter(mHomeAdapter);

        RxRetrofit.createApi(ApiServer.class, Constant.BaseUrl)
                .getHomeList(0)
                .compose(RxHelper.<BaseBean<PageListDataBean<HomeBean>>>rxSchedulderHelper())
                .subscribe(new Observer<BaseBean<PageListDataBean<HomeBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBean<PageListDataBean<HomeBean>> value) {
                        Log.d(TAG, "onNext: 请求成功");
                        PageListDataBean<HomeBean> data = value.getData();
                        List<HomeBean> datas = data.getDatas();
                        mDataList.addAll(datas);
                        mHomeAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        RxRetrofit.createApi(ApiServer.class, Constant.BaseUrl)
                .getBanner()
                .compose(RxHelper.<BaseBean<List<BannerBean>>>rxSchedulderHelper())
                .subscribe(new Observer<BaseBean<List<BannerBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBean<List<BannerBean>> value) {
                        Log.d(TAG, "onNext: 成功" + value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
