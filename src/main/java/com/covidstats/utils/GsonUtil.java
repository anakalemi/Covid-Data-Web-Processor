package com.covidstats.utils;

import com.covidstats.model.Country;
import com.covidstats.model.Record;
import com.covidstats.rest.country.CountryDeserializer;
import com.covidstats.rest.country.CountrySerializer;
import com.covidstats.rest.record.RecordSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtil {

    public static Gson getCountrySerializerGson() {
        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Country.class, new CountrySerializer());
        gsonBuilder.setPrettyPrinting();
        return gsonBuilder.create();
    }

    public static Gson getCountryDeserializerGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Country.class, new CountryDeserializer());
        return gsonBuilder.create();
    }

    public static Gson getRecordSerializerGson() {
        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Record.class, new RecordSerializer());
        gsonBuilder.setPrettyPrinting();
        return gsonBuilder.create();
    }
}
