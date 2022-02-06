package com.covidstats.controller;

import java.util.ArrayList;

public interface IController<T> {

    ArrayList<T> index();

    T show(Object key);

    void store(T entity);

    void update(T entity);

    void destroy(T entity);

}
