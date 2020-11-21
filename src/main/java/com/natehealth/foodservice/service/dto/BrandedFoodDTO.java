package com.natehealth.foodservice.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.natehealth.foodservice.domain.BrandedFood} entity.
 */
public class BrandedFoodDTO implements Serializable {
    
    private Long id;

    @Size(max = 78)
    private String brandOwner;

    @Size(max = 15)
    private String gtinUpc;

    @Size(max = 4000)
    private String ingredients;

    private Double servingSize;

    @Size(max = 2)
    private String servingSizeUnit;

    @Size(max = 174)
    private String householdServingFulltext;

    @Size(max = 72)
    private String brandedFoodCategory;


    private Long foodId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrandOwner() {
        return brandOwner;
    }

    public void setBrandOwner(String brandOwner) {
        this.brandOwner = brandOwner;
    }

    public String getGtinUpc() {
        return gtinUpc;
    }

    public void setGtinUpc(String gtinUpc) {
        this.gtinUpc = gtinUpc;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public Double getServingSize() {
        return servingSize;
    }

    public void setServingSize(Double servingSize) {
        this.servingSize = servingSize;
    }

    public String getServingSizeUnit() {
        return servingSizeUnit;
    }

    public void setServingSizeUnit(String servingSizeUnit) {
        this.servingSizeUnit = servingSizeUnit;
    }

    public String getHouseholdServingFulltext() {
        return householdServingFulltext;
    }

    public void setHouseholdServingFulltext(String householdServingFulltext) {
        this.householdServingFulltext = householdServingFulltext;
    }

    public String getBrandedFoodCategory() {
        return brandedFoodCategory;
    }

    public void setBrandedFoodCategory(String brandedFoodCategory) {
        this.brandedFoodCategory = brandedFoodCategory;
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
        if (!(o instanceof BrandedFoodDTO)) {
            return false;
        }

        return id != null && id.equals(((BrandedFoodDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BrandedFoodDTO{" +
            "id=" + getId() +
            ", brandOwner='" + getBrandOwner() + "'" +
            ", gtinUpc='" + getGtinUpc() + "'" +
            ", ingredients='" + getIngredients() + "'" +
            ", servingSize=" + getServingSize() +
            ", servingSizeUnit='" + getServingSizeUnit() + "'" +
            ", householdServingFulltext='" + getHouseholdServingFulltext() + "'" +
            ", brandedFoodCategory='" + getBrandedFoodCategory() + "'" +
            ", foodId=" + getFoodId() +
            "}";
    }
}
