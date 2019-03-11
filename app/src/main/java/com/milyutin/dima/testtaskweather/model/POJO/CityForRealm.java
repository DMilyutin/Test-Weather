package com.milyutin.dima.testtaskweather.model.POJO;

import io.realm.RealmObject;
import io.realm.annotations.Required;

/** Класс на основе короторого хранится список городов в БД Realm */
public class CityForRealm extends RealmObject {

    @Required
    private String nameCity;

    public String getNameCity() {
        return nameCity;
    }

    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
    }
}
