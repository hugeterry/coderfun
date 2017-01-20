package cn.hugeterry.coderfun;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.umeng.update.UmengUpdateAgent;

/**
 * Created by hugeterry(http://hugeterry.cn)
 * Date: 16/2/22 02:04
 */
public class CoderfunApplication extends Application{

    private static Context appContext;

    /**
     * 获取Application的Context
     *
     * @return 全局Context
     */
    public static Context getAppContext() {
        return appContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();

        Fresco.initialize(this);
        FlowManager.init(this);
    }
}
