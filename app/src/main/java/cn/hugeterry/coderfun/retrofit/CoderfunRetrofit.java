package cn.hugeterry.coderfun.retrofit;

import com.squareup.okhttp.OkHttpClient;

import cn.hugeterry.coderfun.CoderfunKey;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;


/**
 * Created by hugeterry(http://hugeterry.cn)
 * Date: 16/2/15 21:37
 */
public class CoderfunRetrofit {
    private static OkHttpClient httpClient = new OkHttpClient();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(CoderfunKey.API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

    public <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(httpClient).build();
        return retrofit.create(serviceClass);
    }
}
