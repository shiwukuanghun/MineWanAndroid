package com.wujie.minewanandroid.ui.fragment.project;

import com.wujie.minewanandroid.bean.PageListDataBean;
import com.wujie.minewanandroid.bean.ProjectItemBean;
import com.wujie.minewanandroid.presenter.IPresenter;
import com.wujie.minewanandroid.view.IBaseView;

/**
 * Time：2019/1/15 0015 上午 11:44
 * Author：WuChen
 * Description：
 **/
public interface ProjectListContact {

    interface View extends IBaseView {
        void getProjectList(PageListDataBean<ProjectItemBean> pageListDataBean);
    }

    interface Presenter extends IPresenter<View> {
        void getProjectList(int cid);
    }

}
