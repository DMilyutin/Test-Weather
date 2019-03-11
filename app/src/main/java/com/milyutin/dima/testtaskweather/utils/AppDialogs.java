package com.milyutin.dima.testtaskweather.utils;

import android.support.v7.app.AlertDialog;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.milyutin.dima.testtaskweather.R;
import com.milyutin.dima.testtaskweather.view.CitiesActivity.CitiesActivityView;
import com.milyutin.dima.testtaskweather.view.CityForecast.CitysWeatherView;
/** Класс с вынесенными диалогами  */
public class AppDialogs {

    private AlertDialog alertDialog;

    /** Метод вызова диалога для добавления нового города в activity
     * {@link com.milyutin.dima.testtaskweather.view.CitiesActivity.CitiesActivity}
     * */
    public void addNewCityDialog(Context context, CitiesActivityView citiesActivityView){

        LayoutInflater inflater = LayoutInflater.from(context);
        View dialog = inflater.inflate(R.layout.for_dialog_add_new_city, null);

        EditText etNewCity = dialog.findViewById(R.id.etNameNewCity);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Укажите новый город")
        .setPositiveButton("Добавить", (dialog1, which) -> {
            if (!etNewCity.getText().toString().equals(""))                     // Если поле ввода не пустое, то название города
                citiesActivityView.addNewCity(etNewCity.getText().toString());  // отправляется в пресентор для добавления в базу городов
            alertDialog.dismiss();
        }).setNegativeButton("Отмена", (dialog1, which) -> {
            if (alertDialog!=null)
            alertDialog.dismiss();
        });

        builder.setView(dialog);

        alertDialog = builder.create();
        alertDialog.show();

    }

    /** Метод вызова диалога для показа ошибок в ходе выполения работы приложения */
    public void showErrorDialog(Context context, String error){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle("Ошибка")
                .setMessage(error)
                .setNegativeButton("Закрыть", (dialog, which) -> {
                    alertDialog.dismiss();
                });
        alertDialog = builder.create();
        alertDialog.show();

    }

    /** Метод вызова диалога для показа подтверждения об удалении города из БД */
    public void answerDeleteCityDialog(Context context, CitysWeatherView citysWeatherView){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle("Удаление")
                .setMessage("Удалить данный город?")
                .setPositiveButton("Да", (dialog, which) -> {
                    citysWeatherView.deleteThisCity();                  // Если подтверждение есть, то ответ оправляется в презентор
                })                                                      // для удаления из БД городов
                .setNegativeButton("Нет", (dialog, which) -> {

                });
        alertDialog = builder.create();
        alertDialog.show();
    }
}
