package cn.hugeterry.coderfun.retrofit;

import java.io.File;
import java.util.concurrent.TimeUnit;

import cn.hugeterry.coderfun.CoderfunApplication;
import cn.hugeterry.coderfun.CoderfunKey;
import cn.hugeterry.coderfun.utils.CacheStrategyInterceptor;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hugeterry(http://hugeterry.cn)
 * Date: 16/2/15 21:37
 */
public class CoderfunRetrofit {

    private static OkHttpClient httpClient;

    private OkHttpClient createHttpClient(boolean isCache) {
        if (isCache) {
            File httpCacheDirectory = new File(CoderfunApplication.getAppContext().getExternalCacheDir().getAbsolutePath(), "responses");
            Cache cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);
            CacheStrategyInterceptor cacheStrategyInterceptor = new CacheStrategyInterceptor();
            httpClient = new OkHttpClient
                    .Builder()
                    .cache(cache)
                    .addInterceptor(cacheStrategyInterceptor)
                    .addNetworkInterceptor(cacheStrategyInterceptor)
                    .retryOnConnectionFailure(true)
                    .connectTimeout(7, TimeUnit.SECONDS)
                    .build();
        } else {
            httpClient = new OkHttpClient();
        }
        return httpClient;
    }

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(CoderfunKey.API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

    public <S> S createService(Class<S> serviceClass, boolean isCache) {
        Retrofit retrofit = builder.client(createHttpClient(isCache)).build();
        return retrofit.create(serviceClass);
    }
}
