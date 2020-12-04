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

    private MeasureUnitDTO measureUnit;

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

    public MeasureUnitDTO getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(MeasureUnitDTO measureUnit) {
        this.measureUnit = measureUnit;
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
            "id=" + id +
            ", amount=" + amount +
            ", portionDescription='" + portionDescription + '\'' +
            ", modifier='" + modifier + '\'' +
            ", gramWeight=" + gramWeight +
            ", measureUnitId=" + measureUnit +
            '}';
    }
}
