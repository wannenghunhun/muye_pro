package com.framwork.main.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.framwork.common.ui.activity.BaseFragmentActivity;
import com.framwork.common.widget.ClearAbleEditText;
import com.framwork.main.R;
import com.framwork.main.bean.ProjectInfoBean;
import com.framwork.main.bean.UserInfoBean;
import com.framwork.main.ui.contract.HomeContract;
import com.framwork.main.ui.fragment.EmployeeListFragment;
import com.framwork.main.ui.presenter.HomePresenter;
import com.framwork.main.util.LoginUtil;

import java.util.ArrayList;

public class HomeActivity extends BaseFragmentActivity<HomeContract.Presenter> implements HomeContract.View {
    private LinearLayout home_layout_person_add;
    private PopupWindow popupWindow_pro, popupWindow_type, popupWindow_state;
    private View popView_pro, popView_type, popView_state;
    private TextView mHomeTvName;
    private TextView mHomeTvDate;
    private TextView mHomeTvWeek;
    private TextView mHomeTvLogout;
    private TextView mHomeTvProChose;
    private TextView mHomeTvProName;
    private TextView mHomeTvUnit;
    private LinearLayout mHomeLayoutAddress;
    private TextView mHomeTvAddress;
    private TextView mHomeTvManager;
    private TextView mHomeTvPhone;
    private TextView mHomeTvArea;
    private TextView mHomeTvProState;
    private LinearLayout mHomeLayoutType;
    private TextView mHomeTvType;
    private RelativeLayout mHomeLayoutKey;
    private LinearLayout mHomeLayoutState;
    private TextView mHomeTvState;
    private TextView mHomeTvSearch;
    private ClearAbleEditText mHomeEtSearch;
    private int index_pro = 0;
    private int index_type = 0;
    private int index_state = 1;
    private int total = 0;
    private int pageSize = 20;
    private String searchKey = "", type, state;
    private EmployeeListFragment employeeListFragment;
    private UserInfoBean userInfoBean;
    private ProjectInfoBean projectInfo;
    
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
        presenter.getUserInfo();
    }
    
    @Override
    protected void initView() {
        home_layout_person_add = findViewById(R.id.home_layout_person_add);
        mHomeTvName = findViewById(R.id.home_tv_name);
        mHomeTvDate = findViewById(R.id.home_tv_date);
        mHomeTvWeek = findViewById(R.id.home_tv_week);
        mHomeTvLogout = findViewById(R.id.home_tv_logout);
        mHomeTvProChose = findViewById(R.id.home_tv_pro_chose);
        mHomeTvProName = findViewById(R.id.home_tv_pro_name);
        mHomeTvUnit = findViewById(R.id.home_tv_unit);
        mHomeLayoutAddress = findViewById(R.id.home_layout_address);
        mHomeTvAddress = findViewById(R.id.home_tv_address);
        mHomeTvManager = findViewById(R.id.home_tv_manager);
        mHomeTvPhone = findViewById(R.id.home_tv_phone);
        mHomeTvArea = findViewById(R.id.home_tv_area);
        mHomeTvProState = findViewById(R.id.home_tv_pro_state);
        mHomeLayoutType = findViewById(R.id.home_layout_type);
        mHomeTvType = findViewById(R.id.home_tv_type);
        mHomeLayoutKey = findViewById(R.id.home_layout_key);
        mHomeLayoutState = findViewById(R.id.home_layout_state);
        mHomeTvState = findViewById(R.id.home_tv_state);
        mHomeTvSearch = findViewById(R.id.home_tv_search);
        mHomeEtSearch = findViewById(R.id.home_et_search);
        
        popView_pro = View.inflate(this, R.layout.view_popupwindow, null);
        popupWindow_pro = new PopupWindow(popView_pro);
        popView_type = View.inflate(this, R.layout.view_popupwindow, null);
        popupWindow_type = new PopupWindow(popView_type);
        popView_state = View.inflate(this, R.layout.view_popupwindow, null);
        popupWindow_state = new PopupWindow(popView_state);
        setOnClick();
        setView();
        setEdit();
    }
    
    @Override
    protected void initData(@NonNull Bundle bundle) {
    }
    
    private void setFragment(String projectID) {
        employeeListFragment = EmployeeListFragment.newInstance(projectID);
        getSupportFragmentManager().beginTransaction().add(R.id.home_fragment_list, employeeListFragment, "EmployeeList").commit();
        
    }
    
    private void setOnClick() {
        home_layout_person_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, PersonAddActivity.class);
                startActivity(i);
            }
        });
        mHomeTvProChose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popShow(popupWindow_pro, popView_pro);
            }
        });
        mHomeLayoutType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popShow(popupWindow_type, popView_type);
            }
        });
        mHomeLayoutState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popShow(popupWindow_state, popView_state);
            }
        });
        mHomeTvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginUtil.clearUserData();
                Intent i = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
        mHomeTvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关键字类型 0-名称 1-单位 2-班组
                //订单状态：0-离岗 1-在岗 默认在岗
                employeeListFragment.reqData(searchKey, index_type, userInfoBean.projects.get(index_pro).id, index_state);
            }
        });
    }
    
    private void popShow(PopupWindow popupWindow, View view) {
        hideSoftKeyBoard();
        //popupWindow出现屏幕变为半透明
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.5f;
        getWindow().setAttributes(lp);
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }
    
    private void setPopupWindow(String title, TextView textView, ArrayList<String> dataList, PopupWindow popupWindow, View view, int key) {
        TextView popupwindow_tv_title = view.findViewById(R.id.popupwindow_tv_title);
        ListView popupwindow_list = view.findViewById(R.id.popupwindow_list);
        popupwindow_tv_title.setText(title);
        //获取屏幕宽高
        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels * 1 / 2;
        popupWindow.setWidth(width);
        popupWindow.setHeight(height);
        popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
        popupWindow.setFocusable(true);
        //点击外部popueWindow消失
        popupWindow.setOutsideTouchable(true);
        popupwindow_list.setAdapter(new ArrayAdapter<String>(HomeActivity.this, R.layout.item_popupwindow, R.id.item_tv, dataList));
        popupwindow_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                //关闭popupWindow
                popupWindow.dismiss();
                textView.setText(dataList.get(arg2));
                switch(key) {
                    case 0:
                        index_pro = arg2;
                        break;
                    case 1:
                        index_type = arg2;
                        break;
                    case 2:
                        index_state = arg2;
                        break;
                }
            }
        });
        //popupWindow消失屏幕变为不透明
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1.0f;
                getWindow().setAttributes(lp);
            }
        });
    }
    
    private void setView() {
        ArrayList<String> types = new ArrayList<>();
        types.add("姓名");
        types.add("单位");
        types.add("班组");
        setPopupWindow("选择类型", mHomeTvType, types, popupWindow_type, popView_type, 1);
        ArrayList<String> states = new ArrayList<>();
        states.add("离场");
        states.add("在场");
        setPopupWindow("选择状态", mHomeTvState, states, popupWindow_state, popView_state, 2);
    }
    
    private void setEdit() {
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            
            }
            
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchKey = mHomeEtSearch.getText().toString();
            }
            
            @Override
            public void afterTextChanged(Editable s) {
            
            }
        };
        mHomeEtSearch.addTextChangedListener(textWatcher);
    }
    
    private void hideSoftKeyBoard() {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(this.getWindow().getDecorView().getWindowToken(), 0);
    }
    
    @Override
    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfoBean = userInfo;
        mHomeTvName.setText(userInfo.name);
        mHomeTvDate.setText(userInfo.date);
        mHomeTvWeek.setText(userInfo.week);
        mHomeTvProName.setText(userInfo.projects.get(0).name);
        ArrayList<String> pro_names = new ArrayList<>();
        for(int i = 0; i < userInfo.projects.size(); i++) {
            pro_names.add(userInfo.projects.get(i).name);
        }
        setPopupWindow("选择项目", mHomeTvProName, pro_names, popupWindow_pro, popView_pro, 0);
        setFragment(userInfo.projects.get(0).id);
        presenter.getProjectInfo(userInfo.projects.get(0).id);
    }
    
    @Override
    public void setProjectInfo(ProjectInfoBean projectInfo) {
        this.projectInfo = projectInfo;
        mHomeTvUnit.setText(projectInfo.project.generalUnit);
        mHomeTvAddress.setText(projectInfo.project.address);
        mHomeTvManager.setText(projectInfo.project.manager);
        mHomeTvPhone.setText(projectInfo.project.managerTel);
        mHomeTvArea.setText(projectInfo.project.totalArea);
        mHomeTvProState.setText(projectInfo.project.status);
    }
    
}
