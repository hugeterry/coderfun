package cn.hugeterry.coderfun.retrofit;

import cn.hugeterry.coderfun.beans.DataResults;
import cn.hugeterry.coderfun.beans.DayResults;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by hugeterry(http://hugeterry.cn)
 * Date: 16/2/15 22:15
 */
public class CoderfunSingle{

    static CoderfunAPI CoderfunAPISingleton = null;

    public static CoderfunAPI getInstance() {
        if (CoderfunAPISingleton == null) {
            synchronized (CoderfunSingle.class) {
                if (CoderfunAPISingleton == null) {
                    CoderfunAPISingleton = new CoderfunRetrofit().createService(CoderfunAPI.class);
                }
            }
        }
        return CoderfunAPISingleton;
    }

}
