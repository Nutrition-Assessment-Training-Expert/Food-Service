package com.natehealth.foodservice.service.mapper;


import com.natehealth.foodservice.domain.*;
import com.natehealth.foodservice.service.dto.FoodNutrientDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link FoodNutrient} and its DTO {@link FoodNutrientDTO}.
 */
@Mapper(componentModel = "spring", uses = {NutrientMapper.class, FoodMapper.class})
public interface FoodNutrientMapper extends EntityMapper<FoodNutrientDTO, FoodNutrient> {

    @Mapping(source = "nutrient", target = "nutrient")
    FoodNutrientDTO toDto(FoodNutrient foodNutrient);

    @Mapping(source = "nutrient", target = "nutrient")
    FoodNutrient toEntity(FoodNutrientDTO foodNutrientDTO);

    default FoodNutrient fromId(Long id) {
        if (id == null) {
            return null;
        }
        FoodNutrient foodNutrient = new FoodNutrient();
        foodNutrient.setId(id);
        return foodNutrient;
    }
}
