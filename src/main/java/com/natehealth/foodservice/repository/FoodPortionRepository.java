package com.natehealth.foodservice.repository;

import com.natehealth.foodservice.domain.FoodPortion;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the FoodPortion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FoodPortionRepository extends JpaRepository<FoodPortion, Long> {
}
