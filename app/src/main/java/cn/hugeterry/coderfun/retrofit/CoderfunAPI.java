package cn.hugeterry.coderfun.retrofit;

import cn.hugeterry.coderfun.model.beans.DataResults;
import cn.hugeterry.coderfun.model.beans.DayResults;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by hugeterry(http://hugeterry.cn)
 * Date: 16/2/15 19:19
 */
public interface CoderfunAPI {
    @GET("data/{type}/{number}/{page}")
    Observable<DataResults> getDataResults(
            @Path("type") String type,
            @Path("number") int number,
            @Path("page") int page
    );

    @GET("day/{year}/{month}/{day}")
    Observable<DayResults> getDayResults(
            @Path("year") int year,
            @Path("month") int month,
            @Path("day") int day
    );

}
