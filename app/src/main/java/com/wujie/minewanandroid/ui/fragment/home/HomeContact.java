package com.wujie.minewanandroid.ui.fragment.home;

import com.wujie.minewanandroid.bean.HomeBean;
import com.wujie.minewanandroid.bean.PageListDataBean;
import com.wujie.minewanandroid.presenter.IPresenter;
import com.wujie.minewanandroid.view.IBaseView;

/**
 * Created by HuangBin on 2018/11/29 21:34.
 * Description：
 */
public interface HomeContact {

    interface View extends IBaseView {

        void loadHomeFail(String msg);

        void loadHomeData(PageListDataBean<HomeBean> pageListDataBean);

        void collectOrUnCollect(int position);

        void collectFailure(String msg);

    }

    interface Presenter extends IPresenter<View> {

        void getHomeList(int page);

        void collectOrUnCollectArticle(int id, int position);

    }

}
