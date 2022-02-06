package com.covidstats.data;

import java.io.IOException;

public class DBPopulator {

    public static void main(String[] args) {

        //TODO Run only once to populate the database with the records from the CSV file
        try {
            CSVDataReader reader = new CSVDataReader();
            reader.readAndProcessCSV();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
