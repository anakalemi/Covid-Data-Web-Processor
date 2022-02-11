package com.covidstats.rest.record;

import com.covidstats.controller.CountryController;
import com.covidstats.model.Country;
import com.covidstats.model.Record;
import com.covidstats.utils.GsonUtil;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class RecordSerializer implements JsonSerializer<Record> {
    @Override
    public JsonElement serialize(Record record,
                                 Type type,
                                 JsonSerializationContext context) {

        JsonObject recordItem = new JsonObject();
        try {
            recordItem.addProperty("isoCode", record.getRecordID().getIsoCode());
            recordItem.addProperty("date", record.getRecordID().getDateToString());

            recordItem.add("country", GsonUtil.getCountrySerializerGson().toJsonTree(getCountry(record)));
            recordItem.addProperty("totalCases", record.getTotalCases());
            recordItem.addProperty("newCases", record.getNewCases());
            recordItem.addProperty("newCasesSmoothed", record.getNewCasesSmoothed());
            recordItem.addProperty("totalDeaths", record.getTotalDeaths());
            recordItem.addProperty("newDeaths", record.getNewDeaths());
            recordItem.addProperty("newDeathsSmoothed", record.getNewDeathsSmoothed());
            recordItem.addProperty("reproductionRate", record.getReproductionRate());
            recordItem.addProperty("newTests", record.getNewTests());
            recordItem.addProperty("totalTests", record.getTotalTests());
            recordItem.addProperty("stringencyIndex", record.getStringencyIndex());
            recordItem.addProperty("population", record.getPopulation());
            recordItem.addProperty("medianAge", record.getMedianAge());
            recordItem.addProperty("newDeathsPerCase", record.getNewDeathsPerCase());


        } catch (Exception e) {
            e.printStackTrace();
        }
        return recordItem;
    }

    private Country getCountry(Record record) {
        Country country = CountryController.getInstance().show(record.getRecordID().getIsoCode());
        return country == null ? new Country() : country;
    }
}