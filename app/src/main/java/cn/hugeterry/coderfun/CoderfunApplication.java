package cn.hugeterry.coderfun;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.umeng.update.UmengUpdateAgent;

/**
 * Created by hugeterry(http://hugeterry.cn)
 * Date: 16/2/22 02:04
 */
public class CoderfunApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        FlowManager.init(this);
        UmengUpdateAgent.update(this);
        UmengUpdateAgent.setUpdateOnlyWifi(false);
    }
}
