package com.covidstats.controller;

import com.covidstats.dao.DAOFactory;
import com.covidstats.model.Record;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RecordController implements IController<Record>{

    private RecordController() {
    }

    private static class LazyHolder {
        static final RecordController INSTANCE = new RecordController();
    }

    public static RecordController getInstance() {
        return LazyHolder.INSTANCE;
    }

    @Override
    public ArrayList<Record> index() {
        return convertToArrayList(DAOFactory.getRecordDAO().index());
    }

    @Override
    public Record show(Object key) {
        if (!(key instanceof String)) {
            throw new IllegalArgumentException("Wrong key type");
        }
        return DAOFactory.getRecordDAO().show(key);
    }

    @Override
    public void store(Record record) {
        DAOFactory.getRecordDAO().store(record);
    }

    @Override
    public void update(Record record) {
        DAOFactory.getRecordDAO().update(record);
    }

    @Override
    public void destroy(Record record) {
        DAOFactory.getRecordDAO().destroy(record);
    }

    public <T> ArrayList<Record> convertToArrayList(List<T> records) {
        return records.stream()
                .filter(object -> object instanceof Record)
                .map(object -> (Record) object)
                .collect(Collectors.toCollection(ArrayList::new));
    }

}
