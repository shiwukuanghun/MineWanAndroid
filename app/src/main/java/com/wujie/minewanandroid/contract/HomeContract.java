package com.wujie.minewanandroid.contract;

import com.wujie.minewanandroid.bean.HomeBean;
import com.wujie.minewanandroid.view.IBaseView;

import java.util.List;

/**
 * Created by HuangBin on 2018/11/16 12:40.
 * Description：
 */

public interface HomeContract {

    interface IHomePresenter {
        void getHomeList();
    }

    interface IHomeView extends IBaseView {
        void setHomeData(List<HomeBean> dataList);
    }

}
