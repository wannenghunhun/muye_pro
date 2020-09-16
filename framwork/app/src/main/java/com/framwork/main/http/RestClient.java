package com.framwork.main.http;

import android.app.Activity;

import com.framwork.common.helper.ServerHelper;
import com.framwork.common.helper.net.ReleaseServerConfig;
import com.framwork.common.utils.LogUtil;
import com.framwork.okhttputils.OkHttpUtils;
import com.framwork.okhttputils.builder.PostStringBuilder;
import com.framwork.okhttputils.callback.FileCallBack;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import okhttp3.MediaType;


public class RestClient {


    public static final String SYS_NAME_KEY = "sysName";
    public static final String API_NAME_KEY = "apiName";
    public static final String API_VER_KEY = "apiVer";
    public static final String DATA_KEY = "data";


    public static final String SYS_NAME_VALUE = "zw";

    public static final String API_VER_VALUE = "v1";


    /**
     * 带tag的post请求
     *
     * @param params
     * @param callback
     */
    public static void postWithTag(Object o, String interfaceName, JSONObject params, GsonHttpCallback callback) {
        basePost(o, ReleaseServerConfig.SERVER_API, params, interfaceName, callback);
    }

    /**
     * 带tag的post请求,不指定请求参数
     *
     * @param callback
     */
    public static void postWithTag(Object o, String interfaceName, GsonHttpCallback callback) {
        postWithTag(o, interfaceName, null, callback);
    }

    private static void basePost(Object activity, String url, JSONObject
            params, String interfaceName, GsonHttpCallback callback) {
        String realParams = getParams(url, params, interfaceName);
        PostStringBuilder postStringBuilder = OkHttpUtils.postString()
                .url(url)
                .mediaType(MediaType.parse("text/plain"))
                .headers(ServerHelper.headers(url, interfaceName))
                .content(realParams);
        if (activity != null) {
            postStringBuilder.tag(activity);
        }
        LogUtil.e("--->请求URL: %s \n--->请求接口名: %s \n--->请求数据:-- %s", url, interfaceName, realParams);
        postStringBuilder.build().execute(callback);

    }


    /**
     * 上传文件
     *
     * @param url
     * @param file
     * @param callback
     */

    public static void postFile(Activity activity, String url, File file, GsonHttpCallback callback) {
        OkHttpUtils.post().url(url).addFile("uploadfile", file.getName(), file).tag(activity)
                .build().execute(callback);
    }

    /**
     * 下载文件
     *
     * @param url
     * @param callback 需要传入保存的文件夹以及文件名
     */
    public static void downloadFile(String url, FileCallBack callback) {
        OkHttpUtils.get().url(url).build().execute(callback);
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
            if (jsonObject == null) {
                jsonObject = new JSONObject();
            }
            param.put(DATA_KEY, jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        setCommonParams(param, interfaceName);
        return param.toString();
    }

    private static void setCommonParams(JSONObject jsonObject, String interfaceName) {
        try {
            jsonObject.put(SYS_NAME_KEY, SYS_NAME_VALUE);
            jsonObject.put(API_NAME_KEY, interfaceName);
            jsonObject.put(API_VER_KEY, API_VER_VALUE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
