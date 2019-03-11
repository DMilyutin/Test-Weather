package com.milyutin.dima.testtaskweather.model.repository;

import com.milyutin.dima.testtaskweather.model.data.api.ServerAPI;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RepositoryApi {
    /** API Key полученный при регистрации на openweathermap.org нужный для запросов к сервису */
    private final String myKey = "9b1414aba853d15ab6946a1fcf0c0d3f";
    private ServerAPI serverAPI;

    public RepositoryApi(ServerAPI serverAPI) {
        this.serverAPI = serverAPI;
    }

    public void getForecast(String nameCity, CallBackForecast callBackForecast, FailCallBack failCallBack){
        serverAPI.getForecast(nameCity, myKey, "metric")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(stringResponse -> {
                    if (stringResponse.isSuccessful())
                        callBackForecast.myForecast(stringResponse.body());
                    else
                        callBackForecast.forecastError(stringResponse.errorBody().string());
                }, failCallBack::throwableError);
    }
}
