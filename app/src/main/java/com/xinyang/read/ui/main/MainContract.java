package com.xinyang.read.ui.main;

import com.xinyang.read.base.BaseContract;

public interface MainContract {

    interface View extends BaseContract.View {

        void initToolbar();

        void initDrawerLayout();

        void initTab();

        void initVp();
    }

    interface Presenter extends BaseContract.Presenter<View> {

        void getTabList();

    }
}
