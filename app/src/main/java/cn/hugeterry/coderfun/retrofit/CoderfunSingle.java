package cn.hugeterry.coderfun.retrofit;

/**
 * Created by hugeterry(http://hugeterry.cn)
 * Date: 16/2/15 22:15
 */
public class CoderfunSingle {

    private volatile static CoderfunAPI CoderfunIsCacheAPI = null;
    private volatile static CoderfunAPI CoderfunNoCacheAPI = null;

    public static CoderfunAPI getInstance(boolean isCache) {
        if (isCache) {
            return getCoderfunAPI(true, CoderfunIsCacheAPI);
        } else {
            return getCoderfunAPI(false, CoderfunNoCacheAPI);
        }
    }

    private static CoderfunAPI getCoderfunAPI(boolean isCache, CoderfunAPI coderfunAPI) {
        if (coderfunAPI == null) {
            synchronized (CoderfunSingle.class) {
                if (coderfunAPI == null) {
                    coderfunAPI = new CoderfunRetrofit().createService(CoderfunAPI.class, isCache);
                }
            }
        }
        return coderfunAPI;
    }
}
