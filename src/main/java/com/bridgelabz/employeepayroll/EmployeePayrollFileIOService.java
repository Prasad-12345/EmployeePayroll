package com.bridgelabz.employeepayroll;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
/*
 *Author: Prasad
 *Create an Employee Payroll Service to store Employee Payroll into a File
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
}
