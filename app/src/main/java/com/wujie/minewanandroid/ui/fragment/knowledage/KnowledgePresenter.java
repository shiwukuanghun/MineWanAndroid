package com.wujie.minewanandroid.ui.fragment.knowledage;

import com.wujie.minewanandroid.bean.KnowledgeBean;
import com.wujie.minewanandroid.http.BaseObserver;
import com.wujie.minewanandroid.http.RxRetrofit;
import com.wujie.minewanandroid.presenter.BasePresenter;
import com.wujie.minewanandroid.util.RxHelper;

import java.util.List;

/**
 * Time：2019/1/11 0011 上午 10:57
 * Author：WuChen
 * Description：
 **/
public class KnowledgePresenter extends BasePresenter<KnowledgeContact.View> implements KnowledgeContact.Presenter {
    @Override
    public void getKnowledge() {
        addDisposable(RxRetrofit.getApi()
                .getKnowledge()
                .compose(RxHelper.rxSchedulderHelper())
                .compose(RxHelper.handleResult2())
                .subscribeWith(new BaseObserver<List<KnowledgeBean>>() {
                    @Override
                    protected void start() {
                        mV.showLoading("");
                    }

                    @Override
                    protected void onSuccess(List<KnowledgeBean> knowledgeBeans) {
                        mV.hideLoading();
                        mV.getKnowledge(knowledgeBeans);
                    }

                    @Override
                    protected void onFailure(int errorCode, String errorMsg) {
                        mV.showFailure("");
                    }
                }));
    }
}
