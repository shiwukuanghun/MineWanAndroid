package com.wujie.minewanandroid.ui.activity.login;

import com.wujie.minewanandroid.presenter.IPresenter;
import com.wujie.minewanandroid.view.IBaseView;

public interface LoginContact {

    interface View extends IBaseView {
        void loginSuccess();
    }

    interface Presenter extends IPresenter<View> {
        void login(String username, String password);
    }

}
