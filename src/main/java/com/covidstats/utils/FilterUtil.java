package com.covidstats.utils;

import com.covidstats.model.Filter;
import com.covidstats.model.Record;

import javax.persistence.Query;
import java.util.Date;
import java.util.List;

public class FilterUtil {

    private final Filter filter;

    public FilterUtil(Filter filter) {
        this.filter = filter;
    }

    private boolean isParameterPresent(String paramValue) {
        return paramValue != null && !paramValue.trim().isEmpty();
    }

    private boolean isParameterPresent(Date paramValue) {
        return paramValue != null;
    }

    private Query constructFilterQuery() {
        String ql = "SELECT r FROM Record r JOIN Country c on (r.recordID.isoCode = c.isoCode) WHERE 1 = 1 ";
        if (isParameterPresent(filter.getFilterCode()))
            ql += "AND LOWER(c.isoCode) LIKE LOWER(:isoCode)";
        if (isParameterPresent(filter.getFilterContinent()))
            ql += "AND LOWER(c.continent) LIKE LOWER(:continent)";
        if (isParameterPresent(filter.getFilterCountry()))
            ql += "AND LOWER(c.location) LIKE LOWER(:location)";
        if (isParameterPresent(filter.getFilterDate()))
            ql += "AND r.recordID.date = :date";

        Query query = EntityManagerProvider.getEntityManager().createQuery(ql);

        if (isParameterPresent(filter.getFilterCode()))
            query.setParameter("isoCode", filter.getFilterCode() + "%");
        if (isParameterPresent(filter.getFilterContinent()))
            query.setParameter("continent", filter.getFilterContinent() + "%");
        if (isParameterPresent(filter.getFilterCountry()))
            query.setParameter("location", filter.getFilterCountry() + "%");
        if (isParameterPresent(filter.getFilterDate()))
            query.setParameter("date", filter.getFilterDate());

        return query;
    }

    public List<Record> filter() {
        Query query = constructFilterQuery();
        return (List<Record>) query.getResultList();
    }

}
