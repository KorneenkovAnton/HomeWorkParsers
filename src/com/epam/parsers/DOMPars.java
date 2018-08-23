package com.epam.parsers;


import com.epam.entity.Employee;
import com.epam.entity.PersonalInfo;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DOMPars implements Tags{

    private SimpleDateFormat format = new SimpleDateFormat();

    public List<Employee> getEmployeeList(String filePath) throws ParserConfigurationException, IOException, SAXException {
        System.out.println("Strted...");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(filePath));

        List<Employee> employees = new ArrayList<>(getEmployeeInfo(document));
        List<PersonalInfo> personalInfos = new ArrayList<>(getPersonalInfo(document));

        for (int i = 0; i< employees.size();i++){
            employees.get(i).setPersonalInfo(personalInfos.get(i));
        }
        System.out.println("completed");
        return employees;
    }

    private List<PersonalInfo> getPersonalInfo(Document document){
        List<PersonalInfo> personalInfoList = new ArrayList<>();

        NodeList personalInfoNodeList = document.getElementsByTagName(PERSONAL_INFO_TAG);

        for (int i = 0; i< personalInfoNodeList.getLength(); i++){
            if(personalInfoNodeList.item(i).getNodeType() == Node.ELEMENT_NODE){
                Element personalInfoElement = (Element) personalInfoNodeList.item(i);

                PersonalInfo currentPersonalInfo = new PersonalInfo();

                NodeList childNodes = personalInfoElement.getChildNodes();
                for(int j = 0; j< childNodes.getLength();j++){
                    if(childNodes.item(j).getNodeType() == Node.ELEMENT_NODE){
                        Element childElement = (Element) childNodes.item(j);
                        switch (childElement.getNodeName()){
                            case NAME_TAG:{
                                currentPersonalInfo.setName(childElement.getTextContent());
                                break;
                            }
                            case SNAME_TAG:{
                                currentPersonalInfo.setsName(childElement.getTextContent());
                                break;
                            }
                            case DATEOFB_TAG:{
                                format.applyPattern("dd-MM-yyyy");
                                try {
                                    currentPersonalInfo.setDateOfBirth(format.parse(childElement.getTextContent()));
                                } catch (ParseException e) {
                                    System.out.println("Wrong date format");
                                    e.printStackTrace();
                                }
                                break;
                            }
                        }
                    }
                }
                personalInfoList.add(currentPersonalInfo);
            }
        }

        return personalInfoList;
    }


    private List<Employee> getEmployeeInfo(Document document) throws ParserConfigurationException, IOException, SAXException {

        NodeList employeesNodeList = document.getElementsByTagName(EMPLOYEE_TAG);


        List<Employee> employees = new ArrayList<>();

        for(int i = 0; i<employeesNodeList.getLength();i++){
            if(employeesNodeList.item(i).getNodeType() == Node.ELEMENT_NODE){
                Element employeeElement = (Element)employeesNodeList.item(i);

                Employee currentEmplotyee = new Employee();
                currentEmplotyee.setId(Integer.parseInt(employeeElement.getAttribute(ID_TAG)));

                NodeList childNodes = employeeElement.getChildNodes();

                for(int j = 0; j<childNodes.getLength(); j++){
                    if(childNodes.item(j).getNodeType()== Node.ELEMENT_NODE){

                        Element childElement = (Element) childNodes.item(j);

                        switch (childElement.getNodeName()){
                            case POSITION_TAG:{
                                currentEmplotyee.setPosition(childElement.getTextContent());
                                break;
                            }
                            case SALARY_TAG:{
                                currentEmplotyee.setSalary(Integer.parseInt(childElement.getTextContent()));
                                break;
                            }
                            case EXPERIENCE_TAG:{
                                currentEmplotyee.setExperience(Integer.parseInt(childElement.getTextContent()));
                                break;
                            }
                        }
                    }
                }
                employees.add(currentEmplotyee);
            }
        }

        return employees;
    }


}
