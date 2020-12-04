package com.natehealth.foodservice.service;

import com.natehealth.foodservice.domain.FoodPortion;
import com.natehealth.foodservice.repository.FoodPortionRepository;
import com.natehealth.foodservice.service.dto.FoodPortionDTO;
import com.natehealth.foodservice.service.dto.UnitDTO;
import com.natehealth.foodservice.service.mapper.FoodPortionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link FoodPortion}.
 */
@Service
@Transactional
public class FoodPortionService {

    private final Logger log = LoggerFactory.getLogger(FoodPortionService.class);

    private final FoodPortionRepository foodPortionRepository;

    private final FoodPortionMapper foodPortionMapper;

    public FoodPortionService(FoodPortionRepository foodPortionRepository, FoodPortionMapper foodPortionMapper) {
        this.foodPortionRepository = foodPortionRepository;
        this.foodPortionMapper = foodPortionMapper;
    }

    /**
     * Get all the foodPortions.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<FoodPortionDTO> findAll() {
        log.debug("Request to get all FoodPortions");
        return foodPortionRepository.findAll().stream()
            .map(foodPortionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get all the foodPortions.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<UnitDTO> findByFoodId(Long id) {
        log.debug("Request to get all FoodPortions");
        return foodPortionRepository.findDistinctByFood_Id(id).stream()
            .map(foodPortionMapper::toDto)
            .map(dto -> {
                UnitDTO measureUnit = new UnitDTO();
                measureUnit.setId(dto.getId());
                measureUnit.setName(dto.getMeasureUnit().getId() == 9999? dto.getModifier(): dto.getMeasureUnit().getName());
                measureUnit.setGrams(dto.getGramWeight()/dto.getAmount());
                return measureUnit;
            })
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one foodPortion by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FoodPortionDTO> findOne(Long id) {
        log.debug("Request to get FoodPortion : {}", id);
        return foodPortionRepository.findById(id)
            .map(foodPortionMapper::toDto);
    }
}
