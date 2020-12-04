package com.natehealth.foodservice.repository;

import com.natehealth.foodservice.domain.Food;

import com.natehealth.foodservice.service.dto.AutoCompleteDTO;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Food entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
    Optional<Food> findByBrandedFood_GtinUpc(String upc);
    /*@Query(value = "select id as id, description as description from food f where f.description like :search% limit 20"
        , nativeQuery = true)*/
    List<AutoCompleteDTO> findFirst10ByDescriptionStartingWith(@Param("search") String search);
   /* @Query(value = "SELECT Food FROM food f where f.description like :search% ")
    List<String> findByName(@Param("search") String search);*/
}
