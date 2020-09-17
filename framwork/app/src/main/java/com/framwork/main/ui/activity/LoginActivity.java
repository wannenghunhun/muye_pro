package com.framwork.main.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import com.framwork.common.ui.activity.BaseFragmentActivity;
import com.framwork.common.utils.LogUtil;
import com.framwork.common.utils.SPManager;
import com.framwork.common.utils.ToastUtil;
import com.framwork.common.widget.ClearAbleEditText;
import com.framwork.main.GlobalConstants;
import com.framwork.main.R;
import com.framwork.main.bean.LoginBean;
import com.framwork.main.http.ResultBean;
import com.framwork.main.ui.contract.LoginContract;
import com.framwork.main.ui.presenter.LoginPresenter;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginActivity extends BaseFragmentActivity<LoginContract.Presenter> implements LoginContract.View {
    private TextView login_tv_login, login_tv_setting;
    private ClearAbleEditText loginEtAccount;
    private ClearAbleEditText loginEtPassword;
    private String account, password;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads().detectDiskWrites().detectNetwork()
                .penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
                .penaltyLog().penaltyDeath().build());
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
        login_tv_login = findViewById(R.id.login_tv_login);
        login_tv_setting = findViewById(R.id.login_tv_setting);
        loginEtAccount = findViewById(R.id.login_et_account);
        loginEtPassword = findViewById(R.id.login_et_password);
        setEdit();
        setOnclick();
    }
    
    @Override
    protected void initData(@NonNull Bundle bundle) {
    }
    
    private void setEdit() {
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                account = loginEtAccount.getText().toString();
                password = loginEtPassword.getText().toString();
            }
            
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                account = loginEtAccount.getText().toString();
                password = loginEtPassword.getText().toString();
            }
            
            @Override
            public void afterTextChanged(Editable s) {
            
            }
        };
        loginEtAccount.addTextChangedListener(textWatcher);
        loginEtPassword.addTextChangedListener(textWatcher);
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
                //                Intent i =new Intent(LoginActivity.this,HomeActivity.class);
                //                startActivity(i);
                //                LogUtil.e("appid=" + SPManager.getManager("param").getString("APP_ID"), "");
                //                if(TextUtils.isEmpty(account)) {
                //                    ToastUtil.showToast("账号不能为空");
                //                }
                //                else if(TextUtils.isEmpty(password)) {
                //                    ToastUtil.showToast("密码不能为空");
                //                }
                //                else {
                                    presenter.goLogin("admin", "123456");
                //                }
            }
        });
        login_tv_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, SettingActivity.class);
                startActivity(i);
            }
        });
    }
    
    @Override
    public void logSuccess( LoginBean loginBean) {
        ToastUtil.showToast("登录成功！");
        SPManager.getDefaultManager().commitString("token", loginBean.token);
        Intent i = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(i);
    }
    
}
