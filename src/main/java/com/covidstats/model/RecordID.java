package com.covidstats.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Embeddable
public class RecordID implements Serializable {

    @Column(name = "iso_code")
    private String isoCode;

    @Column(name = "reg_date")
    private Date date = null;

    public RecordID() {
    }

    public RecordID(String isoCode, Date date) {
        this.isoCode = isoCode;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public String getDateToString() {
        if (date == null) {
            return "";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }


    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RecordID other)) {
            return false;
        }
        if ((this.isoCode == null && other.isoCode != null)
                || (this.isoCode != null && !this.isoCode.equals(other.isoCode))) {
            return false;
        }
        return (this.date != null || other.date == null)
                && (this.date == null || this.date.equals(other.date));
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (isoCode != null ? isoCode.hashCode() : 0);
        hash += (date != null ? date.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "model.RecordID[ isoCode=" + isoCode + ", date=" + date + " ]";
    }
}
