package com.mpcs.Service;

import com.mpcs.Dto.EmployeeRequest;
import com.mpcs.Model.Employee;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface EmployeeService {
    public void employeeSalarySave(EmployeeRequest employeeRequest);
    public void monthlySalaryDataGenerator() throws IOException;
    public Iterable<Employee> findAllEmployee();

}
