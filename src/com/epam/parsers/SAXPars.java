package com.epam.parsers;


import com.epam.entity.Employee;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class SAXPars extends DefaultHandler implements Tags {

    private List<Employee> employees;
    private Employee currentEmployee;
    private String currentElement;
    private SimpleDateFormat format = new SimpleDateFormat();


    public List<Employee> getEmployees() {
        return employees;
    }

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Start parsing...");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentElement = qName;

        switch (currentElement){
            case EMPLOYEE_LIST_TAG:{
                if(employees == null){
                    employees = new ArrayList<>();
                }
                break;
            }
            case EMPLOYEE_TAG:{
                currentEmployee = new Employee();
                currentEmployee.setId(Integer.parseInt(attributes.getValue(ID_TAG)));
                break;
            }

        }
    }


    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String text = new String (ch, start, length);
        if(text.contains("<") || currentElement == null){
            return;
        }
        switch (currentElement){
            case NAME_TAG:{
                currentEmployee.getPersonalInfo().setName(text);
                break;
            }
            case SNAME_TAG:{
                currentEmployee.getPersonalInfo().setsName(text);
                break;
            }
            case DATEOFB_TAG:{
                format.applyPattern("yyyy-MM-dd");
                try {
                    currentEmployee.getPersonalInfo().setDateOfBirth(format.parse(text));
                } catch (ParseException e) {
                    System.out.println("Wrong date format");
                    e.printStackTrace();
                }
                break;
            }
            case POSITION_TAG:{
                currentEmployee.setPosition(text);
                break;
            }
            case SALARY_TAG:{
                currentEmployee.setSalary(Integer.parseInt(text));
                break;
            }
            case EXPERIENCE_TAG:{
                currentEmployee.setExperience(Integer.parseInt(text));
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        currentElement = null;
        if(qName.equals("employee")){
            employees.add(currentEmployee);
            currentEmployee = null;
        }
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("completed");
    }
}
