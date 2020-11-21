package com.natehealth.foodservice.service;

import com.natehealth.foodservice.domain.FoodNutrient;
import com.natehealth.foodservice.repository.FoodNutrientRepository;
import com.natehealth.foodservice.service.dto.FoodNutrientDTO;
import com.natehealth.foodservice.service.mapper.FoodNutrientMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link FoodNutrient}.
 */
@Service
@Transactional
public class FoodNutrientService {

    private final Logger log = LoggerFactory.getLogger(FoodNutrientService.class);

    private final FoodNutrientRepository foodNutrientRepository;

    private final FoodNutrientMapper foodNutrientMapper;

    public FoodNutrientService(FoodNutrientRepository foodNutrientRepository, FoodNutrientMapper foodNutrientMapper) {
        this.foodNutrientRepository = foodNutrientRepository;
        this.foodNutrientMapper = foodNutrientMapper;
    }

    /**
     * Save a foodNutrient.
     *
     * @param foodNutrientDTO the entity to save.
     * @return the persisted entity.
     */
    public FoodNutrientDTO save(FoodNutrientDTO foodNutrientDTO) {
        log.debug("Request to save FoodNutrient : {}", foodNutrientDTO);
        FoodNutrient foodNutrient = foodNutrientMapper.toEntity(foodNutrientDTO);
        foodNutrient = foodNutrientRepository.save(foodNutrient);
        return foodNutrientMapper.toDto(foodNutrient);
    }

    /**
     * Get all the foodNutrients.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<FoodNutrientDTO> findAll() {
        log.debug("Request to get all FoodNutrients");
        return foodNutrientRepository.findAll().stream()
            .map(foodNutrientMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one foodNutrient by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FoodNutrientDTO> findOne(Long id) {
        log.debug("Request to get FoodNutrient : {}", id);
        return foodNutrientRepository.findById(id)
            .map(foodNutrientMapper::toDto);
    }

    /**
     * Delete the foodNutrient by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete FoodNutrient : {}", id);
        foodNutrientRepository.deleteById(id);
    }
}
