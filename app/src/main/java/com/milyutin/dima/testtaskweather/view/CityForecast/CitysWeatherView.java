package com.milyutin.dima.testtaskweather.view.CityForecast;

import com.milyutin.dima.testtaskweather.model.POJO.WeatherCityOneWeek;

public interface CitysWeatherView {

    void setForecastList(WeatherCityOneWeek weatherCityOneWeeks);
    void showAnswerDeleteCityDialog();
    void deleteThisCity();
    void showError(String error);
    void backToCitiesList();
}
