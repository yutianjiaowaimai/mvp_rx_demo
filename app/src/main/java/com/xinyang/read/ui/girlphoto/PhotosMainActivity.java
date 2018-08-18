package com.xinyang.read.ui.girlphoto;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xinyang.read.R;
import com.xinyang.read.base.BaseActivity;
import com.xinyang.read.entity.PhotoGirl;

import java.util.List;

import butterknife.BindView;

public class PhotosMainActivity extends BaseActivity<PhotosListPresenter> implements PhotoListContract.View, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.rv_photo)
    RecyclerView mRv;
    @BindView(R.id.srl)
    SwipeRefreshLayout mSrl;

    private PhotpAdapter mAdapter;
    private static int SIZE = 20;
    private int mStartPage = 1;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_photos_main;
    }

    @Override
    protected PhotosListPresenter getPresenter() {
        return new PhotosListPresenter();
    }

    @Override
    protected void init() {
        initSrl();
        initRv();
        mPresenter.getPhotosListDataRequest(false,SIZE, mStartPage);
    }

    @Override
    public void initSrl() {
        mSrl.setOnRefreshListener(this);
    }

    @Override
    public void initRv() {
        mAdapter = new PhotpAdapter(R.layout.item_photo,null);
        mAdapter.setOnItemClickListener(this);
        mAdapter.setOnLoadMoreListener(this,mRv);
        mRv.setLayoutManager(new GridLayoutManager(this,2));
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
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent(this, PhotosDetailActivity.class);
        intent.putExtra("url",((PhotoGirl)adapter.getItem(position)).getUrl());
        startActivity(intent);
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.loadMore((long)1,mStartPage++);
    }

    @Override
    public void onRefresh() {
        mStartPage = 1;
        mPresenter.onRefresh();
    }
}
