package com.framwork.main.router;

import com.framwork.common.utils.StringUtil;

import java.util.HashMap;

/**
 *
 */
public class RouterConstants {
    
    public static final String ROUTER_PATH_KEY = "path";
    
    //Router_Key
    public static final String ROUTER_INVALID_KEY = "nonTransition"; // 无效url 不处理
    public static final String ROUTER_TEST_KEY = "test";// 主页面，自定义的主页面
    public static final String ROUTER_HOMEPAGE_KEY = "homePage";// 首页
    public static final String ROUTER_WORKPAGE_KEY = "workPage";// 工作室
    public static final String ROUTER_MINEPAGE_KEY = "minePage";// 我的
    public static final String ROUTER_CALLSERVICE_KEY = "callService";//  打电话
    
    
    //Router_Target
    public static final String ROUTER_INVALID = ""; // 无效url 不处理
    public static final String ROUTER_TEST = "/app/MainBActivitya";// 主页面，自定义的主页面
    
    
    private static final HashMap<String, String> pageMap = new HashMap<String, String>() {{
        put(ROUTER_INVALID_KEY, ROUTER_INVALID); // 无效url 不处理
        put(ROUTER_TEST_KEY, ROUTER_TEST);
        
    }};
    
    
    public static String getRealPath(String originPath) {
        String realPath = pageMap.get(originPath);
        if(StringUtil.isEmpty(realPath)) { // 未找到，
            realPath = originPath;// 原样返回
        }
        return realPath;
    }
    
    public static class Extras {
        
        public static final int LOGIN_NEEDED = 1;
        
        
    }
    
    /**
     * 拦截服务
     */
    public static class Service {
        // url 重定向
        public static final String PATH_REPLACE_SERVICE = "/replace/path";
        public static final String DEGRADE_SERVICE = "/degrade/service";
        public static final String SERVICE_JSON = "/service/json";
    }
    
    /**
     * 拦截器
     */
    public static class Interceptor {
        // 登录拦截--未登录时必须登录
        public static final String LOGIN = "login";
        
    }
    
    
}
