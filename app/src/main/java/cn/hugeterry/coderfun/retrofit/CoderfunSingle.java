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
            if (CoderfunIsCacheAPI == null) {
                synchronized (CoderfunSingle.class) {
                    if (CoderfunIsCacheAPI == null) {
                        CoderfunIsCacheAPI = new CoderfunRetrofit().createService(CoderfunAPI.class, true);
                    }
                }
            }
            return CoderfunIsCacheAPI;
        } else {
            if (CoderfunNoCacheAPI == null) {
                synchronized (CoderfunSingle.class) {
                    if (CoderfunNoCacheAPI == null) {
                        CoderfunNoCacheAPI = new CoderfunRetrofit().createService(CoderfunAPI.class, false);
                    }
                }
            }
            return CoderfunNoCacheAPI;
        }
    }

}
