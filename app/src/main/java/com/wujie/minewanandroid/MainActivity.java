package com.wujie.minewanandroid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.wujie.minewanandroid.adapter.HomeAdapter;
import com.wujie.minewanandroid.bean.HomeBean;
import com.wujie.minewanandroid.ui.fragment.knowledage.KnowledgeFragment;
import com.wujie.minewanandroid.ui.fragment.MineFragment;
import com.wujie.minewanandroid.ui.fragment.NavigationFragment;
import com.wujie.minewanandroid.ui.fragment.home.HomeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private static final String TAG = "MainActivity";
//    @BindView(R.id.rv)
//    RecyclerView mRv;
    @BindView(R.id.activity_main)
LinearLayout mActivityMain;
    @BindView(R.id.fl_container)
    FrameLayout mFlContainer;
    @BindView(R.id.rb_home)
    RadioButton mRbHome;
    @BindView(R.id.rb_knowledge)
    RadioButton mRbKnowledge;
    @BindView(R.id.rb_navigation)
    RadioButton mRbNavigation;
    @BindView(R.id.rb_mine)
    RadioButton mRbMine;
    @BindView(R.id.rg_container)
    RadioGroup mRgContainer;

    private List<HomeBean> mDataList;
    private HomeAdapter mHomeAdapter;
    private HomeFragment mHomeFragment;
    private KnowledgeFragment mKnowledgeFragment;
    private NavigationFragment mNavigationFragment;
    private MineFragment mMineFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mRgContainer.setOnCheckedChangeListener(this);
        mRgContainer.check(R.id.rb_home);

        mDataList = new ArrayList<>();
//
//        mRv.setLayoutManager(new LinearLayoutManager(this));
//        mRv.setHasFixedSize(true);
//        mHomeAdapter = new HomeAdapter(mDataList);
//        mRv.setAdapter(mHomeAdapter);

//        RxRetrofit.createApi(ApiServer.class, Constant.BaseUrl)
//                .getHomeList(0)
//                .compose(RxHelper.<BaseBean<PageListDataBean<HomeBean>>>rxSchedulderHelper())
//                .subscribe(new Observer<BaseBean<PageListDataBean<HomeBean>>>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(BaseBean<PageListDataBean<HomeBean>> value) {
//                        Log.d(TAG, "onNext: 请求成功");
//                        PageListDataBean<HomeBean> data = value.getData();
//                        List<HomeBean> datas = data.getDatas();
//                        mDataList.addAll(datas);
//                        mHomeAdapter.notifyDataSetChanged();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//
//        RxRetrofit.createApi(ApiServer.class, Constant.BaseUrl)
//                .getBanner()
//                .compose(RxHelper.<BaseBean<List<BannerBean>>>rxSchedulderHelper())
//                .subscribe(new Observer<BaseBean<List<BannerBean>>>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(BaseBean<List<BannerBean>> value) {
//                        Log.d(TAG, "onNext: 成功" + value);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideAllFragment(transaction);
        switch (checkedId) {
            case R.id.rb_home:
                if (mHomeFragment == null) {
                    mHomeFragment = new HomeFragment();
                    transaction.add(R.id.fl_container, mHomeFragment, "0");
                } else {
                    transaction.show(mHomeFragment);
                }
                break;
            case R.id.rb_knowledge:
                if (mKnowledgeFragment == null) {
                    mKnowledgeFragment = new KnowledgeFragment();
                    transaction.add(R.id.fl_container, mKnowledgeFragment, "1");
                } else {
                    transaction.show(mKnowledgeFragment);
                }
                break;
            case R.id.rb_navigation:
                if (mNavigationFragment == null) {
                    mNavigationFragment = new NavigationFragment();
                    transaction.add(R.id.fl_container, mNavigationFragment, "2");
                } else {
                    transaction.show(mKnowledgeFragment);
                }
                break;
            case R.id.rb_mine:
                if (mMineFragment == null) {
                    mMineFragment = new MineFragment();
                    transaction.add(R.id.fl_container, mMineFragment, "3");
                } else {
                    transaction.show(mHomeFragment);
                }
                break;
        }
        transaction.commit();
    }

    private void hideAllFragment(FragmentTransaction transaction) {
        if (mHomeFragment != null) {
            transaction.hide(mHomeFragment);
        }
        if (mKnowledgeFragment != null) {
            transaction.hide(mKnowledgeFragment);
        }
        if (mNavigationFragment != null) {
            transaction.hide(mNavigationFragment);
        }
        if (mMineFragment != null) {
            transaction.hide(mMineFragment);
        }
    }

    @Override
    public void onAttachFragment(Fragment fragment) {//解决Fragment重影问题
        super.onAttachFragment(fragment);
        if (mHomeFragment == null && fragment instanceof HomeFragment) {
            mHomeFragment = (HomeFragment) fragment;
        } else if (mKnowledgeFragment == null && fragment instanceof KnowledgeFragment) {
            mKnowledgeFragment = (KnowledgeFragment) fragment;
        } else if (mNavigationFragment == null && fragment instanceof  NavigationFragment) {
            mNavigationFragment = (NavigationFragment) fragment;
        }else if (mMineFragment == null && fragment instanceof MineFragment) {
            mMineFragment = (MineFragment) fragment;
        }
    }

}
