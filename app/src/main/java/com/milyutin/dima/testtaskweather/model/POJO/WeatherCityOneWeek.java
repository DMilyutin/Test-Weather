package com.milyutin.dima.testtaskweather.model.POJO;

import com.google.gson.annotations.SerializedName;

import java.util.List;
/** Класс основанный на ответе с сервера на запрос прогноза погоды */
public class WeatherCityOneWeek {

    private int cod;
    private double message;
    private int cnt;
    @SerializedName("list")
    private List<WeatherList> weatherList;
    private City city;

    public List<WeatherList> getWeatherList() {
        return weatherList;
    }

    public class WeatherList{
        private int dt;
        private Main main;
        private List<Weather> weather;
        private Clouds clouds;
        private Wind wind;
        private Rain rain;
        private Snow snow;
        private Sys sys;
        private String dt_txt;

        public String getDt_txt() {
            return dt_txt;
        }

        public Main getMain() {
            return main;
        }

        public List<Weather> getWeather() {
            return weather;
        }
    }

    public class Weather{
        private int id;
        private String main;
        private String description;
        private String icon;

        public String getMain() {
            return main;
        }

        public String getDescription() {
            return description;
        }
    }

    public class Main{
        private double temp;
        private double temp_min;
        private double temp_max;
        private double pressure;
        private double sea_level;
        private double grnd_level;
        private int humidity;
        private double temp_kf;

        public double getTemp() {
            return temp;
        }
    }



    public class Clouds{
        private int all;
    }

    public class Wind{
        private double speed;
        private double deg;
    }

    public  class Rain{
        @SerializedName("3h")
        private double rain;
    }

    public class Snow{
        @SerializedName("3h")
        private double snow;
    }

    public class Sys{
        private String pod;
    }

    public class City {
        private int geoname_id;
        private String name;
        private double lat;
        private double lon;
        private String country;
        private String iso2;
        private String type;
        private int population;

    }
}







