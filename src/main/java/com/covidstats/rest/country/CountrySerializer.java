package com.covidstats.rest.country;

import com.covidstats.model.Country;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class CountrySerializer implements JsonSerializer<Country> {
    @Override
    public JsonElement serialize(Country country,
                                 Type type,
                                 JsonSerializationContext context) {

        JsonObject countryItem = new JsonObject();
        try {
            countryItem.addProperty("isoCode", country.getIsoCode());
            countryItem.addProperty("continent", country.getContinent());
            countryItem.addProperty("location", country.getLocation());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return countryItem;
    }
}
