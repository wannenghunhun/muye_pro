package com.framwork.main.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.framwork.common.ui.activity.BaseFragmentActivity;
import com.framwork.main.R;
import com.framwork.main.ui.contract.PersonAddContract;
import com.framwork.main.ui.presenter.PersonAddPresenter;

public class PersonAddActivity extends BaseFragmentActivity<PersonAddContract.Presenter> implements PersonAddContract.View {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    
    @Override
    protected PersonAddContract.Presenter createPresenter() {
        return new PersonAddPresenter(this);
    }
    
    @Override
    protected int layoutContentId() {
        return R.layout.activity_person_add;
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
        setOnclick();
    }
    
    @Override
    protected void initData(@NonNull Bundle bundle) {
    }
    
    private void setOnclick() {
    }
}
