package com.wujie.minewanandroid.ui.fragment.knowledage;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.wujie.minewanandroid.R;
import com.wujie.minewanandroid.adapter.KnowledgeAdapter;
import com.wujie.minewanandroid.bean.KnowledgeBean;
import com.wujie.minewanandroid.ui.BaseFragment;
import com.wujie.minewanandroid.ui.fragment.BasePresenterFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by HuangBin on 2018/11/29 11:58.
 * Description：
 */
public class KnowledgeFragment extends BaseFragment<KnowledgePresenter, KnowledgeContact.View> implements KnowledgeContact.View {
    @BindView(R.id.rv_knowledge)
    RecyclerView mRvKnowledge;
    @BindView(R.id.msl)
    SmartRefreshLayout mMsl;
    private KnowledgeAdapter mKnowledgeAdapter;

    @Override
    protected KnowledgePresenter createPresenter() {
        return new KnowledgePresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_knowledge;
    }

    @Override
    protected void init(View view) {
        initLoading(mMsl);
        List<KnowledgeBean> knowledgeBeanList = new ArrayList<>();
        mRvKnowledge.setHasFixedSize(true);
        mRvKnowledge.setLayoutManager(new LinearLayoutManager(getContext()));
        mKnowledgeAdapter = new KnowledgeAdapter(R.layout.item_knowledge, knowledgeBeanList);
        mRvKnowledge.setAdapter(mKnowledgeAdapter);
        mPresenter.getKnowledge();
    }

    @Override
    public void getKnowledge(List<KnowledgeBean> knowledgeBeanList) {
        mKnowledgeAdapter.setNewData(knowledgeBeanList);
    }
}
