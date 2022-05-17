package com.bridgelabz.employeepayroll;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
/*
 *Author: Prasad
 *Ability for Employee Payroll Service to read the Employee Payroll File so that some analysis can be performed
 */
public class EmployeePayrollFileIOService {
    public static String fileName = "payroll_service.txt";

    /*
     *This method will write data into file
     */
    public void writeData(List<EmployeePayrollData> employeePayrollList) {
        StringBuffer empBuffer = new StringBuffer();

        employeePayrollList.forEach(employee -> {String empData = employee.toString().concat("\n");
                        empBuffer.append(empData);
        });

        try {
            Files.write(Paths.get(fileName), empBuffer.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     *This method will return number of entries in file
     */
    public long entries() throws IOException {
        return Files.lines(Paths.get(fileName)).count();
    }

    /*
     *Method to print details of employee
     */
    public void printData() throws IOException {
        Files.lines(Paths.get(fileName)).forEach(System.out::println);
    }

    /*
     *Method to read data from file
     */
    public List<String> readDataFromFile(){
        List<String> employeePayrollList = new ArrayList<>();

        try{
            Files.lines(Paths.get(fileName)).map(employee -> employee.trim()).forEach(
                    employee -> { System.out.println(employee);
                    employeePayrollList.add(employee);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employeePayrollList;
    }
}
