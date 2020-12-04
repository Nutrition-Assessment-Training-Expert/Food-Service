package com.natehealth.foodservice.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Set;

/**
 * A DTO for the {@link com.natehealth.foodservice.domain.Food} entity.
 */
public class FoodDTO implements Serializable {

    private Long id;

    @Size(max = 255)
    private String description;

    private BrandedFoodDTO brandedFood;

    private String foodCategory;

    private Set<FoodNutrientDTO> foodNutrients;

    //private Set<FoodPortionDTO> foodPortions;

    public Set<FoodNutrientDTO> getFoodNutrients() {
        return foodNutrients;
    }

    public void setFoodNutrients(Set<FoodNutrientDTO> foodNutrients) {
        this.foodNutrients = foodNutrients;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BrandedFoodDTO getBrandedFood() {
        return brandedFood;
    }

    public void setBrandedFood(BrandedFoodDTO brandedFood) {
        this.brandedFood = brandedFood;
    }

    public String getFoodCategory() {
        return foodCategory;
    }

    public void setFoodCategory(String foodCategory) {
        this.foodCategory = foodCategory;
    }

    /*public Set<FoodPortionDTO> getFoodPortions() {
        return foodPortions;
    }

    public void setFoodPortions(Set<FoodPortionDTO> foodPortions) {
        this.foodPortions = foodPortions;
    }
*/
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FoodDTO)) {
            return false;
        }

        return id != null && id.equals(((FoodDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore

    @Override
    public String toString() {
        return "FoodDTO{" +
            "id=" + id +
            ", description='" + description + '\'' +
            ", brandedFood=" + brandedFood +
            ", foodCategory='" + foodCategory + '\'' +
            ", foodNutrients=" + foodNutrients +
            '}';
    }
}
