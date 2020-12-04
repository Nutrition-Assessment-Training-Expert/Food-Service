package com.natehealth.foodservice.service.dto;

public class NutrientResponse {
    private Long id;
    private String name;
    private String unitName;
    private Double amount;

    public NutrientResponse(Long id, String name, String unitName, Double amount) {
        this.id = id;
        this.name = name;
        this.unitName = unitName;
        this.amount = amount;
    }

    public Long getId() {
        return id;
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

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
