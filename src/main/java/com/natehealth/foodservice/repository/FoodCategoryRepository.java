package com.natehealth.foodservice.repository;

import com.natehealth.foodservice.domain.FoodCategory;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the FoodCategory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Long> {
}
