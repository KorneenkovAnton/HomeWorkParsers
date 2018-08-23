package com.epam.entity;


import java.util.Date;

public class PersonalInfo {

    private String name;
    private String sName;
    private Date dateOfBirth;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public PersonalInfo(String name, String sName, Date dateOfBirth) {
        this.name = name;
        this.sName = sName;
        this.dateOfBirth = dateOfBirth;
    }

    public PersonalInfo() {
    }

    @Override
    public String toString() {
        return "PersonalInfo{" +
                "name='" + name + '\'' +
                ", sName='" + sName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
