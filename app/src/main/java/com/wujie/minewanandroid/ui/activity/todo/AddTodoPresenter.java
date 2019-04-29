package com.wujie.minewanandroid.ui.activity.todo;

import com.wujie.minewanandroid.bean.TodoBean;
import com.wujie.minewanandroid.http.BaseObserver;
import com.wujie.minewanandroid.http.RxRetrofit;
import com.wujie.minewanandroid.presenter.BasePresenter;
import com.wujie.minewanandroid.util.RxHelper;

/**
 * Time：2019/1/23 0023 下午 14:42
 * Author：WuChen
 * Description：
 **/
public class AddTodoPresenter extends BasePresenter<AddTodoContact.View> implements AddTodoContact.Presenter {
    @Override
    public void addTodo(String title, String content, String date, int type, int priority) {
        addDisposable(RxRetrofit.getApi()
                .addTodo(title, content, date, type, priority)
                .compose(RxHelper.rxSchedulderHelper())
                .compose(RxHelper.handleResult2())
                .subscribeWith(new BaseObserver<TodoBean>() {
                    @Override
                    protected void start() {

                    }

                    @Override
                    protected void onSuccess(TodoBean todoBean) {
                        mV.addTodoSuccess(todoBean);
                    }

                    @Override
                    protected void onFailure(int errorCode, String errorMsg) {

                    }
                }));
    }
}
