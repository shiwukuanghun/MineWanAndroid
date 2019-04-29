package com.wujie.minewanandroid.ui.fragment.todo;


import com.wujie.minewanandroid.bean.PageListDataBean;
import com.wujie.minewanandroid.bean.TodoBean;
import com.wujie.minewanandroid.http.BaseObserver;
import com.wujie.minewanandroid.http.RxRetrofit;
import com.wujie.minewanandroid.presenter.BasePresenter;
import com.wujie.minewanandroid.util.RxHelper;

/**
 * Time：2019/1/23 0023 下午 15:30
 * Author：WuChen
 * Description：
 **/
public class WaitTodoPresenter extends BasePresenter<WaiteTodoContact.View> implements WaiteTodoContact.Presenter {
    @Override
    public void getTodoList(int page, int status, int type, int priority, int orderby) {
        addDisposable(RxRetrofit.getApi()
                .getTodoList(page, status, type, priority, orderby)
                .compose(RxHelper.rxSchedulderHelper())
                .compose(RxHelper.handleResult2())
                .subscribeWith(new BaseObserver<PageListDataBean<TodoBean>>() {
                    @Override
                    protected void start() {

                    }

                    @Override
                    protected void onSuccess(PageListDataBean<TodoBean> pageListDataBean) {
                        mV.getTodoListSuccess(pageListDataBean);
                    }

                    @Override
                    protected void onFailure(int errorCode, String errorMsg) {

                    }
                }));

    }
}
