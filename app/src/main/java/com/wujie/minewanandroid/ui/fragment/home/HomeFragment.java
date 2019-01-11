package com.wujie.minewanandroid.ui.fragment.home;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wujie.minewanandroid.R;
import com.wujie.minewanandroid.adapter.HomeAdapter;
import com.wujie.minewanandroid.bean.HomeBean;
import com.wujie.minewanandroid.bean.PageListDataBean;
import com.wujie.minewanandroid.ui.fragment.BasePresenterFragment;
import com.wujie.minewanandroid.util.ARouterUtils;
import com.wujie.minewanandroid.widget.StatusLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by HuangBin on 2018/11/29 11:50.
 * Description：
 */
public class HomeFragment extends BasePresenterFragment<HomePresenter, HomeContact.View> implements HomeContact.View {
    @BindView(R.id.rv_home)
    RecyclerView mRvHome;
    @BindView(R.id.status_layout)
    StatusLayout mStatusLayout;
    private HomeAdapter mHomeAdapter;
    private List<HomeBean> mDataList;

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void init(View view) {
        mDataList = new ArrayList<>();
        mRvHome.setHasFixedSize(true);
        mRvHome.setLayoutManager(new LinearLayoutManager(getContext()));
        mHomeAdapter = new HomeAdapter(R.layout.item_home, mDataList);
        mRvHome.setAdapter(mHomeAdapter);

        mHomeAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                mPresenter.collectOrUnCollectArticle(mHomeAdapter.getData().get(position).getId(), position);
            }
        });

        mHomeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ARouter.getInstance().build(ARouterUtils.WebViewPath).withString("WebUrl", mHomeAdapter.getData().get(position).getLink()).navigation();
            }
        });

        mStatusLayout.setOnRetryClickListener(new StatusLayout.OnRetryClickListener() {
            @Override
            public void onRetryClick() {
                mPresenter.getHomeList(0);
                Toast.makeText(getContext(), "点击重连", Toast.LENGTH_SHORT).show();
            }
        });
        mPresenter.getHomeList(0);
    }

    @Override
    public void showLoading(String msg) {
        mStatusLayout.setLoading();
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError() {
        Toast.makeText(getContext(), "加载出错", Toast.LENGTH_LONG).show();
        mStatusLayout.setError();
    }

    @Override
    public void showFailure(String msg) {
        mStatusLayout.setError();
    }

    @Override
    public void showEmpty() {
        mStatusLayout.setEmpty();
    }

    @Override
    public void loadHomeFail(String msg) {
        mStatusLayout.setError();
    }

    @Override
    public void loadHomeData(PageListDataBean<HomeBean> pageListDataBean) {
        mStatusLayout.setContent();
        List<HomeBean> homeBeans = pageListDataBean.getDatas();
        mDataList.clear();
        mDataList.addAll(homeBeans);
        mHomeAdapter.notifyDataSetChanged();
    }

    @Override
    public void collectOrUnCollect(int position) {
        boolean isCollect = mHomeAdapter.getData().get(position).isCollect();
        mHomeAdapter.getData().get(position).setCollect(!isCollect);
        mHomeAdapter.notifyItemChanged(position, "payload");
    }

    @Override
    public void collectFailure(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }


   /* @Override
    public void onRetryClick() {
        mPresenter.getHomeList(0);
        Toast.makeText(getContext(), "点击重连", Toast.LENGTH_SHORT).show();
    }*/
}
