package com.wujie.minewanandroid.ui.fragment.project;

import com.wujie.minewanandroid.bean.ProjectTypeBean;
import com.wujie.minewanandroid.presenter.IPresenter;
import com.wujie.minewanandroid.view.IBaseView;

import java.util.List;

/**
 * Time：2019/1/14 0014 下午 17:38
 * Author：WuChen
 * Description：
 **/
public interface ProjectContact {

    interface View extends IBaseView {
        void getProjectType(List<ProjectTypeBean> projectTypeBeanList);
    }

    interface Presenter extends IPresenter<View> {
        void getProjectType();
    }

}
