package com.mpcs.Model;

import javax.persistence.*;

@Entity
@Table(name = "Allowances")
public class SalaryAllowances {
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Id
    private String allowanceId;
    private String name;
    private Double amount;
    @Column(name = "emp_id")
    private String empId;

    public SalaryAllowances() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAllowanceId() {
        return allowanceId;
    }

    public void setAllowanceId(String allowanceId) {
        this.allowanceId = allowanceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
