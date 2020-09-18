package com.framwork.main.ui.presenter;

import com.framwork.common.utils.ToastUtil;
import com.framwork.main.GlobalConstants;
import com.framwork.main.bean.EmployeesBean;
import com.framwork.main.bean.ProjectInfoBean;
import com.framwork.main.bean.UserInfoBean;
import com.framwork.main.http.GsonHttpCallback;
import com.framwork.main.http.RestClient;
import com.framwork.main.http.ResultBean;
import com.framwork.main.ui.contract.HomeContract;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Request;

/**
 */
public class HomePresenter extends HomeContract.Presenter {
    public HomePresenter(HomeContract.View view) {
        super(view);
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
                    getView().setUserInfo(t.data);
                }
            }
            
            @Override
            protected void onNetFail(ResultBean<UserInfoBean> t) {
                ToastUtil.showNetError();
            }
        });
    }
    
    @Override
    public void getProjectInfo(String id) {
        Map<String, String> params = new HashMap<>();
        RestClient.postWithParam(GlobalConstants.InterfaceNameConstants.PROJECT_INFO, params, id, new GsonHttpCallback<ProjectInfoBean>() {
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
            protected void error(ResultBean<ProjectInfoBean> t) {
                ToastUtil.showToast(t.msg);
            }
            
            @Override
            protected void response(ResultBean<ProjectInfoBean> t) {
                if(isViewAttached()) {
                    getView().setProjectInfo(t.data);
                }
            }
            
            @Override
            protected void onNetFail(ResultBean<ProjectInfoBean> t) {
                ToastUtil.showNetError();
            }
        });
    }
    
    @Override
    public void getEmployeesInfo(String key, int keyType, String projectId, int status, int pageNum, int pageSize) {
        //关键字类型 0-名称 1-单位 2-班组
        //订单状态：0-离岗 1-在岗 默认在岗
        JSONObject params = new JSONObject();
        try {
            params.put("key", key);
            params.put("keyType", keyType);
            params.put("pageNum", pageNum);
            params.put("pageSize", pageSize);
            params.put("projectId", projectId);
            params.put("status", status);
        } catch(JSONException e) {
            e.printStackTrace();
        }
        
        RestClient.postWithTag(GlobalConstants.InterfaceNameConstants.PROJECT_EMPLOYEES, params, new GsonHttpCallback<EmployeesBean>() {
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
            protected void error(ResultBean<EmployeesBean> t) {
                ToastUtil.showToast(t.msg);
            }
            
            @Override
            protected void response(ResultBean<EmployeesBean> t) {
                if(isViewAttached()) {
                    //                    getView().setProjectInfo(t.data);
                }
            }
            
            @Override
            protected void onNetFail(ResultBean<EmployeesBean> t) {
                ToastUtil.showNetError();
            }
        });
    }
}
