package com.milyutin.dima.testtaskweather.view.CitiesActivity;



import com.milyutin.dima.testtaskweather.model.POJO.CityForRealm;

import java.util.List;

import io.realm.RealmResults;

public interface CitiesActivityView {

    void setCitiesList(RealmResults<CityForRealm> citiesList);
    void openDetailCity(String nameCity);
    void addNewCity(String nameNewCity);
    void openDialogNewCity();

}
