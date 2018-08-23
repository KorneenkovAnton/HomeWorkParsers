package com.epam.entity;


public class Employee {

    private PersonalInfo personalInfo = new PersonalInfo();
    private String position;
    private int id;
    private int salary;
    private int experience;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    public void setPersonalInfo(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }



    public Employee() {
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id= "+ id + ", " +
                "personalInfo=" + personalInfo +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                ", experience=" + experience +
                '}';
    }
}
