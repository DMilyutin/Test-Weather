package com.milyutin.dima.testtaskweather.view.CitiesActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;


import com.milyutin.dima.testtaskweather.R;
import com.milyutin.dima.testtaskweather.model.POJO.CityForRealm;
import com.milyutin.dima.testtaskweather.presenter.CitiesActivityPresenter;
import com.milyutin.dima.testtaskweather.utils.AppDialogs;
import com.milyutin.dima.testtaskweather.view.CityForecast.CitiesForecastActivity;


import io.realm.RealmResults;

public class CitiesActivity extends AppCompatActivity implements CitiesActivityView {

    private CitiesActivityPresenter citiesActivityPresenter;
    private AdapterCitiesList adapterCitiesList;

    private ListView lvCitiesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);

        if (citiesActivityPresenter==null)
            citiesActivityPresenter = new CitiesActivityPresenter(this, this);

        lvCitiesList = findViewById(R.id.lvCities);

        lvCitiesList.setOnItemClickListener((parent, view, position, id) -> {
            String nameCity = adapterCitiesList.getItem(position).getNameCity();
            citiesActivityPresenter.openForecast(nameCity);
        });

        citiesActivityPresenter.getCities(); // Загрузка списка городов из БД
    }

    /** Метод установки адаптера для листа */
    @Override
    public void setCitiesList(RealmResults<CityForRealm> citiesList) {
        adapterCitiesList = new AdapterCitiesList(citiesList, this);
        lvCitiesList.setAdapter(adapterCitiesList);
    }

    /**  */
    @Override
    public void openDetailCity(String nameCity) {
        Intent intent = new Intent(CitiesActivity.this, CitiesForecastActivity.class);
        intent.putExtra("NameCity", nameCity);
        startActivity(intent);
    }
    /** Метод указывающий пресентуру на добавления нового города */
    @Override
    public void addNewCity(String nameNewCity) {
        citiesActivityPresenter.addCityInRealm(nameNewCity);
    }
    /** Метод указывающий запустить диалоговое окно для добавления нового города */
    @Override
    public void openDialogNewCity() {
        new AppDialogs().addNewCityDialog(this, this);
    }

    /** Метод загрузки меню (установка иконки добавить) */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_new_city_menu, menu);
        return true;
    }
    /** Метод-слушатель нажатия на пункт меню */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.addNewCity:{
                citiesActivityPresenter.openDialogAddNewCity();
                break;
            }
        }
        return true;
    }

    /** Метод в котором необходимо закрывать Realm по документации */
    @Override
    protected void onDestroy() {
        citiesActivityPresenter.closeRealm();
        super.onDestroy();
    }
}
