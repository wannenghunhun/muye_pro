package com.framwork.main.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.framwork.common.ui.activity.BaseFragmentActivity;
import com.framwork.main.R;
import com.framwork.main.ui.contract.SettingContract;
import com.framwork.main.ui.presenter.SettingPresenter;

public class SettingActivity extends BaseFragmentActivity<SettingContract.Presenter> implements SettingContract.View {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    
    @Override
    protected SettingContract.Presenter createPresenter() {
        return new SettingPresenter(this);
    }
    
    @Override
    protected int layoutContentId() {
        return R.layout.activity_setting;
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
