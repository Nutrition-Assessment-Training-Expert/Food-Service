package com.natehealth.foodservice.service.dto;

public class UnitDTO {
    private Long id;
    private String name;
    private Double grams;

    public UnitDTO(Long id, String name, Double grams) {
        this.id = id;
        this.name = name;
        this.grams = grams;
    }

    public UnitDTO() {
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

    public Double getGrams() {
        return grams;
    }

    public void setGrams(Double grams) {
        this.grams = grams;
    }
}
