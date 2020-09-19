package com.framwork.main.ui.presenter;

import com.framwork.common.utils.ToastUtil;
import com.framwork.main.GlobalConstants;
import com.framwork.main.bean.ConditionsBean;
import com.framwork.main.bean.EmployeeBean;
import com.framwork.main.bean.UserInfoBean;
import com.framwork.main.http.GsonHttpCallback;
import com.framwork.main.http.RestClient;
import com.framwork.main.http.ResultBean;
import com.framwork.main.ui.contract.PersonEditContract;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Request;

/**
 */
public class PersonEditPresenter extends PersonEditContract.Presenter {
    public PersonEditPresenter(PersonEditContract.View view) {
        super(view);
    }
    
    @Override
    public void getConditionsInfo(String projectId) {
        Map<String, String> params = new HashMap<>();
        RestClient.postWithParam(GlobalConstants.InterfaceNameConstants.PROJECT, params,
                projectId + GlobalConstants.InterfaceNameConstants.CONDITIONS, new GsonHttpCallback<ConditionsBean>() {
                    @Override
                    public void onBefore(Request request) {
                        if(isViewAttached()) {
                            getView().showLoading();
                        }
                    }
                    
                    @Override
                    public void onAfter() {
                        if(isViewAttached()) {
                            getView().dissLoading();
                        }
                    }
                    
                    @Override
                    protected void error(ResultBean<ConditionsBean> t) {
                        ToastUtil.showToast(t.msg);
                    }
                    
                    @Override
                    protected void response(ResultBean<ConditionsBean> t) {
                        if(isViewAttached()) {
                            getView().setConditionsInfo(t.data);
                        }
                    }
                    
                    @Override
                    protected void onNetFail(ResultBean<ConditionsBean> t) {
                        ToastUtil.showNetError();
                    }
                });
    }
    
    @Override
    public void getUserInfo() {
        Map<String, String> params = new HashMap<>();
        RestClient.postWithForm(GlobalConstants.InterfaceNameConstants.USER_INFO, params, new GsonHttpCallback<UserInfoBean>() {
            @Override
            public void onBefore(Request request) {
                if(isViewAttached()) {
                    getView().showLoading();
                }
            }
            
            @Override
            public void onAfter() {
                if(isViewAttached()) {
                    getView().dissLoading();
                }
            }
            
            @Override
            protected void error(ResultBean<UserInfoBean> t) {
                ToastUtil.showToast(t.msg);
            }
            
            @Override
            protected void response(ResultBean<UserInfoBean> t) {
                if(isViewAttached()) {
                    getView().setProjectInfo(t.data);
                }
            }
            
            @Override
            protected void onNetFail(ResultBean<UserInfoBean> t) {
                ToastUtil.showNetError();
            }
        });
    }
    
    @Override
    public void getEmployeeInfo(String id) {
        Map<String, String> params = new HashMap<>();
        RestClient.getWithParam(GlobalConstants.InterfaceNameConstants.PROJECT, params,
                GlobalConstants.InterfaceNameConstants.EMPLOYEE + id, new GsonHttpCallback<EmployeeBean>() {
                    @Override
                    public void onBefore(Request request) {
                        if(isViewAttached()) {
                            getView().showLoading();
                        }
                    }
                    
                    @Override
                    public void onAfter() {
                        if(isViewAttached()) {
                            getView().dissLoading();
                        }
                    }
                    
                    @Override
                    protected void error(ResultBean<EmployeeBean> t) {
                        ToastUtil.showToast(t.msg);
                    }
                    
                    @Override
                    protected void response(ResultBean<EmployeeBean> t) {
                        if(isViewAttached()) {
                            getView().setEmployeeInfo(t.data);
                        }
                    }
                    
                    @Override
                    protected void onNetFail(ResultBean<EmployeeBean> t) {
                        ToastUtil.showNetError();
                    }
                });
    }
}
