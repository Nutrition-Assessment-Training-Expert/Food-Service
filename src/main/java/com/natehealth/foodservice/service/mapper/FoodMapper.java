package com.natehealth.foodservice.service.mapper;


import com.natehealth.foodservice.domain.*;
import com.natehealth.foodservice.service.dto.FoodDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Food} and its DTO {@link FoodDTO}.
 */
@Mapper(componentModel = "spring", uses = {FoodCategoryMapper.class})
public interface FoodMapper extends EntityMapper<FoodDTO, Food> {

    @Mapping(source = "brandedFood", target = "brandedFood")
    @Mapping(source = "foodCategory.description", target = "foodCategory")
    @Mapping(source = "foodNutrients", target = "foodNutrients")
    FoodDTO toDto(Food food);

    @Mapping(source = "brandedFood", target = "brandedFood")
    @Mapping(target = "removeFoodPoriton", ignore = true)
    @Mapping(target = "removeFoodNutrient", ignore = true)
    @Mapping(source = "foodCategory", target = "foodCategory")
    @Mapping(source = "foodNutrients", target = "foodNutrients")
    Food toEntity(FoodDTO foodDTO);

    default Food fromId(Long id) {
        if (id == null) {
            return null;
        }
        Food food = new Food();
        food.setId(id);
        return food;
    }
}
