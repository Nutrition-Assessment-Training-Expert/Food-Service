package com.natehealth.foodservice.service.dto;

public class FoodWeight {
    private Long id;
    private Double grams;

    public FoodWeight() {
    }

    public FoodWeight(Long id, Double grams) {
        this.id = id;
        this.grams = grams;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getGrams() {
        return grams;
    }

    public void setGrams(Double grams) {
        this.grams = grams;
    }
}
