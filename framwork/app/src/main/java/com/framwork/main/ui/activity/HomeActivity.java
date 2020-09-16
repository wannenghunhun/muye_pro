package com.framwork.main.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.framwork.common.ui.activity.BaseFragmentActivity;
import com.framwork.main.R;
import com.framwork.main.ui.contract.HomeContract;
import com.framwork.main.ui.presenter.HomePresenter;

public class HomeActivity extends BaseFragmentActivity<HomeContract.Presenter> implements HomeContract.View {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    
    @Override
    protected HomeContract.Presenter createPresenter() {
        return new HomePresenter(this);
    }
    
    @Override
    protected int layoutContentId() {
        return R.layout.activity_home;
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    
    @Override
    protected void loadAgain() {
    
    }
    
    @Override
    protected void loadData() {
    }
    
    @Override
    protected void initView() {
    }
    
    @Override
    protected void initData(@NonNull Bundle bundle) {
    }
    
    
}
