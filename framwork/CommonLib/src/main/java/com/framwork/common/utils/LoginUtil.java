package com.framwork.common.utils;

/**
 */
public class LoginUtil {
    
    public static final String TOKEN = "token";
    public static final String AUTH = "auth";
    public static final String PHONE = "phone";
    public static final String EMPLOYEEID = "employeeId";
    public static final String PHONE_MI = "phone_other";
    public static final String AUTHORIZED = "1";    //1、已实名
    
    public static boolean isLogin() {
        return StringUtil.isNotEmpty(getToken());
    }
    
    /**
     * 用户主动退出登录，清空当前用户数据
     */
    public static void logOut() {
        clearUserData();
        DialogManager.getInstance().clearDialog();
        //        EventBus.getDefault().post(new LoginEvent.LoginOff());
    }
    
    /**
     * 登出并重新登录
     */
    public static void forceLogOut() {
        logOut();
        goLogin();
        //        ActivityManager.retain(LoginActivity.class);
        
    }
    
    
    public static void goLogin() {
        // 发起登录
        //        ARouter.getInstance().build(RouterConstants.ROUTER_LOGIN_KEY).navigation();
    }
    
    /**
     * 获取用户token
     *
     * @return
     */
    public static String getToken() {
        //获取token
        return SPManager.getDefaultManager().getString(TOKEN,"");
    }
    
    public static void saveToken(String token) {
        SPManager.getDefaultManager().commitString(TOKEN, token);
        //        EventBus.getDefault().post(new LoginEvent.LoginOn());
    }
    
    /**
     * 保存实名信息
     *
     * @param auth
     */
    public static void saveAuth(String auth) {
        SPManager.getDefaultManager().commitBoolean(AUTH, AUTHORIZED.equals(auth) ? true : false);
    }
    
    public static boolean getAuth() {
        return SPManager.getDefaultManager().getBoolean(AUTH, false);
    }
    
    
    public static void clearUserData() {
        SPManager.getDefaultManager().clear();
    }
}
