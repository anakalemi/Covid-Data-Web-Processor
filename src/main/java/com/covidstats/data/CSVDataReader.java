package com.covidstats.data;

import com.covidstats.model.Record;
import com.covidstats.model.RecordID;
import com.covidstats.model.Country;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CSVDataReader {

    private final String filePath;
    private Map<Integer, String> indexHeaderMap;
    private final List<Record> allRecords = new ArrayList<>();
    private final List<Country> allCountries = new ArrayList<>();

    enum Type {
        NUMERIC,
        TEXT,
        DATE
    }

    public CSVDataReader() throws IOException {

        try(InputStream is = new FileInputStream("src/main/resources/application.properties")) {
            Properties properties = new Properties();
            properties.load(is);
            filePath = properties.getProperty("csvFilePath");
        }
    }

    public void readAndProcessCSV() {
        List<List<String>> dataFromCSV = readDataFromCSV();
        processAllData(dataFromCSV);
    }

    private List<List<String>> readDataFromCSV() {
        List<List<String>> dataList = null;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {

            dataList = bufferedReader.lines()
                    .map(k -> Arrays.asList(k.replaceAll(",", ", ").split(",")))
                    .collect(Collectors.toCollection(ArrayList::new));

            indexHeaderMap = processAndReturnHeaderIndexes(dataList.get(0));
            dataList.remove(0);
            return dataList;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataList;
    }

    private void processAllData(List<List<String>> dataList) {

        dataList.forEach(row -> {
            try {
                Country country = new Country();
                country.setIsoCode((String) getData(row, "iso_code", Type.TEXT));
                country.setContinent((String) getData(row, "continent", Type.TEXT));
                country.setLocation((String) getData(row, "location", Type.TEXT));
                allCountries.add(country);

                RecordID recordID = new RecordID();
                recordID.setIsoCode(country.getIsoCode());
                recordID.setDate((Date) getData(row, "date", Type.DATE));

                Record record = new Record(recordID, country);
                record.setTotalCases((Double) getData(row, "total_cases", Type.NUMERIC));
                record.setNewCases((Double) getData(row, "new_cases", Type.NUMERIC));
                record.setNewCasesSmoothed((Double) getData(row, "new_cases_smoothed", Type.NUMERIC));
                record.setTotalDeaths((Double) getData(row, "total_deaths", Type.NUMERIC));
                record.setNewDeaths((Double) getData(row, "new_deaths", Type.NUMERIC));
                record.setNewDeathsSmoothed((Double) getData(row, "new_deaths_smoothed", Type.NUMERIC));
                record.setReproductionRate((Double) getData(row, "reproduction_rate", Type.NUMERIC));
                record.setNewTests((Double) getData(row, "new_tests", Type.NUMERIC));
                record.setTotalTests((Double) getData(row, "total_tests", Type.NUMERIC));
                record.setStringencyIndex((Double) getData(row, "stringency_index", Type.NUMERIC));
                record.setPopulation((Double) getData(row, "population", Type.NUMERIC));
                record.setMedianAge((Double) getData(row, "median_age", Type.NUMERIC));
                record.setNewDeathsPerCase();
                allRecords.add(record);

            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
    }

    private Object getData(List<String> row, String columnHeader, Type type) throws ParseException {
        int index = getIndexFromColumnHeader(columnHeader);
        Object data = null;

        switch (type) {
            case NUMERIC -> {
                if (!row.get(index).trim().isEmpty()) {
                    data = Double.parseDouble(row.get(index).trim());
                }
            }
            case DATE -> {
                if (!row.get(index).trim().isEmpty()) {
                    data = new SimpleDateFormat("yyyy-MM-dd").parse(row.get(index).trim());
                }
            }
            case TEXT -> {
                if (!row.get(index).trim().isEmpty()) {
                    data = row.get(index).trim();
                }
            }
        }
        return data;
    }

    private int getIndexFromColumnHeader(String columnHeader) {
        return indexHeaderMap.entrySet().stream()
                .filter(i -> i.getValue().equalsIgnoreCase(columnHeader))
                .mapToInt(Map.Entry::getKey)
                .findFirst()
                .orElseThrow();
    }

    private Map<Integer, String> processAndReturnHeaderIndexes(List<String> headers) {
        return IntStream.range(0, headers.size()).boxed()
                .collect(Collectors.toMap(Function.identity(), headers.stream().map(String::trim).toList()::get));
    }

    public List<Record> getAllRecords() {
        return allRecords;
    }

    public List<Country> getAllCountries() {
        return allCountries;
    }
}