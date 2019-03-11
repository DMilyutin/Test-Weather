package com.milyutin.dima.testtaskweather.model.repository;
/** Интерфейс для обработки ошибки при запросе на сервера  */
public interface FailCallBack {

    void throwableError(Throwable throwable);
}
