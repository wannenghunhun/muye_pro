package com.framwork.main.ui.activity;

import android.content.Context;
import android.content.Intent;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.framwork.common.ui.activity.BaseFragmentActivity;
import com.framwork.common.utils.ToastUtil;
import com.framwork.common.widget.ClearAbleEditText;
import com.framwork.main.EditEvent;
import com.framwork.main.R;
import com.framwork.main.bean.BaseBean;
import com.framwork.main.bean.ConditionsBean;
import com.framwork.main.bean.EmployeeBean;
import com.framwork.main.bean.UserInfoBean;
import com.framwork.main.ui.contract.PersonEditContract;
import com.framwork.main.ui.presenter.PersonEditPresenter;
import com.framwork.main.util.ImageUtil;
import com.framwork.main.util.LoginUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

public class PersonEditActivity extends BaseFragmentActivity<PersonEditContract.Presenter> implements PersonEditContract.View {
    private RelativeLayout mPersonEditLayoutTitle;
    private ImageView mPersonEditImgBack;
    private TextView mPersonEditTvTitle;
    private TextView mPersonEditTvLogout;
    private RelativeLayout mPersonEditLayoutPro;
    private TextView mPersonEditTvProChose;
    private TextView mPersonEditTvProName;
    private LinearLayout mPersonEditLayoutProTitle;
    private LinearLayout mPersonEditLayoutVerify;
    private ImageView mPersonEditImgFace;
    private ImageView mPersonEditImgId;
    private LinearLayout mPersonEditLayoutRight;
    private LinearLayout mPersonEditLayoutWrong;
    private ClearAbleEditText mPersonEditEtName;
    private ClearAbleEditText mPersonEditEtId;
    private RelativeLayout mPersonEditLayoutSex;
    private TextView mPersonEditTvSex;
    private ClearAbleEditText mPersonEditEtMinzu;
    private LinearLayout mPersonEditBirthday;
    private TextView mPersonEditTvBirthday;
    private ClearAbleEditText mPersonEditEtAddress;
    private ClearAbleEditText mPersonEditEtPhone;
    private RelativeLayout mPersonEditLayoutZhengzhi;
    private TextView mPersonEditTvZhengzhi;
    private RelativeLayout mPersonEditLayoutJiguan;
    private TextView mPersonEditTvJiguan;
    private RelativeLayout mPersonEditLayoutWenhua;
    private TextView mPersonEditTvWenhua;
    private RelativeLayout mPersonEditLayoutLeader;
    private TextView mPersonEditTvLeader;
    private RelativeLayout mPersonEditLayoutPeixun;
    private TextView mPersonEditTvPeixun;
    private RelativeLayout mPersonEditLayoutChizheng;
    private TextView mPersonEditTvChizheng;
    private ClearAbleEditText mPersonEditEtZhengshu;
    private ClearAbleEditText mPersonEditEtHetong;
    private RelativeLayout mPersonEditLayoutUnit;
    private TextView mPersonEditTvUnit;
    private RelativeLayout mPersonEditLayoutRole;
    private TextView mPersonEditTvRole;
    private RelativeLayout mPersonEditLayoutWorktype;
    private RelativeLayout mPersonEditLayoutTeam;
    private TextView mPersonEditTvWorktype;
    private TextView mPersonEditTvFanchang;
    private TextView mPersonEditTvAddinfo;
    private TextView mPersonEditTvTeam;
    
    private String projectId, id, name = "", id_number = "", minzu = "", address = "", phone = "", zhengshu_number, hetong_number;
    
    private PopupWindow popupWindow_sex, popupWindow_zhengzhi, popupWindow_jiguan, popupWindow_wenhua, popupWindow_leader, popupWindow_peixun,
            popupWindow_chizheng, popupWindow_unit, popupWindow_team, popupWindow_role, popupWindow_worktype, popupWindow_pro;
    private View popView_sex, popView_zhengzhi, popView_jiguan, popView_wenhua, popView_leader, popView_peixun,
            popView_chizheng, popView_unit, popView_team, popView_role, popView_pro, popView_worktype;
    private int index_sex, index_zhengzhi, index_jiguan, index_wenhua, index_leader, index_peixun,
            index_chizheng, index_unit, index_team, index_role, index_worktype, index_pro;
    
    private ConditionsBean conditionsBean;
    private UserInfoBean userInfo;
    private EmployeeBean employeeBean;
    private ArrayList<String> zhengzhiList = new ArrayList<>();
    private ArrayList<String> jiguanList = new ArrayList<>();
    private ArrayList<String> wenhuaList = new ArrayList<>();
    private ArrayList<String> unitList = new ArrayList<>();
    private ArrayList<String> teamList = new ArrayList<>();
    private ArrayList<String> roleList = new ArrayList<>();
    private ArrayList<String> workTypeList = new ArrayList<>();
    private ArrayList<String> pro_names = new ArrayList<>();
    
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
        return R.layout.activity_person_edit;
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
        presenter.getConditionsInfo(projectId);
        presenter.getUserInfo();
    }
    
    @Override
    protected void initView() {
        mPersonEditLayoutTitle = findViewById(R.id.person_edit_layout_title);
        mPersonEditImgBack = findViewById(R.id.person_edit_img_back);
        mPersonEditTvTitle = findViewById(R.id.person_edit_tv_title);
        mPersonEditTvLogout = findViewById(R.id.person_edit_tv_logout);
        mPersonEditLayoutPro = findViewById(R.id.person_edit_layout_pro);
        mPersonEditTvProChose = findViewById(R.id.person_edit_tv_pro_chose);
        mPersonEditTvProName = findViewById(R.id.person_edit_tv_pro_name);
        mPersonEditLayoutProTitle = findViewById(R.id.person_edit_layout_pro_title);
        mPersonEditLayoutVerify = findViewById(R.id.person_edit_layout_verify);
        mPersonEditImgFace = findViewById(R.id.person_edit_img_face);
        mPersonEditImgId = findViewById(R.id.person_edit_img_id);
        mPersonEditLayoutRight = findViewById(R.id.person_edit_layout_right);
        mPersonEditLayoutWrong = findViewById(R.id.person_edit_layout_wrong);
        mPersonEditEtName = findViewById(R.id.person_edit_et_name);
        mPersonEditEtId = findViewById(R.id.person_edit_et_id);
        mPersonEditLayoutSex = findViewById(R.id.person_edit_layout_sex);
        mPersonEditTvSex = findViewById(R.id.person_edit_tv_sex);
        mPersonEditEtMinzu = findViewById(R.id.person_edit_et_minzu);
        mPersonEditBirthday = findViewById(R.id.person_edit_birthday);
        mPersonEditTvBirthday = findViewById(R.id.person_edit_tv_birthday);
        mPersonEditEtAddress = findViewById(R.id.person_edit_et_address);
        mPersonEditEtPhone = findViewById(R.id.person_edit_et_phone);
        mPersonEditLayoutZhengzhi = findViewById(R.id.person_edit_layout_zhengzhi);
        mPersonEditTvZhengzhi = findViewById(R.id.person_edit_tv_zhengzhi);
        mPersonEditLayoutJiguan = findViewById(R.id.person_edit_layout_jiguan);
        mPersonEditTvJiguan = findViewById(R.id.person_edit_tv_jiguan);
        mPersonEditLayoutWenhua = findViewById(R.id.person_edit_layout_wenhua);
        mPersonEditTvWenhua = findViewById(R.id.person_edit_tv_wenhua);
        mPersonEditLayoutLeader = findViewById(R.id.person_edit_layout_leader);
        mPersonEditTvLeader = findViewById(R.id.person_edit_tv_leader);
        mPersonEditLayoutPeixun = findViewById(R.id.person_edit_layout_peixun);
        mPersonEditTvPeixun = findViewById(R.id.person_edit_tv_peixun);
        mPersonEditLayoutChizheng = findViewById(R.id.person_edit_layout_chizheng);
        mPersonEditTvChizheng = findViewById(R.id.person_edit_tv_chizheng);
        mPersonEditEtZhengshu = findViewById(R.id.person_edit_et_zhengshu);
        mPersonEditEtHetong = findViewById(R.id.person_edit_et_hetong);
        mPersonEditLayoutUnit = findViewById(R.id.person_edit_layout_unit);
        mPersonEditTvUnit = findViewById(R.id.person_edit_tv_unit);
        mPersonEditLayoutTeam = findViewById(R.id.person_edit_layout_team);
        mPersonEditLayoutRole = findViewById(R.id.person_edit_layout_role);
        mPersonEditTvRole = findViewById(R.id.person_edit_tv_role);
        mPersonEditLayoutWorktype = findViewById(R.id.person_edit_layout_worktype);
        mPersonEditTvWorktype = findViewById(R.id.person_edit_tv_worktype);
        mPersonEditTvFanchang = findViewById(R.id.person_edit_tv_fanchang);
        mPersonEditTvAddinfo = findViewById(R.id.person_edit_tv_addinfo);
        mPersonEditTvTeam = findViewById(R.id.person_edit_tv_team);
        popView_sex = View.inflate(this, R.layout.view_popupwindow, null);
        popupWindow_sex = new PopupWindow(popView_sex);
        popView_zhengzhi = View.inflate(this, R.layout.view_popupwindow, null);
        popupWindow_zhengzhi = new PopupWindow(popView_zhengzhi);
        popView_jiguan = View.inflate(this, R.layout.view_popupwindow, null);
        popupWindow_jiguan = new PopupWindow(popView_jiguan);
        popView_wenhua = View.inflate(this, R.layout.view_popupwindow, null);
        popupWindow_wenhua = new PopupWindow(popView_wenhua);
        popView_leader = View.inflate(this, R.layout.view_popupwindow, null);
        popupWindow_leader = new PopupWindow(popView_leader);
        popView_peixun = View.inflate(this, R.layout.view_popupwindow, null);
        popupWindow_peixun = new PopupWindow(popView_peixun);
        popView_chizheng = View.inflate(this, R.layout.view_popupwindow, null);
        popupWindow_chizheng = new PopupWindow(popView_chizheng);
        popView_unit = View.inflate(this, R.layout.view_popupwindow, null);
        popupWindow_unit = new PopupWindow(popView_unit);
        popView_team = View.inflate(this, R.layout.view_popupwindow, null);
        popupWindow_team = new PopupWindow(popView_team);
        popView_role = View.inflate(this, R.layout.view_popupwindow, null);
        popupWindow_role = new PopupWindow(popView_role);
        popView_worktype = View.inflate(this, R.layout.view_popupwindow, null);
        popupWindow_worktype = new PopupWindow(popView_worktype);
        popView_pro = View.inflate(this, R.layout.view_popupwindow, null);
        popupWindow_pro = new PopupWindow(popView_pro);
        
        setOnclick();
        setEdit();
    }
    
    @Override
    protected void initData(@NonNull Bundle bundle) {
        projectId = bundle.getString("projectId");
        id = bundle.getString("id");
    }
    
    private void hideSoftKeyBoard() {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(this.getWindow().getDecorView().getWindowToken(), 0);
    }
    
    private void setOnclick() {
        mPersonEditTvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginUtil.clearUserData();
                Intent i = new Intent(PersonEditActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
        mPersonEditImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mPersonEditLayoutSex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popShow(popupWindow_sex, popView_sex);
            }
        });
        mPersonEditLayoutLeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popShow(popupWindow_leader, popView_leader);
            }
        });
        mPersonEditLayoutPeixun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popShow(popupWindow_peixun, popView_peixun);
            }
        });
        mPersonEditLayoutChizheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popShow(popupWindow_chizheng, popView_chizheng);
            }
        });
        
        mPersonEditLayoutZhengzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popShow(popupWindow_zhengzhi, popView_zhengzhi);
            }
        });
        mPersonEditLayoutJiguan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popShow(popupWindow_jiguan, popView_jiguan);
            }
        });
        mPersonEditLayoutWenhua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popShow(popupWindow_wenhua, popView_wenhua);
            }
        });
        mPersonEditLayoutUnit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popShow(popupWindow_unit, popView_unit);
            }
        });
        mPersonEditLayoutTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popShow(popupWindow_team, popView_team);
            }
        });
        mPersonEditLayoutRole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popShow(popupWindow_role, popView_role);
            }
        });
        mPersonEditLayoutWorktype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popShow(popupWindow_worktype, popView_worktype);
            }
        });
        mPersonEditTvProChose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popShow(popupWindow_pro, popView_pro);
            }
        });
        mPersonEditTvAddinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.editEmployeesInfo(id, employeeBean.employee.idPhotoPath, employeeBean.employee.photoPath, "老七",
                        employeeBean.employee.idCard, employeeBean.employee.sex, employeeBean.employee.birthday, employeeBean.employee.nation,
                        employeeBean.employee.address, employeeBean.employee.tel, employeeBean.employee.politicalStatus, employeeBean.employee.nativePlace,
                        employeeBean.employee.education, employeeBean.employee.ifForeman, employeeBean.employee.ifTrain, employeeBean.employee.ifCertificate,
                        projectId, employeeBean.employee.unitId, employeeBean.employee.teamId, employeeBean.employee.roleType, employeeBean.employee.workType,
                        employeeBean.employee.age, employeeBean.employee.certificateSn, employeeBean.employee.contractSn
                );
            }
        });
        mPersonEditTvFanchang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.employeeBack(id);
            }
        });
    }
    
    private void setEdit() {
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                name = mPersonEditEtName.getText().toString();
                id_number = mPersonEditEtId.getText().toString();
                minzu = mPersonEditEtMinzu.getText().toString();
                address = mPersonEditEtAddress.getText().toString();
                phone = mPersonEditEtPhone.getText().toString();
                zhengshu_number = mPersonEditEtZhengshu.getText().toString();
                hetong_number = mPersonEditEtHetong.getText().toString();
            }
            
            @Override
            public void afterTextChanged(Editable s) {
            }
        };
        mPersonEditEtName.addTextChangedListener(textWatcher);
        mPersonEditEtId.addTextChangedListener(textWatcher);
        mPersonEditEtMinzu.addTextChangedListener(textWatcher);
        mPersonEditEtAddress.addTextChangedListener(textWatcher);
        mPersonEditEtPhone.addTextChangedListener(textWatcher);
        mPersonEditEtZhengshu.addTextChangedListener(textWatcher);
        mPersonEditEtHetong.addTextChangedListener(textWatcher);
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
        popupwindow_list.setAdapter(new ArrayAdapter<String>(PersonEditActivity.this, R.layout.item_popupwindow, R.id.item_tv, dataList));
        popupwindow_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                //关闭popupWindow
                popupWindow.dismiss();
                textView.setText(dataList.get(arg2));
                switch(key) {
                    case 0:
                        index_sex = arg2;
                        break;
                    case 1:
                        index_zhengzhi = arg2;
                        break;
                    case 2:
                        index_jiguan = arg2;
                        break;
                    case 3:
                        index_wenhua = arg2;
                        break;
                    case 4:
                        index_leader = arg2;
                        break;
                    case 5:
                        index_peixun = arg2;
                        break;
                    case 6:
                        index_chizheng = arg2;
                        break;
                    case 7:
                        index_unit = arg2;
                        break;
                    case 8:
                        index_team = arg2;
                        break;
                    case 9:
                        index_role = arg2;
                        ArrayList<String> workTypeList = new ArrayList<>();
                        for(int x = 0; x < conditionsBean.types.get(index_role).value.size(); x++) {
                            workTypeList.add(conditionsBean.types.get(index_role).value.get(x).name);
                        }
                        setPopupWindow("工种", mPersonEditTvWorktype, workTypeList, popupWindow_worktype, popView_worktype, 10);
                        mPersonEditTvWorktype.setText(workTypeList.get(0));
                        break;
                    case 10:
                        index_worktype = arg2;
                        break;
                    case 11:
                        index_pro = arg2;
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
    
    @Override
    public void setConditionsInfo(ConditionsBean conditionsBean) {
        this.conditionsBean = conditionsBean;
        ArrayList<String> sexList = new ArrayList<>();
        sexList.add("男");
        sexList.add("女");
        ArrayList<String> booleanList = new ArrayList<>();
        booleanList.add("是");
        booleanList.add("否");
        zhengzhiList = new ArrayList<>();
        for(int i = 0; i < conditionsBean.politicalStatus.size(); i++) {
            zhengzhiList.add(conditionsBean.politicalStatus.get(i).value);
        }
        jiguanList = new ArrayList<>();
        for(int i = 0; i < conditionsBean.provinces.size(); i++) {
            jiguanList.add(conditionsBean.provinces.get(i).value);
        }
        wenhuaList = new ArrayList<>();
        for(int i = 0; i < conditionsBean.education.size(); i++) {
            wenhuaList.add(conditionsBean.education.get(i).value);
        }
        unitList = new ArrayList<>();
        for(int i = 0; i < conditionsBean.units.size(); i++) {
            unitList.add(conditionsBean.units.get(i).name);
        }
        teamList = new ArrayList<>();
        for(int i = 0; i < conditionsBean.teams.size(); i++) {
            teamList.add(conditionsBean.teams.get(i).name);
        }
        roleList = new ArrayList<>();
        for(int i = 0; i < conditionsBean.roles.size(); i++) {
            roleList.add(conditionsBean.roles.get(i).name);
        }
        workTypeList = new ArrayList<>();
        for(int x = 0; x < conditionsBean.types.get(0).value.size(); x++) {
            workTypeList.add(conditionsBean.types.get(0).value.get(x).name);
        }
        setPopupWindow("性别", mPersonEditTvSex, sexList, popupWindow_sex, popView_sex, 0);
        setPopupWindow("政治面貌", mPersonEditTvZhengzhi, zhengzhiList, popupWindow_zhengzhi, popView_zhengzhi, 1);
        setPopupWindow("籍贯", mPersonEditTvJiguan, jiguanList, popupWindow_jiguan, popView_jiguan, 2);
        setPopupWindow("文化程度", mPersonEditTvWenhua, wenhuaList, popupWindow_wenhua, popView_wenhua, 3);
        setPopupWindow("班组长", mPersonEditTvLeader, booleanList, popupWindow_leader, popView_leader, 4);
        setPopupWindow("参加培训", mPersonEditTvPeixun, booleanList, popupWindow_peixun, popView_peixun, 5);
        setPopupWindow("持证上岗", mPersonEditTvChizheng, booleanList, popupWindow_chizheng, popView_chizheng, 6);
        setPopupWindow("所属单位", mPersonEditTvUnit, unitList, popupWindow_unit, popView_unit, 7);
        setPopupWindow("所属班组", mPersonEditTvTeam, teamList, popupWindow_team, popView_team, 8);
        setPopupWindow("角色", mPersonEditTvRole, roleList, popupWindow_role, popView_role, 9);
        setPopupWindow("工种", mPersonEditTvWorktype, workTypeList, popupWindow_worktype, popView_worktype, 10);
        presenter.getEmployeeInfo(id);
    }
    
    @Override
    public void setProjectInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
        pro_names = new ArrayList<>();
        for(int i = 0; i < userInfo.projects.size(); i++) {
            pro_names.add(userInfo.projects.get(i).name);
        }
        setPopupWindow("选择项目", mPersonEditTvProName, pro_names, popupWindow_pro, popView_pro, 11);
        mPersonEditTvProName.setText(pro_names.get(0));
    }
    
    @Override
    public void setEmployeeInfo(EmployeeBean employeeBean) {
        this.employeeBean = employeeBean;
        Glide.with(PersonEditActivity.this).load(employeeBean.employee.photoPath).apply(ImageUtil.getRequestOptions()).into(mPersonEditImgFace);
        Glide.with(PersonEditActivity.this).load(employeeBean.employee.idPhotoPath).apply(ImageUtil.getRequestOptions()).into(mPersonEditImgId);
        mPersonEditEtName.setText(employeeBean.employee.name);
        mPersonEditEtId.setText(employeeBean.employee.idCard);
        mPersonEditTvSex.setText(employeeBean.employee.sex);
        mPersonEditEtMinzu.setText(employeeBean.employee.nation);
        mPersonEditTvBirthday.setText(employeeBean.employee.birthday);
        mPersonEditEtAddress.setText(employeeBean.employee.address);
        mPersonEditEtPhone.setText(employeeBean.employee.tel);
        mPersonEditTvZhengzhi.setText(employeeBean.employee.politicalStatus);
        mPersonEditTvJiguan.setText(employeeBean.employee.nativePlace);
        mPersonEditTvWenhua.setText(employeeBean.employee.education);
        mPersonEditTvLeader.setText(employeeBean.employee.ifForeman);
        mPersonEditTvPeixun.setText(employeeBean.employee.ifTrain);
        mPersonEditTvChizheng.setText(employeeBean.employee.ifCertificate);
        mPersonEditEtHetong.setText(employeeBean.employee.contractSn);
        mPersonEditEtZhengshu.setText(employeeBean.employee.certificateSn);
        for(int i = 0; i < conditionsBean.units.size(); i++) {
            if(employeeBean.employee.unitId.equals(conditionsBean.units.get(i).id)) {
                mPersonEditTvUnit.setText(conditionsBean.units.get(i).name);
            }
            
        }
        for(int i = 0; i < conditionsBean.teams.size(); i++) {
            if(employeeBean.employee.teamId.equals(conditionsBean.teams.get(i).id)) {
                mPersonEditTvTeam.setText(conditionsBean.teams.get(i).name);
            }
            
        }
        for(int i = 0; i < conditionsBean.roles.size(); i++) {
            if(employeeBean.employee.roleType.equals(conditionsBean.roles.get(i).id)) {
                mPersonEditTvRole.setText(conditionsBean.roles.get(i).name);
            }
            
        }
        for(int i = 0; i < conditionsBean.types.size(); i++) {
            for(int x = 0; x < conditionsBean.types.get(i).value.size(); x++) {
                if(employeeBean.employee.workType.equals(conditionsBean.types.get(i).value.get(x).id)) {
                    mPersonEditTvWorktype.setText(conditionsBean.types.get(i).value.get(x).name);
                }
            }
            
        }
        //        if(employeeBean.employee.returnStatus) {
        //            mPersonEditTvFanchang.setVisibility(View.VISIBLE);
        //        }
        //        else {
        //            mPersonEditTvFanchang.setVisibility(View.GONE);
        //        }
    }
    
    @Override
    public void editResult(String s) {
        ToastUtil.showToast(s);
        finish();
        EventBus.getDefault().post(new EditEvent());
    }
    
}
