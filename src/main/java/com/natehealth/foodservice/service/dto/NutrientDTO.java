package com.natehealth.foodservice.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.natehealth.foodservice.domain.Nutrient} entity.
 */
public class NutrientDTO implements Serializable {
    
    private Long id;

    @Size(max = 40)
    private String name;

    @Size(max = 6)
    private String unitName;

    private Double rank;

    
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

    public Double getRank() {
        return rank;
    }

    public void setRank(Double rank) {
        this.rank = rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NutrientDTO)) {
            return false;
        }

        return id != null && id.equals(((NutrientDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NutrientDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", unitName='" + getUnitName() + "'" +
            ", rank=" + getRank() +
            "}";
    }
}
