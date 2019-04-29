package com.wujie.minewanandroid.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.wujie.minewanandroid.bean.ProjectTypeBean;
import com.wujie.minewanandroid.ui.fragment.project.ProjectListFragment;

import java.util.List;

/**
 * Time：2019/1/15 0015 上午 11:18
 * Author：WuChen
 * Description：
 **/
public class ProjectTypeAdapter extends FragmentStatePagerAdapter {
    private List<ProjectTypeBean> mProjectTypeBeanList;

    public ProjectTypeAdapter(FragmentManager fm, List<ProjectTypeBean> projectTypeBeanList) {
        super(fm);
        mProjectTypeBeanList = projectTypeBeanList;
    }

    @Override
    public Fragment getItem(int i) {
        return ProjectListFragment.instantiate(mProjectTypeBeanList.get(i).getId());
    }

    @Override
    public int getCount() {
        return mProjectTypeBeanList == null ? 0 : mProjectTypeBeanList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mProjectTypeBeanList.get(position).getName();
    }
}
