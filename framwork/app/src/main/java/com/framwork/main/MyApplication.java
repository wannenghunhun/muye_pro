package com.framwork.main;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.support.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.framwork.common.GlobalContext;
import com.framwork.common.helper.AppActiveHelper;
import com.framwork.common.helper.HotFixHelper;
import com.framwork.common.helper.ServerHelper;
import com.framwork.common.helper.net.ReleaseServerConfig;
import com.framwork.common.ui.activity.DLRActivityLifecycle;
import com.framwork.common.ui.fragment.DLRFragmentLifecycle;
import com.framwork.common.ui.lifecycle.ActivityLifecycle;
import com.framwork.common.ui.lifecycle.FragmentLifecycle;
import com.framwork.common.ui.lifecycle.IActivityLifecycle;
import com.framwork.common.ui.lifecycle.IFragmentLifecycle;
import com.framwork.common.utils.AppUtil;
import com.framwork.common.utils.GlobalTools;
import com.framwork.main.http.interceptor.RequestEncryptInterceptor;
import com.framwork.main.http.interceptor.ResponseDecryptInterceptor;
import com.framwork.main.http.interceptor.ResponseGzipInterceptor;
import com.framwork.okhttputils.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;


public class MyApplication extends Application {


    @Override
    protected final void attachBaseContext(Context base) {//兼容5.0以下系统
        super.attachBaseContext(base);
        MultiDex.install(base);
//        HotFixHelper.install(this);
    }

    @Override
    public final void onCreate() {
        super.onCreate();
        GlobalContext.setContext(this);
        if (AppUtil.isInitAble()) {
            onceInstall();
            new Handler().postDelayed(new Runnable() {
                                          @Override
                                          public void run() {
                                              delayInstall();
                                          }
                                      },
                    Math.max(3000L - 500L, 0L));
        }
    }


    protected void onceInstall() {
        GlobalTools.installLog(BuildConfig.SHOW_LOG);
        ARouter.init(this);
        HotFixHelper.init(this);
        addActivityLifeCycle(new DLRActivityLifecycle());
        addFragmentLifeCycle(new DLRFragmentLifecycle());
        ServerHelper.init(ServerHelper.ServerReleaseType, new ReleaseServerConfig());
        AppActiveHelper.install(this);

    }

    protected void delayInstall() {
        initOkHttpClient();
    }


    protected void initOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(new RequestEncryptInterceptor())  // 请求参数加密拦截器
                .addNetworkInterceptor(new ResponseDecryptInterceptor()) // 响应解密拦截器
                .addNetworkInterceptor(new ResponseGzipInterceptor()); // Gzip 解压拦截器
        OkHttpUtils.init(builder, true);
    }

    protected final void addActivityLifeCycle(IActivityLifecycle activityLife) {
        ActivityLifecycle.addActivityLife(activityLife);
    }

    protected final void addFragmentLifeCycle(IFragmentLifecycle iFragmentLife) {
        FragmentLifecycle.addFragmentLife(iFragmentLife);
    }


}