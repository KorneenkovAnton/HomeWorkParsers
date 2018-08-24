package com.epam.parsers;

import com.epam.entity.Employee;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class StAXPars implements Tags {

    private SimpleDateFormat format = new SimpleDateFormat();

    public List<Employee> getEmployeeList(String path) throws XMLStreamException, IOException {
        List<Employee> employeeList = null;
        String content = null;
        Employee currentEmpl = null;
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = factory.createXMLStreamReader(Files.newInputStream(Paths.get(path)));

        while (reader.hasNext()){
            int event = reader.next();

            switch (event){
                case XMLStreamConstants.START_ELEMENT:{
                    if(EMPLOYEE_TAG.equals(reader.getLocalName())){
                        currentEmpl = new Employee();
                        currentEmpl.setId(Integer.parseInt(reader.getAttributeValue(0)));
                    }
                    if(EMPLOYEE_LIST_TAG.equals(reader.getLocalName())){
                        employeeList = new ArrayList<>();
                    }
                    break;
                }

                case XMLStreamConstants.CHARACTERS:{
                    content = reader.getText().trim();
                    break;
                }


                case XMLStreamConstants.END_ELEMENT: {
                    switch (reader.getLocalName()) {
                        case NAME_TAG: {
                            currentEmpl.getPersonalInfo().setName(content);
                            break;
                        }
                        case SNAME_TAG: {
                            currentEmpl.getPersonalInfo().setsName(content);
                            break;
                        }
                        case DATEOFB_TAG: {
                            format.applyPattern("yyyy-MM-dd");
                            try {
                                currentEmpl.getPersonalInfo().setDateOfBirth(format.parse(content));
                            } catch (ParseException e) {
                                System.out.println("Wrong date");
                                e.printStackTrace();
                            }
                            break;
                        }
                        case POSITION_TAG: {
                            currentEmpl.setPosition(content);
                            break;
                        }
                        case SALARY_TAG: {
                            currentEmpl.setSalary(Integer.parseInt(content));
                            break;
                        }
                        case EXPERIENCE_TAG: {
                            currentEmpl.setExperience(Integer.parseInt(content));
                            break;
                        }
                        case EMPLOYEE_TAG:{
                            employeeList.add(currentEmpl);
                            currentEmpl = null;
                            break;
                        }
                    }
                    break;

                }

            }
        }

        return employeeList;
    }
}
