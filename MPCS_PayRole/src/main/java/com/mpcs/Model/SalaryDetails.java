package com.mpcs.Model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "SalaryDetails")
@Data
public class SalaryDetails {

    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Id
    private String emp_id;

    private String firstName;
    private String lastName;
    private String email;
    private Double basicSalary;
    private Double allowancesAmount;
    //given 8% from me 12% from company
    private Double epf;
    //5% from the company
    private Double etf;
    private Double deductions;
    private Double totalSalary;

}
