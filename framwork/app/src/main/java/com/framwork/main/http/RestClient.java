package com.framwork.main.http;

import android.app.Activity;

import com.framwork.common.helper.ServerHelper;
import com.framwork.common.utils.LogUtil;
import com.framwork.common.utils.LoginUtil;
import com.framwork.main.GlobalConstants;
import com.framwork.okhttputils.OkHttpUtils;
import com.framwork.okhttputils.builder.PostFormBuilder;
import com.framwork.okhttputils.builder.PostStringBuilder;
import com.framwork.okhttputils.callback.FileCallBack;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.IdentityHashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.MediaType;


public class RestClient {
    public static final String DATA_KEY = "data";
    
    /**
     * 带tag的post请求
     *
     * @param params
     * @param callback
     */
    public static void postWithTag(Object o, String interfaceName, JSONObject params, GsonHttpCallback callback) {
        basePost(o, GlobalConstants.URLConstants.BASE_URL + interfaceName, params, interfaceName, callback);
    }
    
    /**
     * 表单的post请求
     *
     * @param params
     * @param callback
     */
    public static void postWithForm(String interfaceName, Map<String, String> params, GsonHttpCallback callback) {
        String url = GlobalConstants.URLConstants.BASE_URL + interfaceName;
        PostFormBuilder postFormBuilder = OkHttpUtils.postForm().url(url).params(params);
        postFormBuilder.build().execute(callback);
        LogUtil.e("--->请求URL: %s \n--->请求接口名: %s \n--->请求数据:-- %s", url, interfaceName, params);
    }
    
    
    private static void basePost(Object activity, String url, JSONObject
            params, String interfaceName, GsonHttpCallback callback) {
        String realParams = getParams(url, params, interfaceName);
        PostStringBuilder postStringBuilder = OkHttpUtils.postString()
                .url(url)
                .mediaType(MediaType.parse("application/x-www-form-urlencoded"))
                .headers(ServerHelper.headers(url, interfaceName))
                .content(params.toString());
        if(activity != null) {
            postStringBuilder.tag(activity);
        }
        LogUtil.e("--->请求URL: %s \n--->请求接口名: %s \n--->请求数据:-- %s", url, interfaceName, params.toString());
        postStringBuilder.build().execute(callback);
        
    }
    
    
    private static Map<String, String> commonHeaders() {
        Map<String, String> headers = new IdentityHashMap<>();
        headers.put("token", LoginUtil.getToken());
        //                headers.put("Content-Type", "app/plain");
        //        headers.put("device", "android");
        //        headers.put("deviceVer", DeviceUtil.getDeviceType() + "[" + DeviceUtil.getSystemVersion() + "]");
        //        headers.put("appName", "zw");
        //        headers.put("platform", "app");
        //        headers.put("appMarket", ChannelUtils.getChannel());
        //        headers.put("appVer", AppUtil.getVersionName());
        return headers;
    }
    
    
    /**
     * 根据tag取消请求
     *
     * @param activity
     */
    public static void cancelRequest(Object activity) {
        OkHttpUtils.getInstance().cancelTag(activity);
    }
    
    /**
     * post参数
     *
     * @param jsonObject
     * @return
     */
    public static String getParams(String url, JSONObject jsonObject, String interfaceName) {
        JSONObject param = new JSONObject();
        try {
            if(jsonObject == null) {
                jsonObject = new JSONObject();
            }
            param.put(DATA_KEY, jsonObject);
        } catch(JSONException e) {
            e.printStackTrace();
        }
        //        setCommonParams(param, interfaceName);
        return param.toString();
    }
    
    
}
