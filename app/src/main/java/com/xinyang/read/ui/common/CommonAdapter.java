package com.xinyang.read.ui.common;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xinyang.read.R;
import com.xinyang.read.entity.NewsBean;

import java.util.List;

public class CommonAdapter extends BaseQuickAdapter<NewsBean, BaseViewHolder> {
    public CommonAdapter(int layoutResId, @Nullable List<NewsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsBean item) {
        //标题
        helper.setText(R.id.tv_title, item.getTitle());
        //描述
        helper.setText(R.id.tv_summary, item.getSummary());
        //时间
        helper.setText(R.id.tv_time, item.getPublishDate());
        //发布媒体
        helper.setText(R.id.tv_info, item.getSiteName());
    }
}
