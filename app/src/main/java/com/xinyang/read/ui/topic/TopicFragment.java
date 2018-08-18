package com.xinyang.read.ui.topic;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xinyang.read.R;
import com.xinyang.read.base.BaseFragment;
import com.xinyang.read.entity.TopicBean;
import com.xinyang.read.ui.main.NewsDetailActivity;

import java.util.List;

import butterknife.BindView;

public class TopicFragment extends BaseFragment<TopicPresenter> implements TopicContract.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.rv)
    RecyclerView mRv;
    @BindView(R.id.srl)
    SwipeRefreshLayout mSrl;

    private TopicAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list;
    }

    @Override
    protected TopicPresenter getPresenter() {
        return new TopicPresenter();
    }

    @Override
    protected void init() {
        initSrl();
        initRv();
        mPresenter.getListData(false,null);
    }

    @Override
    public void initSrl() {
        mSrl.setOnRefreshListener(this);
    }

    @Override
    public void initRv() {
        mAdapter = new TopicAdapter(R.layout.item_topic,null);
        mAdapter.setOnItemClickListener(this);
        mAdapter.setOnLoadMoreListener(this,mRv);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        mRv.setLayoutManager(new LinearLayoutManager(mActivity));
        mRv.setAdapter(mAdapter);
    }

    @Override
    public void showRefreshLoading() {
        mSrl.setRefreshing(true);
    }

    @Override
    public void hideRefreshLoading() {
        mSrl.setRefreshing(false);
    }

    @Override
    public void setData(List list, int loadType) {
        setLoadDataResult(mAdapter,mSrl,list,loadType);
    }

    @Override
    public void onRefresh() {
        mPresenter.onRefresh();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

        Intent intent = new Intent(getActivity(),NewsDetailActivity.class);
        intent.putExtra("url",((TopicBean)adapter.getItem(position)).getNewsArray().get(0).getMobileUrl());
        startActivity(intent);

    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.loadMore((long) mAdapter.getItem(mAdapter.getItemCount() - 2).getOrder(),0);
    }
}
