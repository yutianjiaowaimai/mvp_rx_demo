package com.xinyang.read.ui.main;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.xinyang.read.R;
import com.xinyang.read.base.BaseActivity;
import com.xinyang.read.ui.common.CommonFragment;
import com.xinyang.read.ui.girlphoto.PhotosMainActivity;
import com.xinyang.read.ui.topic.TopicFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View, NavigationView.OnNavigationItemSelectedListener {


    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tab)
    TabLayout mTab;
    @BindView(R.id.vp)
    ViewPager mVp;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    private String[] mTabs;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected MainPresenter getPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void init() {
        initToolbar();
        initDrawerLayout();
        initTab();
        initVp();
    }


    @Override
    public void initToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(R.string.app_name);
        mFab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,PhotosMainActivity.class));
            }
        });
    }

    @Override
    public void initDrawerLayout() {
        mNavView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void initTab() {
        mTabs = getResources().getStringArray(R.array.tab_item);
        for (String tab : mTabs) {
            mTab.addTab(mTab.newTab().setText(tab));
        }
        mTab.setupWithViewPager(mVp);
        mTab.setTabMode(TabLayout.MODE_FIXED);
    }

    @Override
    public void initVp() {
        List<Fragment> list = new ArrayList<>();
        list.add(new TopicFragment());
        list.add(CommonFragment.newInstance(CommonFragment.NEWS));
        list.add(CommonFragment.newInstance(CommonFragment.TECH));
        list.add(CommonFragment.newInstance(CommonFragment.BLOCKCHAIN));

        MainVpAdapter adapter = new MainVpAdapter(getSupportFragmentManager(), mTabs, list);
        mVp.setAdapter(adapter);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_camera:

                break;
            case R.id.nav_gallery:

                break;
            default:
        }
        return false;
    }

}
