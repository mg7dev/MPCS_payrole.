package com.mpcs.Model;

import javax.persistence.*;

@Entity
@Table(name = "Deductions")
public class Deductions {
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Id
    private String deductionId;

    private String name;
    private Double amount;

    public Deductions() {
    }

    public Long getId() {
        return id;
    }

    public String getDeductionId() {
        return deductionId;
    }

    public void setDeductionId(String deductionId) {
        this.deductionId = deductionId;
    }

    public void setId(Long id) {
        this.id = id;
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
