package com.framwork.main.ui.fragment;

import android.view.View;

import com.framwork.common.ui.fragment.BaseRefreshFragment;
import com.framwork.main.R;
import com.framwork.main.ui.contract.PersonListContract;
import com.framwork.main.ui.presenter.PersonListPresenter;

public class PersonListFragment extends BaseRefreshFragment<PersonListContract.Presenter> implements PersonListContract.View {
    @Override
    protected void initView(View view) {
        setEnableRefresh(true);
    }
    
    @Override
    protected int layoutContentId() {
        return R.layout.fragment_person_list;
    }
    
    @Override
    protected PersonListContract.Presenter createPresenter() {
        return new PersonListPresenter(this);
    }
    
    @Override
    protected void initData() {
    
    }
    
    @Override
    protected void loadData() {
    
    }
    
    @Override
    protected void loadAgain() {
    
    }
}
