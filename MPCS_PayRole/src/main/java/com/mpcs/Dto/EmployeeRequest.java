package com.mpcs.Dto;

import com.mpcs.Model.Deductions;
import com.mpcs.Model.SalaryAllowances;
import lombok.Data;

import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;

@Data
public class EmployeeRequest {
    @OneToMany(mappedBy="employee")
    private List<SalaryAllowances> salaryAllowances;
    private String emp_id;
    private String firstName;
    private String lastName;
    private String email;
    private Double basicSalary;
    //given 8% from me 12% from company
    private Double epf;
    //5% from the company
    private Double etf;
    private List<Deductions> deductions;
}
