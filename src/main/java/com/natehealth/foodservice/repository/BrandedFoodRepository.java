package com.natehealth.foodservice.repository;

import com.natehealth.foodservice.domain.BrandedFood;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the BrandedFood entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BrandedFoodRepository extends JpaRepository<BrandedFood, Long> {
}
