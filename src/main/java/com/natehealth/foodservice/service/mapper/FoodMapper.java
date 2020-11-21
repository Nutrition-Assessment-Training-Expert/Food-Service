package com.natehealth.foodservice.service.mapper;


import com.natehealth.foodservice.domain.*;
import com.natehealth.foodservice.service.dto.FoodDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Food} and its DTO {@link FoodDTO}.
 */
@Mapper(componentModel = "spring", uses = {FoodCategoryMapper.class})
public interface FoodMapper extends EntityMapper<FoodDTO, Food> {

    @Mapping(source = "foodCategory.id", target = "foodCategoryId")
    FoodDTO toDto(Food food);

    @Mapping(target = "foodPoritons", ignore = true)
    @Mapping(target = "removeFoodPoriton", ignore = true)
    @Mapping(target = "foodNutrients", ignore = true)
    @Mapping(target = "removeFoodNutrient", ignore = true)
    @Mapping(target = "brandedFood", ignore = true)
    @Mapping(source = "foodCategoryId", target = "foodCategory")
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
