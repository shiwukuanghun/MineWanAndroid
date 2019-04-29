package com.wujie.minewanandroid.ui.fragment.project;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;


import com.wujie.minewanandroid.R;
import com.wujie.minewanandroid.adapter.ProjectTypeAdapter;
import com.wujie.minewanandroid.bean.ProjectTypeBean;
import com.wujie.minewanandroid.ui.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Time：2019/1/14 0014 下午 16:22
 * Author：WuChen
 * Description：
 **/
public class ProjectFragment extends BaseFragment<ProjectPresenter, ProjectContact.View> implements ProjectContact.View {

    private static final String TAG = "ProjectFragment";

    @BindView(R.id.tbl)
    TabLayout mTbl;
    @BindView(R.id.vp)
    ViewPager mVp;

    private List<ProjectTypeBean> mProjectTypeBeanList;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_project;
    }

    @Override
    protected void init(View view) {
        mProjectTypeBeanList = new ArrayList<>();
        mPresenter.getProjectType();
    }

    @Override
    protected ProjectPresenter createPresenter() {
        return new ProjectPresenter();
    }


    @Override
    public void getProjectType(List<ProjectTypeBean> projectTypeBeanList) {
        if (projectTypeBeanList!=null && projectTypeBeanList.size()>0) {
            mProjectTypeBeanList.addAll(projectTypeBeanList);
            Log.d(TAG, "getProjectType: " +mProjectTypeBeanList.size());
            ProjectTypeAdapter projectTypeAdapter = new ProjectTypeAdapter(getChildFragmentManager(), mProjectTypeBeanList);
            mVp.setAdapter(projectTypeAdapter);
            mTbl.setupWithViewPager(mVp);
        }
    }
}
