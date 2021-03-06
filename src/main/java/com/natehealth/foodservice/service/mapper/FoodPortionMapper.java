package com.natehealth.foodservice.service.mapper;


import com.natehealth.foodservice.domain.*;
import com.natehealth.foodservice.service.dto.FoodPortionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link FoodPortion} and its DTO {@link FoodPortionDTO}.
 */
@Mapper(componentModel = "spring", uses = {FoodMapper.class, MeasureUnitMapper.class})
public interface FoodPortionMapper extends EntityMapper<FoodPortionDTO, FoodPortion> {

    @Mapping(source = "measureUnit", target = "measureUnit")
    FoodPortionDTO toDto(FoodPortion foodPortion);

    @Mapping(source = "measureUnit", target = "measureUnit")
    FoodPortion toEntity(FoodPortionDTO foodPortionDTO);

    default FoodPortion fromId(Long id) {
        if (id == null) {
            return null;
        }
        FoodPortion foodPortion = new FoodPortion();
        foodPortion.setId(id);
        return foodPortion;
    }
}
