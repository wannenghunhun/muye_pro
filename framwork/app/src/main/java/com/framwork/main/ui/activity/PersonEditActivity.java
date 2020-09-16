package com.framwork.main.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.framwork.common.ui.activity.BaseFragmentActivity;
import com.framwork.main.R;
import com.framwork.main.ui.contract.PersonAddContract;
import com.framwork.main.ui.contract.PersonEditContract;
import com.framwork.main.ui.presenter.PersonAddPresenter;
import com.framwork.main.ui.presenter.PersonEditPresenter;

public class PersonEditActivity extends BaseFragmentActivity<PersonEditContract.Presenter> implements PersonEditContract.View {
    private TextView login_tv_login, login_tv_setting;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    
    @Override
    protected PersonEditContract.Presenter createPresenter() {
        return new PersonEditPresenter(this);
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
        login_tv_login = findViewById(R.id.login_tv_login);
        login_tv_setting = findViewById(R.id.login_tv_setting);
        setOnclick();
    }
    
    @Override
    protected void initData(@NonNull Bundle bundle) {
    }
    
    private void setOnclick() {
        login_tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //                SuccessDialog successDialog = new SuccessDialog(LoginActivity.this, "shishi");
                //                successDialog.show();
//                OutDialog outDialog = new OutDialog(LoginActivity.this, new OutDialog.OnConfirmClickListener() {
//                    @Override
//                    public void onConfirmClick() {
//                        ToastUtil.showToast("fdsfsafdadf");
//                    }
//                });
//                outDialog.show();
                Intent i =new Intent(PersonEditActivity.this,HomeActivity.class);
                startActivity(i);
            }
        });
        login_tv_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PersonEditActivity.this, SettingActivity.class);
                startActivity(i);
            }
        });
    }
}