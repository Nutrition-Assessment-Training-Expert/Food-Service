package com.natehealth.foodservice.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.natehealth.foodservice.domain.FoodPortion} entity.
 */
public class FoodPortionDTO implements Serializable {
    
    private Long id;

    private Double amount;

    @Size(max = 255)
    private String portionDescription;

    @Size(max = 255)
    private String modifier;

    private Double gramWeight;


    private Long foodId;

    private Long measureUnitId;
    
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

    public String getPortionDescription() {
        return portionDescription;
    }

    public void setPortionDescription(String portionDescription) {
        this.portionDescription = portionDescription;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Double getGramWeight() {
        return gramWeight;
    }

    public void setGramWeight(Double gramWeight) {
        this.gramWeight = gramWeight;
    }

    public Long getFoodId() {
        return foodId;
    }

    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }

    public Long getMeasureUnitId() {
        return measureUnitId;
    }

    public void setMeasureUnitId(Long measureUnitId) {
        this.measureUnitId = measureUnitId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FoodPortionDTO)) {
            return false;
        }

        return id != null && id.equals(((FoodPortionDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FoodPortionDTO{" +
            "id=" + getId() +
            ", amount=" + getAmount() +
            ", portionDescription='" + getPortionDescription() + "'" +
            ", modifier='" + getModifier() + "'" +
            ", gramWeight=" + getGramWeight() +
            ", foodId=" + getFoodId() +
            ", measureUnitId=" + getMeasureUnitId() +
            "}";
    }
}
