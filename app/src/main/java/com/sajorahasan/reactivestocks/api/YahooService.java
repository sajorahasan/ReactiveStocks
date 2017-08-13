package com.sajorahasan.reactivestocks.api;

import com.sajorahasan.reactivestocks.api.gson.YahooStockResult;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Sajora on 06-08-2017.
 */

public interface YahooService {

    @GET("yql?format=json")
    Single<YahooStockResult> yqlQuery(@Query("q") String query,
                                      @Query("env") String env);
}
