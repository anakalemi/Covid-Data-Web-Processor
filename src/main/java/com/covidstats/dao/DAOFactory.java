package com.covidstats.dao;

public class DAOFactory {
    private static final UserDAO USER_DAO;
    private static final RecordDAO RECORD_DAO;
    private static final CountryDAO COUNTRY_DAO;

    static {
        USER_DAO = new UserDAO();
        RECORD_DAO = new RecordDAO();
        COUNTRY_DAO = new CountryDAO();
    }

    public static UserIDAO getUserDAO() {
        return USER_DAO;
    }

    public static RecordDAO getRecordDAO() {
        return RECORD_DAO;
    }

    public static CountryDAO getCountryDAO() {
        return COUNTRY_DAO;
    }
}
