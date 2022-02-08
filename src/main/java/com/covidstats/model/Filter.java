package com.covidstats.model;

import java.util.Date;

public class Filter {

    private String filterCode;
    private String filterCountry;
    private String filterContinent;
    private Date filterDate;

    public Filter() {
        filterCode = "";
        filterCountry = "";
        filterContinent = "";
        filterDate = null;
    }

    public String getFilterCode() {
        return filterCode;
    }

    public void setFilterCode(String filterCode) {
        this.filterCode = filterCode;
    }

    public String getFilterCountry() {
        return filterCountry;
    }

    public void setFilterCountry(String filterCountry) {
        this.filterCountry = filterCountry;
    }

    public String getFilterContinent() {
        return filterContinent;
    }

    public void setFilterContinent(String filterContinent) {
        this.filterContinent = filterContinent;
    }

    public Date getFilterDate() {
        return filterDate;
    }

    public void setFilterDate(Date filterDate) {
        this.filterDate = filterDate;
    }

    @Override
    public String toString() {
        return "Filter{" +
                "filterCode='" + filterCode + '\'' +
                ", filterCountry='" + filterCountry + '\'' +
                ", filterContinent='" + filterContinent + '\'' +
                ", filterDate=" + filterDate +
                '}';
    }
}
