package com.milyutin.dima.testtaskweather.view.CityForecast;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.milyutin.dima.testtaskweather.R;
import com.milyutin.dima.testtaskweather.model.POJO.WeatherCityOneWeek;
import com.milyutin.dima.testtaskweather.presenter.ForecastCitiesPresenter;
import com.milyutin.dima.testtaskweather.utils.AppDialogs;
/** Activity с прогнозом погоды  */
public class CitiesForecastActivity extends AppCompatActivity implements CitysWeatherView{

    private ForecastCitiesPresenter forecastCitiesPresenter;
    private ListView lvForecast;
    private String nameCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citys_weather);

        Intent intent = getIntent();
        nameCity = intent.getStringExtra("NameCity");

        forecastCitiesPresenter = new ForecastCitiesPresenter(this, this);

        TextView tvNameCity = findViewById(R.id.tvNameCityDetailForecast);
        lvForecast = findViewById(R.id.lvDetailForecast);

        tvNameCity.setText(nameCity);
        forecastCitiesPresenter.getForecast(nameCity);
    }
    /** Метод установки адаптера для листа */
    @Override
    public void setForecastList(WeatherCityOneWeek weatherCityOneWeeks) {
        AdapterForecast adapterForecast = new AdapterForecast(this, weatherCityOneWeeks.getWeatherList());
        lvForecast.setAdapter(adapterForecast);
    }
    /** Метод указывающий запустить диалоговое окно для получения подтверждения на удаления города из списка */
    @Override
    public void showAnswerDeleteCityDialog() {
        new AppDialogs().answerDeleteCityDialog(this, this);
    }
    /** Метод указывающий пресентору удалить город из БД */
    @Override
    public void deleteThisCity() {
        forecastCitiesPresenter.deleteCityFromRealm(nameCity);
    }
    /** Метод запуска диалога с указание ошибки (Используется при ошибки загрузки данных с сервера) */
    @Override
    public void showError(String error) {
        new AppDialogs().showErrorDialog(this, error);
        Log.i("Loog", "Погода в Москве error - " + error );
    }
    /** Метод возрата на activity со списком городов после удаления текущего города */
    @Override
    public void backToCitiesList() {
        onBackPressed();
    }
    /** Метод загрузки меню (установка иконки удалить) */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.delete_city_menu, menu);
        return true;
    }
    /** Метод-слушатель нажатия на пункт меню */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.deleteCity : {
                forecastCitiesPresenter.onenAnswerDeleteDialog();
                break;
            }
        }
        return true;
    }

    /** Метод в котором необходимо закрывать Realm по документации */
    @Override
    protected void onDestroy() {
        forecastCitiesPresenter.clouseRealm();
        super.onDestroy();
    }
}
