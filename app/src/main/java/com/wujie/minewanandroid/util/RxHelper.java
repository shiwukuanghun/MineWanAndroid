package com.wujie.minewanandroid.util;

import com.wujie.minewanandroid.bean.BaseBean;
import com.wujie.minewanandroid.bean.HomeBean;
import com.wujie.minewanandroid.bean.PageListDataBean;
import com.wujie.minewanandroid.http.NetConfig;
import com.wujie.minewanandroid.http.OtherException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by HuangBin on 2018/11/15 09:48.
 * Description：
 */

public class RxHelper {

    public static <T> ObservableTransformer<T, T> rxSchedulderHelper() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static <T> ObservableTransformer<BaseBean<T>, T> handleResult() {
        return new ObservableTransformer<BaseBean<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<BaseBean<T>> upstream) {
                return upstream.flatMap(new Function<BaseBean<T>, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(BaseBean<T> tBaseBean) throws Exception {
                        if (tBaseBean.getErrorCode() == NetConfig.REQUEST_SUCCESS && tBaseBean.getData()!=null) {
                            return createData(tBaseBean.getData());
                        } else {
                            return Observable.error(new OtherException());
                        }
                    }
                });
            }
        };
    }

    public static <T> ObservableTransformer<BaseBean<T>, T> handleResult2() {
        return httpResponseObservable ->
                httpResponseObservable.flatMap((Function<BaseBean<T>, Observable<T>>) baseResponse -> {
                    if(baseResponse.getErrorCode() == 0
                            && baseResponse.getData() != null) {
                        return createData(baseResponse.getData());
                    } else {
                        return Observable.error(new OtherException(baseResponse.getErrorCode(), baseResponse.getErrorMsg()));
                    }
                });
    }

    public static <T> ObservableTransformer<BaseBean<T>, T> handleCollectResult() {
        return new ObservableTransformer<BaseBean<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<BaseBean<T>> upstream) {
                return upstream.flatMap(new Function<BaseBean<T>, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(BaseBean<T> tBaseBean) throws Exception {
                        if (tBaseBean.getErrorCode() == NetConfig.REQUEST_SUCCESS) {
                            PageListDataBean<HomeBean> pageListDataBean = new PageListDataBean<>();
                            tBaseBean.setData((T) pageListDataBean);
                            return createData(tBaseBean.getData());
                        } else {
                            return Observable.error(new OtherException(tBaseBean.getErrorCode(), tBaseBean.getErrorMsg()));
                        }
                    }
                });
            }
        };
    }

    public static <T> Observable<T> createData(T t) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> e) throws Exception {
                try {
                    e.onNext(t);
                    e.onComplete();
                } catch (Exception e1) {
                    e.onError(e1);
                }
            }
        });
    }

}
