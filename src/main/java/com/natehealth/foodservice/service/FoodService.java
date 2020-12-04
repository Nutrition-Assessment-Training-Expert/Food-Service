package com.natehealth.foodservice.service;

import com.natehealth.foodservice.domain.Food;
import com.natehealth.foodservice.repository.FoodRepository;
import com.natehealth.foodservice.service.dto.AutoCompleteDTO;
import com.natehealth.foodservice.service.dto.FoodDTO;
import com.natehealth.foodservice.service.dto.FoodWeight;
import com.natehealth.foodservice.service.dto.NutrientResponse;
import com.natehealth.foodservice.service.mapper.FoodMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing {@link Food}.
 */
@Service
@Transactional
public class FoodService {

    private final Logger log = LoggerFactory.getLogger(FoodService.class);

    private final FoodRepository foodRepository;

    private final FoodMapper foodMapper;

    public FoodService(FoodRepository foodRepository, FoodMapper foodMapper) {
        this.foodRepository = foodRepository;
        this.foodMapper = foodMapper;
    }

    /**
     * Save a food.
     *
     * @param foodDTO the entity to save.
     * @return the persisted entity.
     */
    public FoodDTO save(FoodDTO foodDTO) {
        log.debug("Request to save Food : {}", foodDTO);
        Food food = foodMapper.toEntity(foodDTO);
        food = foodRepository.save(food);
        return foodMapper.toDto(food);
    }

    /**
     * Get all the foods.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<FoodDTO> findAll() {
        log.debug("Request to get all Foods");
        return foodRepository.findAll().stream()
            .map(foodMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the foods.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<FoodDTO> findAllByIds(List<Long> ids) {
        log.debug("Request to get all Foods");
        return ids.stream()
            .map(foodRepository::findById)
            .map(op -> op.map(foodMapper::toDto).orElse(null))
            .filter(Objects::nonNull)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the foods.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AutoCompleteDTO> findByWord(String search) {
        log.debug("Request to get all Foods");
        return foodRepository.findFirst10ByDescriptionStartingWith(search);/*.stream()
            .map(foodMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));*/
        //return foodRepository.findByName(search);
    }

    /**
     * Get one food by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FoodDTO> findOne(Long id) {
        log.debug("Request to get Food : {}", id);
        return foodRepository.findById(id)
            .map(foodMapper::toDto);
    }
    /**
     * Get one food by upc.
     *
     * @param upc the upc of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FoodDTO> findByUpc(String upc) {
        log.debug("Request to get Food : {}", upc);
        return foodRepository.findByBrandedFood_GtinUpc(upc)
            .map(foodMapper::toDto);
    }

    /**
     * Get one food by upc.
     *
     * @param FoodWeight the foodWeight of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FoodDTO> findByIdAndWeight(FoodWeight foodWeight) {
        log.debug("Request to get Food : {}", foodWeight);
        Optional<FoodDTO> foodDto = foodRepository.findById(foodWeight.getId())
            .map(foodMapper::toDto);
        foodDto.ifPresent(food ->
            food.getFoodNutrients()
                .stream()
                /*.map(dto ->
                    new NutrientResponse(dto.getNutrient().getId(),
                        dto.getNutrient().getName(),
                        dto.getNutrient().getUnitName(),dto.getAmount())
                )*/
                .forEach(n -> n.setAmount((foodWeight.getGrams()*n.getAmount())/100))
        );
        return foodDto;
    }
}
