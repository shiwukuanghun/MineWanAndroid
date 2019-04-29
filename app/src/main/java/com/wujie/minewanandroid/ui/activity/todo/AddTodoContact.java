package com.wujie.minewanandroid.ui.activity.todo;


import com.wujie.minewanandroid.bean.TodoBean;
import com.wujie.minewanandroid.presenter.IPresenter;
import com.wujie.minewanandroid.view.IBaseView;

/**
 * Time：2019/1/23 0023 下午 14:31
 * Author：WuChen
 * Description：
 **/
public interface AddTodoContact {

    interface View extends IBaseView {
        void addTodoSuccess(TodoBean todoBean);
    }

    interface Presenter extends IPresenter<View> {
        void addTodo(String title, String content, String date, int type, int priority);
    }

}
