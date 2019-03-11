package com.milyutin.dima.testtaskweather.utils;

import com.milyutin.dima.testtaskweather.BuildConfig;
import com.milyutin.dima.testtaskweather.model.data.api.ServerAPI;
import com.milyutin.dima.testtaskweather.model.repository.RepositoryApi;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Initialization {

    /** Класс Initialization предназначен для первоначальной настройки всех необходимых компонентов
     *
     * */
    private final String URL = "Https://api.openweathermap.org/data/2.5/";
    private  static Initialization myInitialization;

    private RepositoryApi repositoryApi;

    private Initialization() {
        repositoryApi = getRepositoryAPI(serverApi(getRetrofit(getOkHttpClient(getHttpLoggingInterceptor()))));
    }

    /** Используется паттерн Singleton для существования одного экземпляра данного класса */
    public static Initialization getInstance(){
        if (myInitialization == null)
            myInitialization = new Initialization();
        return myInitialization;
    }

    private HttpLoggingInterceptor getHttpLoggingInterceptor(){
        return new HttpLoggingInterceptor()
                .setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
    }

    private OkHttpClient getOkHttpClient(HttpLoggingInterceptor interceptor){
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .build();
    }


    private Retrofit getRetrofit(OkHttpClient client){
        return new Retrofit.Builder()

                .baseUrl(URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
    }

    private ServerAPI serverApi(Retrofit retrofit){
        return retrofit.create(ServerAPI.class);
    }

    private RepositoryApi getRepositoryAPI(ServerAPI serverApi){
        return new RepositoryApi(serverApi);
    }

    public RepositoryApi getRepositoryApi() {
            return repositoryApi;
    }
}
