package com.wujie.minewanandroid.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wujie.minewanandroid.R;
import com.wujie.minewanandroid.bean.HomeBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HuangBin on 2018/11/16 11:35.
 * Description：
 */

public class HomeAdapter extends BaseQuickAdapter<HomeBean, BaseViewHolder> {
    public HomeAdapter(int layoutResId, @Nullable List<HomeBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBean item) {
        helper.setText(R.id.tv_author, item.getAuthor() + "")
                .setText(R.id.tv_title, item.getTitle())
                .setText(R.id.tv_type, item.getChapterName())
                .addOnClickListener(R.id.iv_collect);
        ((ImageView)helper.getView(R.id.iv_collect)).setImageResource(item.isCollect()?R.mipmap.icon_collect_yes:R.mipmap.icon_collect_no);
    }

    /*private List<HomeBean> mDataList;

    public HomeAdapter(List<HomeBean> dataList) {
        mDataList = dataList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        HomeBean homeBean = mDataList.get(position);
        viewHolder.mTvAuthor.setText(homeBean.getAuthor()+"");
        viewHolder.mTvTitle.setText(homeBean.getTitle());
        viewHolder.mTvType.setText(homeBean.getChapterName());
    }

    @Override
    public int getItemCount() {
        return mDataList==null?0:mDataList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_author)
        TextView mTvAuthor;
        @BindView(R.id.tv_title)
        TextView mTvTitle;
        @BindView(R.id.tv_type)
        TextView mTvType;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }*/
}
