package com.covidstats.rest.country;

import com.covidstats.model.Country;
import com.google.gson.*;

import java.lang.reflect.Type;

public class CountryDeserializer implements JsonDeserializer<Country> {

    @Override
    public Country deserialize(JsonElement jsonElement,
                              Type type,
                              JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject json = (JsonObject) jsonElement;
        Country country = new Country();
        try {
            country.setIsoCode(json.get("isoCode").getAsString());
            country.setContinent(json.get("continent").getAsString());
            country.setLocation(json.get("location").getAsString());

            return country;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

