package com.milyutin.dima.testtaskweather.view.CitiesActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.milyutin.dima.testtaskweather.R;
import com.milyutin.dima.testtaskweather.model.POJO.CityForRealm;

import io.realm.RealmChangeListener;
import io.realm.RealmResults;
/** Адаптер для представления списка городов взятого с БД */
public class AdapterCitiesList extends BaseAdapter implements RealmChangeListener {

    /** Список городов из БД Realm  */
    private RealmResults<CityForRealm> mCities;
    private Context context;

    public AdapterCitiesList(RealmResults<CityForRealm> cities, Context context) {
        this.context = context;
        mCities = cities;
        mCities.addChangeListener(this);
    }

    @Override
    public int getCount() {
        return mCities.size();
    }

    @Override
    public CityForRealm getItem(int position) {
        return mCities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.for_list_cities, null);
        }
        /** Запись названия города в списке городов */
        ((TextView) convertView.findViewById(R.id.tvNameCityForList)).setText(mCities.get(position).getNameCity());

        return convertView;

    }
    /** Метод для автообновления БД в случае удаления/изменения/добавления данных */
    @Override
    public void onChange() {
        notifyDataSetChanged();
    }
}
