package com.xinyang.read.ui.topic;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xinyang.read.R;
import com.xinyang.read.entity.TopicBean;

import java.util.List;

public class TopicAdapter extends BaseQuickAdapter<TopicBean, BaseViewHolder> {
    public TopicAdapter(int layoutResId, @Nullable List<TopicBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TopicBean item) {
        //标题
        helper.setText(R.id.tv_title, item.getTitle());
        //描述
        helper.setText(R.id.tv_summary, item.getSummary());
        //时间
        helper.setText(R.id.tv_time, item.getUpdatedAt());
        //发布媒体
        helper.setText(R.id.tv_info, item.getNewsArray().get(0).getSiteName());
    }
}
