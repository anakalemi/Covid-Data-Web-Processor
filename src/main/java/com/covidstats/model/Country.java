package com.covidstats.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "countries")
@NamedQueries({
        @NamedQuery(name = "Country.findAll", query = "SELECT c FROM Country c")
        ,@NamedQuery(name = "Country.findByCode", query = "SELECT c FROM Country c WHERE c.isoCode = :isoCode")
        ,@NamedQuery(name = "Country.findByContinent", query = "SELECT c FROM Country c WHERE c.continent = :continent")
        ,@NamedQuery(name = "Country.findByLocation", query = "SELECT c FROM Country c WHERE c.location = :location")
})
public class Country implements Serializable {

    @Id
    @Column(name = "iso_code")
    private String isoCode;
    @Column(name = "continent")
    private String continent;
    @Column(name = "location")
    private String location;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "country")
    List<Record> recordList;

    public Country() {
    }

    public Country(String isoCode, String continent, String location) {
        this.isoCode = isoCode;
        this.continent = continent;
        this.location = location;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Record> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<Record> recordList) {
        this.recordList = recordList;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Country other)) {
            return false;
        }
        return (this.isoCode != null || other.isoCode == null)
                && (this.isoCode == null || this.isoCode.equals(other.isoCode));
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (isoCode != null ? isoCode.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "model.Country[ isoCode=" + isoCode + " ]";
    }
}
