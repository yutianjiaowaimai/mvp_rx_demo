package com.xinyang.read.ui.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xinyang.read.R;
import com.xinyang.read.base.BaseFragment;
import com.xinyang.read.constant.Constant;
import com.xinyang.read.entity.NewsBean;
import com.xinyang.read.ui.main.NewsDetailActivity;

import java.util.List;

import butterknife.BindView;

public class CommonFragment extends BaseFragment<CommonPresenter> implements CommonContract.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.RequestLoadMoreListener {

    public static final String NEWS = "news";
    public static final String TECH = "technews";
    public static final String BLOCKCHAIN = "blockchain";


    @BindView(R.id.rv)
    RecyclerView mRv;
    @BindView(R.id.srl)
    SwipeRefreshLayout mSrl;

    private String mType;
    private CommonAdapter mAdapter;

    public static CommonFragment newInstance(String type) {
        CommonFragment fragment = new CommonFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.NEWS_TYPE, type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (null != bundle && !TextUtils.isEmpty(bundle.getString(Constant.NEWS_TYPE))) {
            mType = bundle.getString(Constant.NEWS_TYPE);
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    protected CommonPresenter getPresenter() {
        return new CommonPresenter(mType);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list;
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
        mAdapter = new CommonAdapter(R.layout.item_topic,null);
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
        intent.putExtra("url",((NewsBean)adapter.getItem(position)).getMobileUrl());
        startActivity(intent);
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.loadMore((long) mAdapter.getItem(mAdapter.getItemCount() - 2).getId(),0);
    }
}
