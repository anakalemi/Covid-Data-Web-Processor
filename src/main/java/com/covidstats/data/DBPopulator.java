package com.covidstats.data;

import com.covidstats.controller.CountryController;
import com.covidstats.controller.RecordController;
import com.covidstats.model.Country;
import com.covidstats.model.Record;

import java.io.IOException;
import java.util.List;

public class DBPopulator {

    private DBPopulator() {
    }

    private static class LazyHolder {
        static final DBPopulator INSTANCE = new DBPopulator();
    }

    public static DBPopulator getInstance() {
        return DBPopulator.LazyHolder.INSTANCE;
    }

    public static void main(String[] args) {

        //TODO Run only once to populate the database with the records from the CSV file
        try {
            CSVDataReader reader = new CSVDataReader();
            reader.readAndProcessCSV();

            DBPopulator.getInstance().populateCountries(reader.getAllCountries());
            DBPopulator.getInstance().populateRecords(reader.getAllRecords());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void populateCountries (List<Country> allCountries) {
        allCountries.stream().distinct()
                .forEach(country -> CountryController.getInstance().store(country));

    }

    private void populateRecords (List<Record> allRecords) {
        allRecords.forEach( record -> RecordController.getInstance().store(record));
    }

}
