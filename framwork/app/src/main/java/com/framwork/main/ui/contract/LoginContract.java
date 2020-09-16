package com.framwork.main.ui.contract;


import com.framwork.common.presenter.BasePresenter;
import com.framwork.common.ui.view.IBaseView;

public interface LoginContract {
    
    interface View extends IBaseView {
    }
    
    abstract class Presenter extends BasePresenter<View> {
        public Presenter(View view) {
            super(view);
        }
    }
}