package com.milyutin.dima.testtaskweather.presenter;

import android.content.Context;

import com.milyutin.dima.testtaskweather.model.POJO.CityForRealm;
import com.milyutin.dima.testtaskweather.model.POJO.WeatherCityOneWeek;
import com.milyutin.dima.testtaskweather.model.repository.CallBackForecast;
import com.milyutin.dima.testtaskweather.model.repository.FailCallBack;
import com.milyutin.dima.testtaskweather.model.repository.RepositoryApi;
import com.milyutin.dima.testtaskweather.utils.Initialization;
import com.milyutin.dima.testtaskweather.view.CityForecast.CitysWeatherView;

import io.realm.Realm;
import io.realm.RealmResults;

/** Пресентор activity {@link com.milyutin.dima.testtaskweather.view.CityForecast.CitiesForecastActivity} */
public class ForecastCitiesPresenter {

    /** Интерфейс управления activity {@link com.milyutin.dima.testtaskweather.view.CityForecast.CitiesForecastActivity}*/
    private CitysWeatherView citysWeatherView;
    /** Экземпляр класса RepositoryApi для отправки запросов на сервер */
    private RepositoryApi repositoryApi;
    /** Экземпляр класса Realm для работы с БД  */
    private Realm realm;

    public ForecastCitiesPresenter(CitysWeatherView citysWeatherView, Context context) {
        this.citysWeatherView = citysWeatherView;
        repositoryApi = Initialization.getInstance().getRepositoryApi();
        realm = Realm.getInstance(context);
    }
    /** Метод запроса данных о прогнозе погоды на сервер  */
    public void getForecast(String nameCity){
        repositoryApi.getForecast(nameCity, new CallBackForecast() {
            @Override
            public void myForecast(WeatherCityOneWeek weatherCityOneWeekList) {
                citysWeatherView.setForecastList(weatherCityOneWeekList);
            }

            @Override
            public void forecastError(String error) {
                citysWeatherView.showError(error);
            }
        }, new FailCallBack() {
            @Override
            public void throwableError(Throwable throwable) {
                citysWeatherView.showError(throwable.getMessage());
            }
        });
    }
    /** Метод запуска диалога подтверждения удаления города */
    public void onenAnswerDeleteDialog(){
        citysWeatherView.showAnswerDeleteCityDialog();
    }
    /** Метод удаления города из списка сохраненных городов в БД */
    public void deleteCityFromRealm(String nameCity){
        realm.beginTransaction();
        RealmResults<CityForRealm> city = realm.where(CityForRealm.class).equalTo("nameCity", nameCity).findAll();
        if (!city.isEmpty()){
            for(int i = 0; i<city.size(); i++){
                city.get(i).removeFromRealm();
            }
        }
        realm.commitTransaction();
        citysWeatherView.backToCitiesList();
    }
    /** Метод закрытия БД Realm */
    public void clouseRealm(){
        realm.close();
    }
}
