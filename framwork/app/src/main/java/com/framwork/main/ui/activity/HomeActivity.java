package com.framwork.main.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.LinearLayout;

import com.framwork.common.ui.activity.BaseFragmentActivity;
import com.framwork.main.R;
import com.framwork.main.ui.contract.HomeContract;
import com.framwork.main.ui.fragment.PersonListFragment;
import com.framwork.main.ui.presenter.HomePresenter;

public class HomeActivity extends BaseFragmentActivity<HomeContract.Presenter> implements HomeContract.View {
    private LinearLayout home_layout_person_add;
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
        home_layout_person_add=findViewById(R.id.home_layout_person_add);
        setFragment();
        setOnClick();
    }
    
    @Override
    protected void initData(@NonNull Bundle bundle) {
    }
    
    private void setFragment(){
        getSupportFragmentManager().beginTransaction().add(R.id.home_fragment_list,new PersonListFragment(), "PersonList").commit();
    }
    
    private void setOnClick(){
        home_layout_person_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(HomeActivity.this,PersonAddActivity.class);
                startActivity(i);
            }
        });
    }
}
