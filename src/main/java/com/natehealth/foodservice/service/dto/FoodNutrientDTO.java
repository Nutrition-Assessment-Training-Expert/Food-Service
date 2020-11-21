package com.natehealth.foodservice.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.natehealth.foodservice.domain.FoodNutrient} entity.
 */
public class FoodNutrientDTO implements Serializable {
    
    private Long id;

    private Double amount;


    private Long nutrientId;

    private Long foodId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getNutrientId() {
        return nutrientId;
    }

    public void setNutrientId(Long nutrientId) {
        this.nutrientId = nutrientId;
    }

    public Long getFoodId() {
        return foodId;
    }

    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FoodNutrientDTO)) {
            return false;
        }

        return id != null && id.equals(((FoodNutrientDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FoodNutrientDTO{" +
            "id=" + getId() +
            ", amount=" + getAmount() +
            ", nutrientId=" + getNutrientId() +
            ", foodId=" + getFoodId() +
            "}";
    }
}
