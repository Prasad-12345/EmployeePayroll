package com.bridgelabz.employeepayroll;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/*
 *Author: Prasad
 *Ability for Employee Payroll Service to read the Employee Payroll File so that some analysis can be performed
 */
public class EmployeePayrollService {
    public enum IOService {
        CONSOLE_IO, FILE_IO, DB_IO, REST_IO
    }
    //creating list
    private List<EmployeePayrollData> employeePayrollList;

    //Constructor
    public EmployeePayrollService() {}

    //parametrized constructor
    public EmployeePayrollService(List<EmployeePayrollData> employeePayrollList) {
        this.employeePayrollList = employeePayrollList;
    }

    public static void main(String[] args) throws SQLException {
        ArrayList<EmployeePayrollData> employeePayrollList = new ArrayList<>();
        EmployeePayrollService employeePayrollService = new EmployeePayrollService(employeePayrollList);
        Scanner consoleInputReader = new Scanner(System.in);
        employeePayrollService.readEmpPayrollData(consoleInputReader);
        employeePayrollService.writeEmpPayrollData(IOService.CONSOLE_IO);
    }

    /*
     * This method read data from user and add it to the list
     */
    public void readEmpPayrollData(Scanner consoleInputReader) {
        System.out.println("Enter employee id : ");
        String empId = consoleInputReader.next();
        System.out.println("Enter employee name :");
        String empName = consoleInputReader.next();
        System.out.println("Enter employee salary : ");
        double empSalary = consoleInputReader.nextDouble();

        employeePayrollList.add(new EmployeePayrollData(empId, empName, empSalary));
    }

    /*
     * This method print data to console
     */
    public void writeEmpPayrollData(IOService ioService) {
        if(ioService.equals(IOService.CONSOLE_IO)) {
            System.out.println("Writing employee payroll on console : \n" + employeePayrollList);
        }
        else if (ioService.equals(IOService.FILE_IO)){
            new EmployeePayrollFileIOService().writeData(employeePayrollList);
        }
    }

    public void printData(IOService fileIo) throws IOException {
        if(fileIo.equals(IOService.FILE_IO)) new EmployeePayrollFileIOService().printData();
    }

    /*
     *Return number of entries
     */
    public long countEntries(IOService fileIo) throws IOException {
        if(fileIo.equals(IOService.FILE_IO)) return new EmployeePayrollFileIOService().entries();
        return 0;
    }

    public long readDataFromFile(IOService fileIo){
        List<String> employeePayrollFromFile = new ArrayList<>();
        if(fileIo.equals(IOService.FILE_IO)) {
            employeePayrollFromFile = new EmployeePayrollFileIOService().readDataFromFile();
        }
        return employeePayrollFromFile.size();
    }
}
