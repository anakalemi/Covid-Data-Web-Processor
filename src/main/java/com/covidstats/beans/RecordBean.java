package com.covidstats.beans;

import com.covidstats.controller.CountryController;
import com.covidstats.controller.RecordController;
import com.covidstats.model.Country;
import com.covidstats.model.Filter;
import com.covidstats.model.Record;
import com.covidstats.utils.FilterUtil;
import com.covidstats.utils.GrowlMessage;
import com.covidstats.utils.Routes;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@SessionScoped
public class RecordBean implements Serializable {

    @ManagedProperty(value = "#{userBean}")
    private UserBean userBean;

    private List<Record> allRecords;
    private Record record = new Record();

    @ManagedProperty(value = "#{filterBean}")
    private FilterBean filterBean;

    public RecordBean() {
    }

    @PostConstruct
    public void init() {
        loadList();
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public List<Record> getAllRecords() {
        return allRecords;
    }

    public void setAllRecords(List<Record> allRecords) {
        this.allRecords = allRecords;
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }

    public boolean hasRecords() {
        return !allRecords.isEmpty();
    }

    public void loadList() {
        allRecords = RecordController.getInstance().index();
    }

    public FilterBean getFilterBean() {
        return filterBean;
    }

    public void setFilterBean(FilterBean filterBean) {
        this.filterBean = filterBean;
    }

    public String filter() {
        allRecords = new FilterUtil(filterBean.filter).filter();
        return Routes.HOME.getUrl();
    }

    public String clearFilter() {
        loadList();
        filterBean.setFilter(new Filter());
        return Routes.HOME.getUrl();
    }

    public String prepareCreate() {
        if (!userBean.hasCurrentUser()) {
            new GrowlMessage().showWarn("Unauthorized user!");
            return Routes.LOGIN.getUrl();
        }
        record = new Record();
        return Routes.CREATE_RECORD.getUrl();
    }

    public String prepareEdit() {
        if (!userBean.hasCurrentUser()) {
            new GrowlMessage().showWarn("Unauthorized user!");
            return Routes.LOGIN.getUrl();
        }
        if (this.record == null) {
            new GrowlMessage().showInfo("Select a record first");
            return Routes.HOME.getUrl();
        }
        return Routes.UPDATE_RECORD.getUrl();
    }

    public String createRecord() {
        try {
            Country country = CountryController.getInstance().show(record.getRecordID().getIsoCode());
            if (country == null) {
                country = new Country(record.getRecordID().getIsoCode(), null, null);
                CountryController.getInstance().store(country);
            }
            record.setCountry(country);
            RecordController.getInstance().store(record);
            loadList();
            new GrowlMessage().showInfo("Record Created");
            return Routes.HOME.getUrl();
        } catch (Exception e) {
            new GrowlMessage().showInfo("Failed to Create!");
            return null;
        }
    }

    public String updateRecord() {
        try {
            RecordController.getInstance().update(record);
            new GrowlMessage().showInfo("Record Updated!");
            loadList();
            return Routes.HOME.getUrl();
        } catch (Exception e) {
            new GrowlMessage().showInfo("Failed to update!");
            return null;
        }
    }

    public String deleteRecord() {
        try {
            if (!userBean.hasCurrentUser()) {
                new GrowlMessage().showWarn("Unauthorized user!");
                return Routes.LOGIN.getUrl();
            }
            if (this.record == null) {
                new GrowlMessage().showInfo("Select a record first");
                return Routes.HOME.getUrl();
            }
            RecordController.getInstance().destroy(record);
            new GrowlMessage().showInfo("Record Deleted!");
            return actionReload();
        } catch (Exception e) {
            new GrowlMessage().showError("Failed to delete!");
            return Routes.HOME.getUrl();
        }
    }

    public String actionReload() {
        loadList();
        return Routes.HOME.getUrl();
    }

}
