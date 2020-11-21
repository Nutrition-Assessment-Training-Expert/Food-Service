package com.natehealth.foodservice.service;

import com.natehealth.foodservice.domain.Nutrient;
import com.natehealth.foodservice.repository.NutrientRepository;
import com.natehealth.foodservice.service.dto.NutrientDTO;
import com.natehealth.foodservice.service.mapper.NutrientMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Nutrient}.
 */
@Service
@Transactional
public class NutrientService {

    private final Logger log = LoggerFactory.getLogger(NutrientService.class);

    private final NutrientRepository nutrientRepository;

    private final NutrientMapper nutrientMapper;

    public NutrientService(NutrientRepository nutrientRepository, NutrientMapper nutrientMapper) {
        this.nutrientRepository = nutrientRepository;
        this.nutrientMapper = nutrientMapper;
    }

    /**
     * Save a nutrient.
     *
     * @param nutrientDTO the entity to save.
     * @return the persisted entity.
     */
    public NutrientDTO save(NutrientDTO nutrientDTO) {
        log.debug("Request to save Nutrient : {}", nutrientDTO);
        Nutrient nutrient = nutrientMapper.toEntity(nutrientDTO);
        nutrient = nutrientRepository.save(nutrient);
        return nutrientMapper.toDto(nutrient);
    }

    /**
     * Get all the nutrients.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<NutrientDTO> findAll() {
        log.debug("Request to get all Nutrients");
        return nutrientRepository.findAll().stream()
            .map(nutrientMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one nutrient by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<NutrientDTO> findOne(Long id) {
        log.debug("Request to get Nutrient : {}", id);
        return nutrientRepository.findById(id)
            .map(nutrientMapper::toDto);
    }

    /**
     * Delete the nutrient by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Nutrient : {}", id);
        nutrientRepository.deleteById(id);
    }
}
