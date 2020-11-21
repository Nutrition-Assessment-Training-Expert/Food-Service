package com.natehealth.foodservice.repository;

import com.natehealth.foodservice.domain.FoodNutrient;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the FoodNutrient entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FoodNutrientRepository extends JpaRepository<FoodNutrient, Long> {
}
