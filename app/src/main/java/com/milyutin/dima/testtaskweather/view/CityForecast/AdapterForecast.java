package com.milyutin.dima.testtaskweather.view.CityForecast;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.milyutin.dima.testtaskweather.R;
import com.milyutin.dima.testtaskweather.model.POJO.WeatherCityOneWeek;

import java.util.List;
/** Адаптер для представления прогноза погоды взятого с сервера */
public class AdapterForecast extends BaseAdapter {

    private Context context;
    /** Лист типа WeatherList составленного на основании ответа с сервера */
    private List<WeatherCityOneWeek.WeatherList> weatherLists;

    public AdapterForecast(Context context, List<WeatherCityOneWeek.WeatherList> weatherLists) {
        this.context = context;
        this.weatherLists = weatherLists;
    }

    @Override
    public int getCount() {
        return weatherLists.size();
    }

    @Override
    public WeatherCityOneWeek.WeatherList getItem(int position) {
        return weatherLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.for_list_forecast, null);
        }
        /** Запись даты, времени, температуры и описания в списке прогноза */
        ((TextView) convertView.findViewById(R.id.tvDateForecastList)).setText(getItem(position).getDt_txt());
        ((TextView) convertView.findViewById(R.id.tvTempForecastList)).setText(String.valueOf(getItem(position).getMain().getTemp()));
        ((TextView) convertView.findViewById(R.id.tvDecForecastList)).setText(getItem(position).getWeather().get(0).getDescription());


        return convertView;
    }
}
