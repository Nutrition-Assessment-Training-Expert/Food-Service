package com.natehealth.foodservice.service.mapper;


import com.natehealth.foodservice.domain.*;
import com.natehealth.foodservice.service.dto.BrandedFoodDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link BrandedFood} and its DTO {@link BrandedFoodDTO}.
 */
@Mapper(componentModel = "spring", uses = {FoodMapper.class})
public interface BrandedFoodMapper extends EntityMapper<BrandedFoodDTO, BrandedFood> {

    @Mapping(source = "food.id", target = "foodId")
    BrandedFoodDTO toDto(BrandedFood brandedFood);

    @Mapping(source = "foodId", target = "food")
    BrandedFood toEntity(BrandedFoodDTO brandedFoodDTO);

    default BrandedFood fromId(Long id) {
        if (id == null) {
            return null;
        }
        BrandedFood brandedFood = new BrandedFood();
        brandedFood.setId(id);
        return brandedFood;
    }
}
