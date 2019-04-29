package com.wujie.minewanandroid.ui.fragment.navigation;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.wujie.minewanandroid.R;
import com.wujie.minewanandroid.adapter.NavigationAdapter;
import com.wujie.minewanandroid.bean.NavigationBean;
import com.wujie.minewanandroid.ui.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.TabView;


/**
 * Created by HuangBin on 2018/11/29 11:59.
 * Description：
 */
public class NavigationFragment extends BaseFragment<NavigationPresenter, NavigationContact.View> implements NavigationContact.View {
    @BindView(R.id.vtl)
    VerticalTabLayout mVtl;
    @BindView(R.id.rv_navigation)
    RecyclerView mRvNavigation;
    @BindView(R.id.ll_container)
    LinearLayout mLlContainer;
    Unbinder unbinder;
    private NavigationAdapter mNavigationAdapter;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_navigation;
    }

    @Override
    protected NavigationPresenter createPresenter() {
        return new NavigationPresenter();
    }

    @Override
    protected void init(View view) {
//        initLoading(mLlContainer);
        List<NavigationBean> list = new ArrayList<>();
        mRvNavigation.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRvNavigation.setLayoutManager(mLayoutManager);
        mNavigationAdapter = new NavigationAdapter(R.layout.item_navigation, list);
        mRvNavigation.setAdapter(mNavigationAdapter);
        mPresenter.getNavigation();

        mRvNavigation.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    int firstVisibleItemPosition = mLayoutManager.findFirstVisibleItemPosition();
                    mVtl.setTabSelected(firstVisibleItemPosition);
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        mVtl.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tab, int position) {
                int firstVisibleItemPosition = mLayoutManager.findFirstVisibleItemPosition();
                int lastVisibleItemPosition = mLayoutManager.findLastVisibleItemPosition();
                if (position < firstVisibleItemPosition) {
                    mRvNavigation.smoothScrollToPosition(position);
                } else if (position <= lastVisibleItemPosition) {
                    int top = mRvNavigation.getChildAt(position - firstVisibleItemPosition).getTop();
                    mRvNavigation.smoothScrollBy(0, top);
                } else {
                    mRvNavigation.smoothScrollToPosition(position);
                }
            }

            @Override
            public void onTabReselected(TabView tab, int position) {
            }
        });
    }

    @Override
    public void getNavigationSuccess(List<NavigationBean> navigationBeanList) {
        if (navigationBeanList != null && navigationBeanList.size() > 0) {
            mNavigationAdapter.setNewData(navigationBeanList);
            mVtl.setTabAdapter(new TabAdapter() {
                @Override
                public int getCount() {
                    return navigationBeanList.size();
                }

                @Override
                public ITabView.TabBadge getBadge(int position) {
                    return null;
                }

                @Override
                public ITabView.TabIcon getIcon(int position) {
                    return null;
                }

                @Override
                public ITabView.TabTitle getTitle(int position) {
                    return new TabView.TabTitle.Builder()
                            .setContent(navigationBeanList.get(position).getName())
                            .setTextColor(getResources().getColor(R.color.shallow_green), ContextCompat.getColor(getContext(), R.color.shallow_grey))
                            .build();
                }

                @Override
                public int getBackground(int position) {
                    return 0;
                }
            });
        }
    }

}
