package com.milyutin.dima.testtaskweather.model.data.api;

import com.milyutin.dima.testtaskweather.model.POJO.WeatherCityOneWeek;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServerAPI {

    @GET("forecast")
    Observable<Response<WeatherCityOneWeek>> getForecast(@Query("q") String nameCity,
                                                         @Query("appid") String appid,
                                                         @Query("units") String Metric);

}
