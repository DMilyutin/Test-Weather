package com.milyutin.dima.testtaskweather.model.repository;

import com.milyutin.dima.testtaskweather.model.POJO.WeatherCityOneWeek;
/** Интерфейс для обработки ответа с сервера  */
public interface CallBackForecast {
    void myForecast(WeatherCityOneWeek weatherCityOneWeekList);
    void forecastError(String error);
}
