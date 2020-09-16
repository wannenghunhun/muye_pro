package com.framwork.main.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.framwork.common.ui.activity.BaseFragmentActivity;
import com.framwork.main.R;
import com.framwork.main.ui.contract.LoginContract;
import com.framwork.main.ui.presenter.LoginPresenter;

public class LoginActivity extends BaseFragmentActivity<LoginContract.Presenter> implements LoginContract.View {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    
    @Override
    protected LoginContract.Presenter createPresenter() {
        return new LoginPresenter(this);
    }
    
    @Override
    protected int layoutContentId() {
        return R.layout.activity_login;
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
