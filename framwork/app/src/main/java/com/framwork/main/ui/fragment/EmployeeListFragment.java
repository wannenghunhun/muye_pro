package com.framwork.main.ui.fragment;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.framwork.common.ui.fragment.BaseRefreshFragment;
import com.framwork.common.utils.ToastUtil;
import com.framwork.main.R;
import com.framwork.main.bean.EmployeesBean;
import com.framwork.main.ui.adapter.EmployeesAdapter;
import com.framwork.main.ui.contract.PersonListContract;
import com.framwork.main.ui.presenter.PersonListPresenter;

public class EmployeeListFragment extends BaseRefreshFragment<PersonListContract.Presenter> implements PersonListContract.View {
    private RecyclerView employee_list;
    private EmployeesAdapter mAdapter;
    
    private int page = 1, pageSize = 10, total;
    
    @Override
    protected void initView(View view) {
        setEnableRefresh(true);
        setEnableLoadMore(true);
        employee_list = findViewById(R.id.employee_list);
        employee_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new EmployeesAdapter(getActivity());
        employee_list.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        employee_list.setAdapter(mAdapter);
    }
    
    @Override
    protected int layoutContentId() {
        return R.layout.fragment_employee_list;
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
        presenter.getEmployeesInfo("", 0, "8", 1, 1, pageSize, true);
    }
    
    @Override
    protected void loadAgain() {
    
    }
    
    @Override
    protected void pullDownRefresh() {
        super.pullDownRefresh();
        showLoading();
        page = 1;
        presenter.getEmployeesInfo("", 0, "8", 1, 1, pageSize, true);
    }
    
    @Override
    protected void pullUpLoadMore() {
        super.pullUpLoadMore();
        showLoading();
        int t = mAdapter.getItemCount();
        if(t < total) {
            page = page + 1;
            presenter.getEmployeesInfo("", 0, "8", 1, 1, pageSize, false);
        }
        else {
            dissLoading();
            loadComplete();
            ToastUtil.showToast("没有更多数据了");
        }
    }
    
    @Override
    public void setViews(EmployeesBean employeesBean, boolean isRefresh) {
        total = employeesBean.total;
        if(isRefresh) {
            if(employeesBean.employees.size() < 1) {
                showEmpty("暂时没有数据", "点击屏幕刷新");
            }
            else {
                mAdapter.setData(employeesBean.employees);
                loadComplete();
            }
        }
        else {
            if(employeesBean.employees.size() < 1) {
                dissLoading();
                loadComplete();
                ToastUtil.showToast("没有更多数据了");
            }
            else {
                mAdapter.addItems2Last(employeesBean.employees);
            }
            loadComplete();
        }
        
    }
}
