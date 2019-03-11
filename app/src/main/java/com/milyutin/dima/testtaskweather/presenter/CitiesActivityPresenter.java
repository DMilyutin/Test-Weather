package com.milyutin.dima.testtaskweather.presenter;


import android.content.Context;

import com.milyutin.dima.testtaskweather.model.POJO.CityForRealm;
import com.milyutin.dima.testtaskweather.view.CitiesActivity.CitiesActivityView;

import io.realm.Realm;
/** Пресентор activity {@link com.milyutin.dima.testtaskweather.view.CitiesActivity.CitiesActivity} */
public class CitiesActivityPresenter {

    /** Интерфейс управления activity {@link com.milyutin.dima.testtaskweather.view.CitiesActivity.CitiesActivity} */
    private CitiesActivityView citiesActivityView;
    /** Экземпляр класса Realm для работы с БД  */
    private Realm realm;

    public CitiesActivityPresenter(CitiesActivityView citiesActivityView, Context context) {
        this.citiesActivityView = citiesActivityView;
        realm = Realm.getInstance(context);
        emptyCitiesList();
    }
    /** Метод загрузки дефолтных городов */
    private void emptyCitiesList(){
        if (realm.allObjects(CityForRealm.class).size()==0){
            addCityInRealm("Москва");
            addCityInRealm("Самара");
        }
    }
     /** Метод добавления нового города в БД */
    public void addCityInRealm(String nameCity){
        realm.beginTransaction();
        CityForRealm city = realm.createObject(CityForRealm.class);
        city.setNameCity(nameCity);
        realm.commitTransaction();
    }
    /** Метод открытия activity с прогнозом выбранного города
     *  {@link com.milyutin.dima.testtaskweather.view.CityForecast.CitiesForecastActivity}
     *  */
    public void openForecast(String nameCity){
        citiesActivityView.openDetailCity(nameCity);
    }
    /** Метод получения всех сохраненных городов с БД */
    public void getCities(){
        citiesActivityView.setCitiesList(realm.allObjects(CityForRealm.class));
    }
    /** Метод закрытия БД Realm */
    public void closeRealm(){
        realm.close();
    }
    /** Метод запуска диалога с полем ввода нового города */
    public void openDialogAddNewCity(){
        citiesActivityView.openDialogNewCity();
    }
}
