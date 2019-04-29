package com.wujie.minewanandroid.ui.fragment.todo;

import com.wujie.minewanandroid.bean.PageListDataBean;
import com.wujie.minewanandroid.bean.TodoBean;
import com.wujie.minewanandroid.presenter.IPresenter;
import com.wujie.minewanandroid.view.IBaseView;

/**
 * Time：2019/1/23 0023 下午 15:28
 * Author：WuChen
 * Description：
 **/
public interface WaiteTodoContact {

    interface View extends IBaseView {
        void getTodoListSuccess(PageListDataBean<TodoBean> pageListDataBean);
    }

    interface Presenter extends IPresenter<View> {
        void getTodoList(int page, int status, int type, int priority, int orderby);
    }

}
