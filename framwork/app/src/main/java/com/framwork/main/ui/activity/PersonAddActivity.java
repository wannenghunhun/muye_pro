package com.framwork.main.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextUtils;
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

import com.framwork.common.ui.activity.BaseFragmentActivity;
import com.framwork.common.utils.ToastUtil;
import com.framwork.common.widget.ClearAbleEditText;
import com.framwork.main.EditEvent;
import com.framwork.main.R;
import com.framwork.main.bean.ConditionsBean;
import com.framwork.main.bean.EmployeeBean;
import com.framwork.main.bean.UserInfoBean;
import com.framwork.main.ui.contract.PersonAddContract;
import com.framwork.main.ui.presenter.PersonAddPresenter;
import com.framwork.main.util.LoginUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

public class PersonAddActivity extends BaseFragmentActivity<PersonAddContract.Presenter> implements PersonAddContract.View {
    private RelativeLayout mPersonAddLayoutTitle;
    private ImageView mPersonAddImgBack;
    private TextView mPersonAddTvTitle;
    private TextView mPersonAddTvLogout;
    private RelativeLayout mPersonAddLayoutPro;
    private TextView mPersonAddTvProChose;
    private TextView mPersonAddTvProName;
    private LinearLayout mPersonAddLayoutProTitle;
    private LinearLayout mPersonAddLayoutVerify;
    private ImageView mPersonAddImgFace;
    private ImageView mPersonAddImgId;
    private LinearLayout mPersonAddLayoutRight;
    private LinearLayout mPersonAddLayoutWrong;
    private ClearAbleEditText mPersonAddEtName;
    private ClearAbleEditText mPersonAddEtId;
    private RelativeLayout mPersonAddLayoutSex;
    private TextView mPersonAddTvSex;
    private ClearAbleEditText mPersonAddEtMinzu;
    private LinearLayout mPersonAddBirthday;
    private TextView mPersonAddTvBirthday;
    private ClearAbleEditText mPersonAddEtAddress;
    private ClearAbleEditText mPersonAddEtPhone;
    private RelativeLayout mPersonAddLayoutZhengzhi;
    private TextView mPersonAddTvZhengzhi;
    private RelativeLayout mPersonAddLayoutJiguan;
    private TextView mPersonAddTvJiguan;
    private RelativeLayout mPersonAddLayoutWenhua;
    private TextView mPersonAddTvWenhua;
    private RelativeLayout mPersonAddLayoutLeader;
    private TextView mPersonAddTvLeader;
    private RelativeLayout mPersonAddLayoutPeixun;
    private TextView mPersonAddTvPeixun;
    private RelativeLayout mPersonAddLayoutChizheng;
    private TextView mPersonAddTvChizheng;
    private ClearAbleEditText mPersonAddEtZhengshu;
    private ClearAbleEditText mPersonAddEtHetong;
    private RelativeLayout mPersonAddLayoutUnit;
    private TextView mPersonAddTvUnit;
    private RelativeLayout mPersonAddLayoutRole;
    private TextView mPersonAddTvRole;
    private RelativeLayout mPersonAddLayoutWorktype;
    private RelativeLayout mPersonAddLayoutTeam;
    private TextView mPersonAddTvWorktype;
    private TextView mPersonAddTvFanchang;
    private TextView mPersonAddTvAddinfo;
    private TextView mPersonAddTvTeam;
    
    private String projectId, name = "", id_number = "", minzu = "", address = "", phone = "", zhengshu_number, hetong_number;
    
    private PopupWindow popupWindow_sex, popupWindow_zhengzhi, popupWindow_jiguan, popupWindow_wenhua, popupWindow_leader, popupWindow_peixun,
            popupWindow_chizheng, popupWindow_unit, popupWindow_team, popupWindow_role, popupWindow_worktype, popupWindow_pro;
    private View popView_sex, popView_zhengzhi, popView_jiguan, popView_wenhua, popView_leader, popView_peixun,
            popView_chizheng, popView_unit, popView_team, popView_role, popView_pro, popView_worktype;
    private int index_sex, index_zhengzhi, index_jiguan, index_wenhua, index_leader, index_peixun,
            index_chizheng, index_unit, index_team, index_role, index_worktype, index_pro;
    
    private ConditionsBean conditionsBean;
    private UserInfoBean userInfo;
    private ArrayList<String> zhengzhiList = new ArrayList<>();
    private ArrayList<String> jiguanList = new ArrayList<>();
    private ArrayList<String> wenhuaList = new ArrayList<>();
    private ArrayList<String> unitList = new ArrayList<>();
    private ArrayList<String> teamList = new ArrayList<>();
    private ArrayList<String> roleList = new ArrayList<>();
    private ArrayList<String> workTypeList = new ArrayList<>();
    private ArrayList<String> pro_names = new ArrayList<>();
    
    private String person_add_unitId, person_add_teamId, person_add_roleType, person_add_workType;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    
    @Override
    protected PersonAddContract.Presenter createPresenter() {
        return new PersonAddPresenter(this);
    }
    
    @Override
    protected int layoutContentId() {
        return R.layout.activity_person_add;
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
        mPersonAddLayoutTitle = findViewById(R.id.person_add_layout_title);
        mPersonAddImgBack = findViewById(R.id.person_add_img_back);
        mPersonAddTvTitle = findViewById(R.id.person_add_tv_title);
        mPersonAddTvLogout = findViewById(R.id.person_add_tv_logout);
        mPersonAddLayoutPro = findViewById(R.id.person_add_layout_pro);
        mPersonAddTvProChose = findViewById(R.id.person_add_tv_pro_chose);
        mPersonAddTvProName = findViewById(R.id.person_add_tv_pro_name);
        mPersonAddLayoutProTitle = findViewById(R.id.person_add_layout_pro_title);
        mPersonAddLayoutVerify = findViewById(R.id.person_add_layout_verify);
        mPersonAddImgFace = findViewById(R.id.person_add_img_face);
        mPersonAddImgId = findViewById(R.id.person_add_img_id);
        mPersonAddLayoutRight = findViewById(R.id.person_add_layout_right);
        mPersonAddLayoutWrong = findViewById(R.id.person_add_layout_wrong);
        mPersonAddEtName = findViewById(R.id.person_add_et_name);
        mPersonAddEtId = findViewById(R.id.person_add_et_id);
        mPersonAddLayoutSex = findViewById(R.id.person_add_layout_sex);
        mPersonAddTvSex = findViewById(R.id.person_add_tv_sex);
        mPersonAddEtMinzu = findViewById(R.id.person_add_et_minzu);
        mPersonAddBirthday = findViewById(R.id.person_add_birthday);
        mPersonAddTvBirthday = findViewById(R.id.person_add_tv_birthday);
        mPersonAddEtAddress = findViewById(R.id.person_add_et_address);
        mPersonAddEtPhone = findViewById(R.id.person_add_et_phone);
        mPersonAddLayoutZhengzhi = findViewById(R.id.person_add_layout_zhengzhi);
        mPersonAddTvZhengzhi = findViewById(R.id.person_add_tv_zhengzhi);
        mPersonAddLayoutJiguan = findViewById(R.id.person_add_layout_jiguan);
        mPersonAddTvJiguan = findViewById(R.id.person_add_tv_jiguan);
        mPersonAddLayoutWenhua = findViewById(R.id.person_add_layout_wenhua);
        mPersonAddTvWenhua = findViewById(R.id.person_add_tv_wenhua);
        mPersonAddLayoutLeader = findViewById(R.id.person_add_layout_leader);
        mPersonAddTvLeader = findViewById(R.id.person_add_tv_leader);
        mPersonAddLayoutPeixun = findViewById(R.id.person_add_layout_peixun);
        mPersonAddTvPeixun = findViewById(R.id.person_add_tv_peixun);
        mPersonAddLayoutChizheng = findViewById(R.id.person_add_layout_chizheng);
        mPersonAddTvChizheng = findViewById(R.id.person_add_tv_chizheng);
        mPersonAddEtZhengshu = findViewById(R.id.person_add_et_zhengshu);
        mPersonAddEtHetong = findViewById(R.id.person_add_et_hetong);
        mPersonAddLayoutUnit = findViewById(R.id.person_add_layout_unit);
        mPersonAddTvUnit = findViewById(R.id.person_add_tv_unit);
        mPersonAddLayoutTeam = findViewById(R.id.person_add_layout_team);
        mPersonAddLayoutRole = findViewById(R.id.person_add_layout_role);
        mPersonAddTvRole = findViewById(R.id.person_add_tv_role);
        mPersonAddLayoutWorktype = findViewById(R.id.person_add_layout_worktype);
        mPersonAddTvWorktype = findViewById(R.id.person_add_tv_worktype);
        mPersonAddTvFanchang = findViewById(R.id.person_add_tv_fanchang);
        mPersonAddTvAddinfo = findViewById(R.id.person_add_tv_addinfo);
        mPersonAddTvTeam = findViewById(R.id.person_add_tv_team);
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
    }
    
    private void hideSoftKeyBoard() {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(this.getWindow().getDecorView().getWindowToken(), 0);
    }
    
    private void setOnclick() {
        mPersonAddTvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginUtil.clearUserData();
                Intent i = new Intent(PersonAddActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
        mPersonAddImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mPersonAddLayoutSex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popShow(popupWindow_sex, popView_sex);
            }
        });
        mPersonAddLayoutLeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popShow(popupWindow_leader, popView_leader);
            }
        });
        mPersonAddLayoutPeixun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popShow(popupWindow_peixun, popView_peixun);
            }
        });
        mPersonAddLayoutChizheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popShow(popupWindow_chizheng, popView_chizheng);
            }
        });
        
        mPersonAddLayoutZhengzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popShow(popupWindow_zhengzhi, popView_zhengzhi);
            }
        });
        mPersonAddLayoutJiguan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popShow(popupWindow_jiguan, popView_jiguan);
            }
        });
        mPersonAddLayoutWenhua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popShow(popupWindow_wenhua, popView_wenhua);
            }
        });
        mPersonAddLayoutUnit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popShow(popupWindow_unit, popView_unit);
            }
        });
        mPersonAddLayoutTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popShow(popupWindow_team, popView_team);
            }
        });
        mPersonAddLayoutRole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popShow(popupWindow_role, popView_role);
            }
        });
        mPersonAddLayoutWorktype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popShow(popupWindow_worktype, popView_worktype);
            }
        });
        mPersonAddTvProChose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popShow(popupWindow_pro, popView_pro);
            }
        });
        mPersonAddTvAddinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(phone)) {
                    ToastUtil.showToast("请填写正确的手机号码");
                }
                else {
                    presenter.addEmployeesInfo("", "employeeBean.employee.idPhotoPath", "employeeBean.employee.photoPath", name,
                            id_number, mPersonAddTvSex.getText().toString(), mPersonAddTvBirthday.getText().toString(), minzu,
                            address, phone, mPersonAddTvZhengzhi.getText().toString(), mPersonAddTvJiguan.getText().toString(),
                            mPersonAddTvWenhua.getText().toString(), mPersonAddTvLeader.getText().toString(), mPersonAddTvPeixun.getText().toString(),
                            mPersonAddTvChizheng.getText().toString(), projectId, conditionsBean.units.get(index_unit).id, conditionsBean.teams.get(index_team).id,
                            conditionsBean.types.get(index_role).id, conditionsBean.types.get(index_role).value.get(index_worktype).id,
                            "30", zhengshu_number, hetong_number
                    );
                }
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
                name = mPersonAddEtName.getText().toString();
                id_number = mPersonAddEtId.getText().toString();
                minzu = mPersonAddEtMinzu.getText().toString();
                address = mPersonAddEtAddress.getText().toString();
                phone = mPersonAddEtPhone.getText().toString();
                zhengshu_number = mPersonAddEtZhengshu.getText().toString();
                hetong_number = mPersonAddEtHetong.getText().toString();
            }
            
            @Override
            public void afterTextChanged(Editable s) {
            }
        };
        mPersonAddEtName.addTextChangedListener(textWatcher);
        mPersonAddEtId.addTextChangedListener(textWatcher);
        mPersonAddEtMinzu.addTextChangedListener(textWatcher);
        mPersonAddEtAddress.addTextChangedListener(textWatcher);
        mPersonAddEtPhone.addTextChangedListener(textWatcher);
        mPersonAddEtZhengshu.addTextChangedListener(textWatcher);
        mPersonAddEtHetong.addTextChangedListener(textWatcher);
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
        popupwindow_list.setAdapter(new ArrayAdapter<String>(PersonAddActivity.this, R.layout.item_popupwindow, R.id.item_tv, dataList));
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
                        setPopupWindow("工种", mPersonAddTvWorktype, workTypeList, popupWindow_worktype, popView_worktype, 10);
                        mPersonAddTvWorktype.setText(workTypeList.get(0));
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
        setPopupWindow("性别", mPersonAddTvSex, sexList, popupWindow_sex, popView_sex, 0);
        setPopupWindow("政治面貌", mPersonAddTvZhengzhi, zhengzhiList, popupWindow_zhengzhi, popView_zhengzhi, 1);
        setPopupWindow("籍贯", mPersonAddTvJiguan, jiguanList, popupWindow_jiguan, popView_jiguan, 2);
        setPopupWindow("文化程度", mPersonAddTvWenhua, wenhuaList, popupWindow_wenhua, popView_wenhua, 3);
        setPopupWindow("班组长", mPersonAddTvLeader, booleanList, popupWindow_leader, popView_leader, 4);
        setPopupWindow("参加培训", mPersonAddTvPeixun, booleanList, popupWindow_peixun, popView_peixun, 5);
        setPopupWindow("持证上岗", mPersonAddTvChizheng, booleanList, popupWindow_chizheng, popView_chizheng, 6);
        setPopupWindow("所属单位", mPersonAddTvUnit, unitList, popupWindow_unit, popView_unit, 7);
        setPopupWindow("所属班组", mPersonAddTvTeam, teamList, popupWindow_team, popView_team, 8);
        setPopupWindow("角色", mPersonAddTvRole, roleList, popupWindow_role, popView_role, 9);
        setPopupWindow("工种", mPersonAddTvWorktype, workTypeList, popupWindow_worktype, popView_worktype, 10);
    }
    
    @Override
    public void setProjectInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
        pro_names = new ArrayList<>();
        for(int i = 0; i < userInfo.projects.size(); i++) {
            pro_names.add(userInfo.projects.get(i).name);
        }
        setPopupWindow("选择项目", mPersonAddTvProName, pro_names, popupWindow_pro, popView_pro, 11);
        mPersonAddTvProName.setText(pro_names.get(0));
    }
    
    
    @Override
    public void editResult(String s) {
        ToastUtil.showToast(s);
        finish();
        EventBus.getDefault().post(new EditEvent());
    }
    
}
