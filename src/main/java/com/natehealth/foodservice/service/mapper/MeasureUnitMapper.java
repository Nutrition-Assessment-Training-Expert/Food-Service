package com.natehealth.foodservice.service.mapper;


import com.natehealth.foodservice.domain.*;
import com.natehealth.foodservice.service.dto.MeasureUnitDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MeasureUnit} and its DTO {@link MeasureUnitDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MeasureUnitMapper extends EntityMapper<MeasureUnitDTO, MeasureUnit> {


    @Mapping(target = "foodPortions", ignore = true)
    @Mapping(target = "removeFoodPortion", ignore = true)
    MeasureUnit toEntity(MeasureUnitDTO measureUnitDTO);

    default MeasureUnit fromId(Long id) {
        if (id == null) {
            return null;
        }
        MeasureUnit measureUnit = new MeasureUnit();
        measureUnit.setId(id);
        return measureUnit;
    }
}
