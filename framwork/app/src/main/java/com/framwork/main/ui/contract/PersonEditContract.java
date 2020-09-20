package com.framwork.main.ui.contract;


import com.framwork.common.presenter.BasePresenter;
import com.framwork.common.ui.view.IBaseView;
import com.framwork.main.bean.ConditionsBean;
import com.framwork.main.bean.EmployeeBean;
import com.framwork.main.bean.UserInfoBean;

public interface PersonEditContract {
    
    interface View extends IBaseView {
        void setConditionsInfo(ConditionsBean projectInfo);
        
        void setProjectInfo(UserInfoBean userInfo);
        
        void setEmployeeInfo(EmployeeBean employeeBean);
    }
    
    abstract class Presenter extends BasePresenter<View> {
        public Presenter(View view) {
            super(view);
        }
        
        public abstract void getUserInfo();
        
        public abstract void getConditionsInfo(String projectId);
        
        public abstract void getEmployeeInfo(String id);
    }
}
