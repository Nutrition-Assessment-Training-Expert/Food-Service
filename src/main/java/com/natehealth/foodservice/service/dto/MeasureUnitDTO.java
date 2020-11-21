package com.natehealth.foodservice.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.natehealth.foodservice.domain.MeasureUnit} entity.
 */
public class MeasureUnitDTO implements Serializable {
    
    private Long id;

    @Size(max = 16)
    private String name;

    
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MeasureUnitDTO)) {
            return false;
        }

        return id != null && id.equals(((MeasureUnitDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MeasureUnitDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
