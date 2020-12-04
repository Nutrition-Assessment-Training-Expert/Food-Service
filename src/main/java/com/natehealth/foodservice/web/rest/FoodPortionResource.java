package com.natehealth.foodservice.web.rest;

import com.natehealth.foodservice.domain.FoodPortion;
import com.natehealth.foodservice.domain.MeasureUnit;
import com.natehealth.foodservice.service.FoodPortionService;
import com.natehealth.foodservice.service.dto.MeasureUnitDTO;
import com.natehealth.foodservice.service.dto.UnitDTO;
import com.natehealth.foodservice.web.rest.errors.BadRequestAlertException;
import com.natehealth.foodservice.service.dto.FoodPortionDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.natehealth.foodservice.domain.FoodPortion}.
 */
@RestController
@RequestMapping("/api")
public class FoodPortionResource {

    private final Logger log = LoggerFactory.getLogger(FoodPortionResource.class);

    private static final String ENTITY_NAME = "foodserviceFoodPortion";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FoodPortionService foodPortionService;

    public FoodPortionResource(FoodPortionService foodPortionService) {
        this.foodPortionService = foodPortionService;
    }

    /**
     * {@code GET  /food-portions} : get all the foodPortions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of foodPortions in body.
     */
    @GetMapping("/food-portions")
    public List<FoodPortionDTO> getAllFoodPortions() {
        log.debug("REST request to get all FoodPortions");
        return foodPortionService.findAll();
    }

    /**
     * {@code GET  /food-portions} : get all the foodPortions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of foodPortions in body.
     */
    @GetMapping("/food-portions/unit/{id}")
    public List<UnitDTO> getFoodPortionsByFoodId(@PathVariable Long id) {
        log.debug("REST request to get all FoodPortions");
        return foodPortionService.findByFoodId(id);
    }

    /**
     * {@code GET  /food-portions/:id} : get the "id" foodPortion.
     *
     * @param id the id of the foodPortionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the foodPortionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/food-portions/{id}")
    public ResponseEntity<FoodPortionDTO> getFoodPortion(@PathVariable Long id) {
        log.debug("REST request to get FoodPortion : {}", id);
        Optional<FoodPortionDTO> foodPortionDTO = foodPortionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(foodPortionDTO);
    }
}
