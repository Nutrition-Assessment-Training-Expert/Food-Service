package com.natehealth.foodservice.service;

import com.natehealth.foodservice.domain.MeasureUnit;
import com.natehealth.foodservice.repository.MeasureUnitRepository;
import com.natehealth.foodservice.service.dto.MeasureUnitDTO;
import com.natehealth.foodservice.service.mapper.MeasureUnitMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link MeasureUnit}.
 */
@Service
@Transactional
public class MeasureUnitService {

    private final Logger log = LoggerFactory.getLogger(MeasureUnitService.class);

    private final MeasureUnitRepository measureUnitRepository;

    private final MeasureUnitMapper measureUnitMapper;

    public MeasureUnitService(MeasureUnitRepository measureUnitRepository, MeasureUnitMapper measureUnitMapper) {
        this.measureUnitRepository = measureUnitRepository;
        this.measureUnitMapper = measureUnitMapper;
    }

    /**
     * Save a measureUnit.
     *
     * @param measureUnitDTO the entity to save.
     * @return the persisted entity.
     */
    public MeasureUnitDTO save(MeasureUnitDTO measureUnitDTO) {
        log.debug("Request to save MeasureUnit : {}", measureUnitDTO);
        MeasureUnit measureUnit = measureUnitMapper.toEntity(measureUnitDTO);
        measureUnit = measureUnitRepository.save(measureUnit);
        return measureUnitMapper.toDto(measureUnit);
    }

    /**
     * Get all the measureUnits.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<MeasureUnitDTO> findAll() {
        log.debug("Request to get all MeasureUnits");
        return measureUnitRepository.findAll().stream()
            .map(measureUnitMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one measureUnit by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MeasureUnitDTO> findOne(Long id) {
        log.debug("Request to get MeasureUnit : {}", id);
        return measureUnitRepository.findById(id)
            .map(measureUnitMapper::toDto);
    }

    /**
     * Delete the measureUnit by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MeasureUnit : {}", id);
        measureUnitRepository.deleteById(id);
    }
}
