package com.covidstats.beans;

import com.covidstats.controller.CountryController;
import com.covidstats.model.Country;
import com.covidstats.utils.GrowlMessage;
import com.covidstats.utils.Routes;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@SessionScoped
public class CountryBean implements Serializable {

    @ManagedProperty(value="#{userBean}")
    private UserBean userBean;

    private List<Country> allCountries;
    private Country country;

    public CountryBean() {
    }

    @PostConstruct
    public void init() {
        loadList();
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public List<Country> getAllCountries() {
        return allCountries;
    }

    public void setAllCountries(List<Country> allCountries) {
        this.allCountries = allCountries;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public void loadList() {
        allCountries = CountryController.getInstance().index();
    }

    public String prepareCreate() {
        if (!userBean.hasCurrentUser()) {
            new GrowlMessage().showWarn("Unauthorized user!");
            return Routes.LOGIN.getUrl();
        }
        this.country = new Country();
        return Routes.CREATE_COUNTRY.getUrl();
    }

    public String prepareEdit() {
        if (!userBean.hasCurrentUser()) {
            new GrowlMessage().showWarn("Unauthorized user!");
            return Routes.LOGIN.getUrl();
        }
        if (this.country == null) {
            new GrowlMessage().showInfo("Select a country first");
            return Routes.COUNTRIES_HOME.getUrl();
        }
        return Routes.UPDATE_COUNTRY.getUrl();
    }

    public String createCountry() {
        try {
            CountryController.getInstance().store(country);
            loadList();
            new GrowlMessage().showInfo("Country Created");
            return Routes.COUNTRIES_HOME.getUrl();
        } catch (Exception e) {
            new GrowlMessage().showInfo("Failed to Create!");
            return null;
        }
    }

    public String updateCountry() {
        try {
            CountryController.getInstance().update(country);
            new GrowlMessage().showInfo("Country Updated!");
            loadList();
            return Routes.COUNTRIES_HOME.getUrl();
        } catch (Exception e) {
            new GrowlMessage().showInfo("Failed to update!");
            return null;
        }
    }

    public String deleteCountry() {
        try {
            if (!userBean.hasCurrentUser()) {
                new GrowlMessage().showWarn("Unauthorized user!");
                return Routes.LOGIN.getUrl();
            }
            if (this.country == null) {
                new GrowlMessage().showInfo("Select a country first");
                return Routes.COUNTRIES_HOME.getUrl();
            }
            CountryController.getInstance().destroy(country);
            new GrowlMessage().showInfo("Record Deleted!");
            return actionReload();
        } catch (Exception e) {
            new GrowlMessage().showError("Failed to delete!");
            return Routes.COUNTRIES_HOME.getUrl();
        }
    }

    public String actionReload() {
        loadList();
        return Routes.COUNTRIES_HOME.getUrl();
    }

}
