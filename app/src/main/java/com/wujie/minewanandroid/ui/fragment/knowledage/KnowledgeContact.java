package com.wujie.minewanandroid.ui.fragment.knowledage;

import com.wujie.minewanandroid.bean.KnowledgeBean;
import com.wujie.minewanandroid.presenter.IPresenter;
import com.wujie.minewanandroid.view.IBaseView;

import java.util.List;

/**
 * Time：2019/1/11 0011 上午 10:55
 * Author：WuChen
 * Description：
 **/
public interface KnowledgeContact {

    interface View extends IBaseView {
        void getKnowledge(List<KnowledgeBean> knowledgeBeanList);
    }

    interface Presenter extends IPresenter<View> {
        void getKnowledge();
    }

}
