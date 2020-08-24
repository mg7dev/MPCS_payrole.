package com.mpcs.Model;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "EMPLOYEE")
public class Employee {
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Id
    private String emp_id;

    @OneToMany(targetEntity = SalaryAllowances.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "emp_id", referencedColumnName = "emp_id")
    private List<SalaryAllowances> salaryAllowances;

    /*@OneToMany(mappedBy="employee", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
            CascadeType.REFRESH })
    private List<SalaryAllowances> salaryAllowances;*/

    private String firstName;
    private String lastName;
    private String email;
    private Double basicSalary;
    //given 8% from me 12% from company
    private Double epf;
    //5% from the company
    private Double etf;
    //private Double totalSalary;

    @OneToMany(targetEntity = Deductions.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "emp_deduction_fk", referencedColumnName = "emp_id")
    private List<Deductions> deductions;

    public Employee() {
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(Double basicSalary) {
        this.basicSalary = basicSalary;
    }

    public List<SalaryAllowances> getSalaryAllowances() {
        return salaryAllowances;
    }

    public void setSalaryAllowances(List<SalaryAllowances> salaryAllowances) {
        this.salaryAllowances = salaryAllowances;
    }

    public void setDeductions(List<Deductions> deductions) {
        this.deductions = deductions;
    }

    public List<Deductions> getDeductions() {
        return deductions;
    }

    public Double getEpf() {
        return epf;
    }

    public void setEpf(Double epf) {
        this.epf = epf;
    }

    public Double getEtf() {
        return etf;
    }

    public void setEtf(Double etf) {
        this.etf = etf;
    }


}
