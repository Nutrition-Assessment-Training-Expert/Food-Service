package com.natehealth.foodservice.service.mapper;


import com.natehealth.foodservice.domain.*;
import com.natehealth.foodservice.service.dto.FoodCategoryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link FoodCategory} and its DTO {@link FoodCategoryDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FoodCategoryMapper extends EntityMapper<FoodCategoryDTO, FoodCategory> {


    @Mapping(target = "foods", ignore = true)
    @Mapping(target = "removeFood", ignore = true)
    FoodCategory toEntity(FoodCategoryDTO foodCategoryDTO);

    default FoodCategory fromId(Long id) {
        if (id == null) {
            return null;
        }
        FoodCategory foodCategory = new FoodCategory();
        foodCategory.setId(id);
        return foodCategory;
    }
}
