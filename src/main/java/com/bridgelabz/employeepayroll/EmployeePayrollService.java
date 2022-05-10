package com.bridgelabz.employeepayroll;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/*
 *Author: Prasad
 * Create an Employee Payroll Service to Read and Write Employee Payroll to a Console
 */
public class EmployeePayrollService {
    //creating list
    private List<EmployeePayrollData> employeePayrollList;

    //Constructor
    public EmployeePayrollService() {}

    //parametrized constructor
    public EmployeePayrollService(List<EmployeePayrollData> employeePayrollList) {
        this.employeePayrollList = employeePayrollList;
    }

    public static void main(String[] args) {
        ArrayList<EmployeePayrollData> employeePayrollList = new ArrayList<>();
        EmployeePayrollService employeePayrollService = new EmployeePayrollService(employeePayrollList);
        Scanner scanner = new Scanner(System.in);
        employeePayrollService.readEmpPayrollData(scanner);
        employeePayrollService.writeEmpPayrollData();
    }

    /*
     * This method read data from user and add it to the list
     */
    private void readEmpPayrollData(Scanner scanner) {
        System.out.println("Enter employee id : ");
        String empId = scanner.next();
        System.out.println("Enter employee name :");
        String empName = scanner.next();
        System.out.println("Enter employee salary : ");
        double empSalary = scanner.nextDouble();

        employeePayrollList.add(new EmployeePayrollData(empId, empName, empSalary));
    }

    /*
     * This method print data to console
     */
    private void writeEmpPayrollData() {
        System.out.println("Writing employee payroll on console : \n" + employeePayrollList);
    }
}
