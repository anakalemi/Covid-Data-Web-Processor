package com.covidstats.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="records")
@NamedQueries({
        @NamedQuery(name = "Record.findAll", query = "SELECT r FROM Record r")
        , @NamedQuery(name = "Record.findByIsoCode", query = "SELECT r FROM Record r WHERE r.recordID.isoCode = :isoCode")
        , @NamedQuery(name = "Record.findByDate", query = "SELECT r FROM Record r WHERE r.recordID.date = :date")
        , @NamedQuery(name = "Record.findByTotalCases", query = "SELECT r FROM Record r WHERE r.totalCases = :totalCases")
        , @NamedQuery(name = "Record.findByNewCases", query = "SELECT r FROM Record r WHERE r.newCases = :newCases")
        , @NamedQuery(name = "Record.findByNewCasesSmoothed", query = "SELECT r FROM Record r WHERE r.newCasesSmoothed = :newCasesSmoothed")
        , @NamedQuery(name = "Record.findByTotalDeaths", query = "SELECT r FROM Record r WHERE r.totalDeaths = :totalDeaths")
        , @NamedQuery(name = "Record.findByNewDeaths", query = "SELECT r FROM Record r WHERE r.newDeaths = :newDeaths")
        , @NamedQuery(name = "Record.findByNewDeathsSmoothed", query = "SELECT r FROM Record r WHERE r.newDeathsSmoothed = :newDeathsSmoothed")
        , @NamedQuery(name = "Record.findByReproductionRate", query = "SELECT r FROM Record r WHERE r.reproductionRate = :reproductionRate")
        , @NamedQuery(name = "Record.findByNewTests", query = "SELECT r FROM Record r WHERE r.newTests = :newTests")
        , @NamedQuery(name = "Record.findByTotalTests", query = "SELECT r FROM Record r WHERE r.totalTests = :totalTests")
        , @NamedQuery(name = "Record.findByStringencyIndex", query = "SELECT r FROM Record r WHERE r.stringencyIndex = :stringencyIndex")
        , @NamedQuery(name = "Record.findByPopulation", query = "SELECT r FROM Record r WHERE r.population = :population")
        , @NamedQuery(name = "Record.findByMedianAge", query = "SELECT r FROM Record r WHERE r.medianAge = :medianAge")
        , @NamedQuery(name = "Record.findByNewDeathsPerCase", query = "SELECT r FROM Record r WHERE r.newDeathsPerCase = :newDeathsPerCase")
})
public class Record implements Serializable {

    @EmbeddedId
    private RecordID recordID;


    @ManyToOne()
    @JoinColumn(name = "iso_code", referencedColumnName = "iso_code", insertable = false, updatable = false)
    private Country country;

    @Column(name = "total_cases")
    private Double totalCases = null;
    @Column(name = "new_cases")
    private Double newCases;
    @Column(name = "new_cases_smoothed")
    private Double newCasesSmoothed;
    @Column(name = "total_deaths")
    private Double totalDeaths;
    @Column(name = "new_deaths")
    private Double newDeaths;
    @Column(name = "new_deaths_smoothed")
    private Double newDeathsSmoothed;
    @Column(name = "reproduction_rate")
    private Double reproductionRate;
    @Column(name = "new_tests")
    private Double newTests;
    @Column(name = "total_tests")
    private Double totalTests;
    @Column(name = "stringency_index")
    private Double stringencyIndex;
    @Column(name = "population")
    private Double population;
    @Column(name = "median_age")
    private Double medianAge;
    @Column(name = "new_deaths_per_case")
    private Double newDeathsPerCase;

    public Record() {

    }

    public Record(RecordID recordID, Country country) {
        this.recordID = recordID;
        this.country = country;
    }

    public RecordID getRecordID() {
        return recordID;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Double getTotalCases() {
        return totalCases;
    }

    public void setTotalCases(Double totalCases) {
        this.totalCases = totalCases;
    }

    public Double getNewCases() {
        return newCases;
    }

    public void setNewCases(Double newCases) {
        this.newCases = newCases;
    }

    public Double getNewCasesSmoothed() {
        return newCasesSmoothed;
    }

    public void setNewCasesSmoothed(Double newCasesSmoothed) {
        this.newCasesSmoothed = newCasesSmoothed;
    }

    public Double getTotalDeaths() {
        return totalDeaths;
    }

    public void setTotalDeaths(Double totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    public Double getNewDeaths() {
        return newDeaths;
    }

    public void setNewDeaths(Double newDeaths) {
        this.newDeaths = newDeaths;
    }

    public Double getNewDeathsSmoothed() {
        return newDeathsSmoothed;
    }

    public void setNewDeathsSmoothed(Double newDeathSmoothed) {
        this.newDeathsSmoothed = newDeathSmoothed;
    }

    public Double getReproductionRate() {
        return reproductionRate;
    }

    public void setReproductionRate(Double reproductionRate) {
        this.reproductionRate = reproductionRate;
    }

    public Double getNewTests() {
        return newTests;
    }

    public void setNewTests(Double newTests) {
        this.newTests = newTests;
    }

    public Double getTotalTests() {
        return totalTests;
    }

    public void setTotalTests(Double totalTests) {
        this.totalTests = totalTests;
    }

    public Double getStringencyIndex() {
        return stringencyIndex;
    }

    public void setStringencyIndex(Double stringencyIndex) {
        this.stringencyIndex = stringencyIndex;
    }

    public Double getPopulation() {
        return population;
    }

    public void setPopulation(Double population) {
        this.population = population;
    }

    public Double getMedianAge() {
        return medianAge;
    }

    public void setMedianAge(Double medianAge) {
        this.medianAge = medianAge;
    }

    public void setNewDeathsPerCase() {
        if(newCases != Double.MIN_VALUE && newDeaths != Double.MIN_VALUE && newCases != 0) {
            newDeathsPerCase = newDeaths / newCases;
        }
    }

    public Double getNewDeathsPerCase() {
        return newDeathsPerCase;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Record other)) {
            return false;
        }
        return (this.recordID != null || other.recordID == null)
                && (this.recordID == null || this.recordID.equals(other.recordID));
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (recordID != null ? recordID.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "model.Record[ recordID=" + recordID + " ]";
    }
}
