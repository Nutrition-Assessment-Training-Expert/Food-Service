package com.natehealth.foodservice.repository;

import com.natehealth.foodservice.domain.MeasureUnit;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the MeasureUnit entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MeasureUnitRepository extends JpaRepository<MeasureUnit, Long> {
}
