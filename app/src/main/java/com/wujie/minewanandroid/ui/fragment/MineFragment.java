package com.wujie.minewanandroid.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.alibaba.android.arouter.launcher.ARouter;
import com.wujie.minewanandroid.R;
import com.wujie.minewanandroid.bean.HttpsRequest;
import com.wujie.minewanandroid.http.ApiServer;
import com.wujie.minewanandroid.http.RxRetrofit;
import com.wujie.minewanandroid.util.ARouterUtils;
import com.wujie.minewanandroid.util.Constant;
import com.wujie.minewanandroid.util.RxHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by HuangBin on 2018/11/29 12:00.
 * Description：
 */
public class MineFragment extends Fragment {

    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_https)
    Button mBtnHttps;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.btn_login, R.id.btn_https})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                ARouter.getInstance().build(ARouterUtils.LoginPath).navigation();
                break;
            case R.id.btn_https:
                //Latitude=39.922705&Longitude=116.416636&start=0&productName=华为&limit=10
                HttpsRequest httpsRequest = new HttpsRequest();
                httpsRequest.setLatitude("39.922705");
                httpsRequest.setLongitude("116.416636");
                httpsRequest.setStart(0);
                httpsRequest.setProductName("华为");
                httpsRequest.setLimit(10);
                RxRetrofit.createApi(ApiServer.class, Constant.BaseUrl2)
//                        .getReuslt("39.922705", "116.416636", 0, "华为", 10)
                        .getResult(httpsRequest)
                        .compose(RxHelper.rxSchedulderHelper())
                        .subscribe(new Observer<Object>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(Object value) {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
                break;
        }
    }
}
