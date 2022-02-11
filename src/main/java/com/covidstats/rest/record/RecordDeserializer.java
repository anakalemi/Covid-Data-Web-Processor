package com.covidstats.rest.record;

import com.covidstats.model.Country;
import com.covidstats.model.Record;
import com.covidstats.model.RecordID;
import com.covidstats.utils.GsonUtil;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;

public class RecordDeserializer implements JsonDeserializer<Record> {

    @Override
    public Record deserialize(JsonElement jsonElement,
                              Type type,
                              JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject json = (JsonObject) jsonElement;
        Record record = new Record();
        try {

            RecordID id = new RecordID();
            id.setIsoCode(json.get("isoCode").getAsString());
            id.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(json.get("date").getAsString()));

            record.setRecordID(id);
            record.setCountry(GsonUtil.getCountryDeserializerGson().fromJson(json.get("country"), Country.class));
            record.setTotalCases(json.get("totalCases").getAsDouble());
            record.setNewCases(json.get("newCases").getAsDouble());
            record.setNewCasesSmoothed(json.get("newCasesSmoothed").getAsDouble());
            record.setTotalDeaths(json.get("totalDeaths").getAsDouble());
            record.setNewDeaths(json.get("newDeaths").getAsDouble());
            record.setNewDeathsSmoothed(json.get("newDeathsSmoothed").getAsDouble());
            record.setReproductionRate(json.get("reproductionRate").getAsDouble());
            record.setNewTests(json.get("newTests").getAsDouble());
            record.setTotalTests(json.get("totalTests").getAsDouble());
            record.setStringencyIndex(json.get("stringencyIndex").getAsDouble());
            record.setPopulation(json.get("population").getAsDouble());
            record.setMedianAge(json.get("medianAge").getAsDouble());
            record.setNewDeathsPerCase(json.get("newDeathsPerCase").getAsDouble());

            return record;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

