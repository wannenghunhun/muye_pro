package com.framwork.main;


public class GlobalConstants {
    //参数配置类
    public static class ParamConstants {
        public static String APP_ID = "2t5HUtnH9fu5XrQgpLF9AiNaAsiY7hNppqL868rGCzbd";
        public static String SDK_KEY = "ALedCWshfjtMS26Kjp1ZNAFPngFHj1t6LRPaZnqmuWue";
        public static String SOFT_ID = "eda6bece9a07544caf0b7440916b829f";
        public static String LOG_ID = "87460b34999165ba5d3080d116aa65c5";
        public static String FACE_SERVER = "http://47.93.180.222:9002/";
    }
    
    //SP管理类
    public static class SPConstants {
        public static String SETTING_PARAM = "setting_param";
    }
    
    //URL管理类
    public static class URLConstants {
        public static String BASE_URL = "https://app.muyexiang.com/";
    }
    
    //接口名管理类
    public static class InterfaceNameConstants {
        //登录
        public static final String LOGIN = "login";
        //获取用户信息
        public static final String USER_INFO = "user/info";
        //获取项目信息接口
        public static final String PROJECT = "project/";
        //分页获取项目所属工人信息
        public static final String PROJECT_EMPLOYEES = "project/employees";
        //获取项目雇员选项信息
        public static final String CONDITIONS = "/conditions";// project/{projectId}/conditions
        //获取人员基本信息
        public static final String EMPLOYEE = "employee/";// project/employee/{id}
    }
    
}
