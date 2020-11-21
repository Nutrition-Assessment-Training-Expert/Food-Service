package com.natehealth.foodservice.service;

import com.natehealth.foodservice.domain.BrandedFood;
import com.natehealth.foodservice.repository.BrandedFoodRepository;
import com.natehealth.foodservice.service.dto.BrandedFoodDTO;
import com.natehealth.foodservice.service.mapper.BrandedFoodMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link BrandedFood}.
 */
@Service
@Transactional
public class BrandedFoodService {

    private final Logger log = LoggerFactory.getLogger(BrandedFoodService.class);

    private final BrandedFoodRepository brandedFoodRepository;

    private final BrandedFoodMapper brandedFoodMapper;

    public BrandedFoodService(BrandedFoodRepository brandedFoodRepository, BrandedFoodMapper brandedFoodMapper) {
        this.brandedFoodRepository = brandedFoodRepository;
        this.brandedFoodMapper = brandedFoodMapper;
    }

    /**
     * Save a brandedFood.
     *
     * @param brandedFoodDTO the entity to save.
     * @return the persisted entity.
     */
    public BrandedFoodDTO save(BrandedFoodDTO brandedFoodDTO) {
        log.debug("Request to save BrandedFood : {}", brandedFoodDTO);
        BrandedFood brandedFood = brandedFoodMapper.toEntity(brandedFoodDTO);
        brandedFood = brandedFoodRepository.save(brandedFood);
        return brandedFoodMapper.toDto(brandedFood);
    }

    /**
     * Get all the brandedFoods.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<BrandedFoodDTO> findAll() {
        log.debug("Request to get all BrandedFoods");
        return brandedFoodRepository.findAll().stream()
            .map(brandedFoodMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one brandedFood by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BrandedFoodDTO> findOne(Long id) {
        log.debug("Request to get BrandedFood : {}", id);
        return brandedFoodRepository.findById(id)
            .map(brandedFoodMapper::toDto);
    }

    /**
     * Delete the brandedFood by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete BrandedFood : {}", id);
        brandedFoodRepository.deleteById(id);
    }
}
