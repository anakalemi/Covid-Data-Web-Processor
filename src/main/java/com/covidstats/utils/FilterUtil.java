package com.covidstats.utils;

import com.covidstats.model.Record;

import java.util.List;
import java.util.stream.Collectors;

public class FilterUtil {
    private final String type;
    private final String value;
    private List<Record> allRecordsList;

    public FilterUtil(List<Record> allRecordsList, String type, String value) {
        this.type = type;
        this.value = value;
        this.allRecordsList = allRecordsList;
    }

    public List<Record> filter() {
        if ("continent".equals(type)) {
            allRecordsList = filterByContinent();
        } else if ("country".equals(type)) {
            allRecordsList = filterByCountry();
        } else if ("code".equals(type)) {
            allRecordsList = filterByIsoCode();
        } else if ("date".equals(type)) {
            allRecordsList = filterByDate();
        }

        return allRecordsList;
    }

    public List<Record> filterByContinent() {
        if(value.trim().isEmpty()){
            return allRecordsList;
        }
        return allRecordsList.stream()
                .filter(r -> r.getCountry().getContinent() != null)
                .filter(r -> r.getCountry().getContinent().toUpperCase().contains(value.toUpperCase()))
                .collect(Collectors.toList());
    }

    public List<Record> filterByCountry() {
        if(value.trim().isEmpty()){
            return allRecordsList;
        }
        return allRecordsList.stream()
                .filter(r -> r.getCountry().getLocation() != null)
                .filter(r -> r.getCountry().getLocation().toUpperCase().contains(value.toUpperCase()))
                .collect(Collectors.toList());
    }

    public List<Record> filterByIsoCode() {
        if(value.trim().isEmpty()){
            return allRecordsList;
        }
        return allRecordsList.stream()
                .filter(r -> r.getCountry().getIsoCode() != null)
                .filter(r -> r.getCountry().getIsoCode().toUpperCase().contains(value.toUpperCase()))
                .collect(Collectors.toList());
    }

    public List<Record> filterByDate() {
        if(value.trim().isEmpty()){
            return allRecordsList;
        }
        return allRecordsList.stream()
                .filter(r -> r.getRecordID().getDateToString() != null)
                .filter(r -> r.getRecordID().getDateToString().toUpperCase().contains(value.toUpperCase()))
                .collect(Collectors.toList());
    }


}
