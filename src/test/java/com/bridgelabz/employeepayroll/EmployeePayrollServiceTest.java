package com.bridgelabz.employeepayroll;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;

public class EmployeePayrollServiceTest {
    /*
     *Test case to ensure writeData method
     */
    @Test
    public void given3Employees_WhenWrittenToFile_ShouldMatchEmployeeEntries() throws IOException {
        EmployeePayrollData[] arraysOfEmp = {
                new EmployeePayrollData("1", "Prasad", 600000 ),
                new EmployeePayrollData("2", "Vipul", 700000 ),
                new EmployeePayrollData("3", "Swapnil", 800000)
        };

        EmployeePayrollService employeePayrollService = new EmployeePayrollService(Arrays.asList(arraysOfEmp));
        employeePayrollService.writeEmpPayrollData(EmployeePayrollService.IOService.FILE_IO);
        employeePayrollService.printData(EmployeePayrollService.IOService.FILE_IO);

        EmployeePayrollFileIOService obj = new EmployeePayrollFileIOService();
        long entries = obj.entries();
        Assert.assertEquals(entries, 3);
    }

    @Test
    public void givenFile_WhenRead_ShouldReturnNumberOfEntries() {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        long entries = employeePayrollService.readDataFromFile(EmployeePayrollService.IOService.FILE_IO);
        Assert.assertEquals(3, entries);
    }
}
