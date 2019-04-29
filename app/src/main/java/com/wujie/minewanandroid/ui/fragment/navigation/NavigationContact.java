package com.wujie.minewanandroid.ui.fragment.navigation;


import com.wujie.minewanandroid.bean.NavigationBean;
import com.wujie.minewanandroid.presenter.IPresenter;
import com.wujie.minewanandroid.view.IBaseView;

import java.util.List;

/**
 * Time：2019/1/25 0025 下午 18:09
 * Author：WuChen
 * Description：
 **/
public interface NavigationContact {

    interface View extends IBaseView {
        void getNavigationSuccess(List<NavigationBean> navigationBeanList);
    }

    interface Presenter extends IPresenter<View> {
        void getNavigation();
    }

}
