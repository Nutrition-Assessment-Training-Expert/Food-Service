package com.natehealth.foodservice.service.mapper;


import com.natehealth.foodservice.domain.*;
import com.natehealth.foodservice.service.dto.NutrientDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Nutrient} and its DTO {@link NutrientDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface NutrientMapper extends EntityMapper<NutrientDTO, Nutrient> {


    @Mapping(target = "foodNutrients", ignore = true)
    @Mapping(target = "removeFoodNutrient", ignore = true)
    Nutrient toEntity(NutrientDTO nutrientDTO);

    default Nutrient fromId(Long id) {
        if (id == null) {
            return null;
        }
        Nutrient nutrient = new Nutrient();
        nutrient.setId(id);
        return nutrient;
    }
}
