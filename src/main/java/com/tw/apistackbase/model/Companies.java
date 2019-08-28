package com.tw.apistackbase.model;

public class Companies {
    private int companiesID;
    private String companiesName;

    public Companies() {
    }

    public Companies(int companiesID, String companiesName) {
        this.companiesID = companiesID;
        this.companiesName = companiesName;
    }

    public int getCompaniesID() {
        return companiesID;
    }

    public void setCompaniesID(int companiesID) {
        this.companiesID = companiesID;
    }

    public String getCompaniesName() {
        return companiesName;
    }

    public void setCompaniesName(String companiesName) {
        this.companiesName = companiesName;
    }
}
