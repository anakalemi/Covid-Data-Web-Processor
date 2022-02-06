package com.covidstats.controller;

import com.covidstats.dao.DAOFactory;
import com.covidstats.model.Country;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CountryController implements IController<Country>{

    private CountryController() {
    }

    private static class LazyHolder {
        static final CountryController INSTANCE = new CountryController();
    }

    public static CountryController getInstance() {
        return CountryController.LazyHolder.INSTANCE;
    }

    @Override
    public ArrayList<Country> index() {
        return convertToArrayList(DAOFactory.getCountryDAO().index());
    }

    @Override
    public Country show(Object key) {
        if (!(key instanceof String)) {
            throw new IllegalArgumentException("Wrong key type");
        }
        return DAOFactory.getCountryDAO().show(key);
    }

    @Override
    public void store(Country country) {
        DAOFactory.getCountryDAO().store(country);
    }

    @Override
    public void update(Country country) {
        DAOFactory.getCountryDAO().update(country);
    }

    @Override
    public void destroy(Country country) {
        DAOFactory.getCountryDAO().destroy(country);
    }

    public <T> ArrayList<Country> convertToArrayList(List<T> countries) {
        return countries.stream()
                .filter(object -> object instanceof Country)
                .map(object -> (Country) object)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
