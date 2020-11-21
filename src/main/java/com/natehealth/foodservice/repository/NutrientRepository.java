package com.natehealth.foodservice.repository;

import com.natehealth.foodservice.domain.Nutrient;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Nutrient entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NutrientRepository extends JpaRepository<Nutrient, Long> {
}
