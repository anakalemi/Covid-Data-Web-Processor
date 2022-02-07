package com.covidstats.utils;

public enum Routes {
    HOME("home.xhtml"),
    COUNTRIES_HOME("countries-home.xhtml"),
    PROFILE("profile.xhtml"),
    LOGIN("login.xhtml"),
    CREATE_RECORD("create-record.xhtml"),
    UPDATE_RECORD("update-record.xhtml"),
    CREATE_COUNTRY("create-country.xhtml"),
    UPDATE_COUNTRY("update-country.xhtml");


    private final String url;

    Routes(final String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}

