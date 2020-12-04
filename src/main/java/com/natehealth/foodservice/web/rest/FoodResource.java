package com.natehealth.foodservice.web.rest;

import com.natehealth.foodservice.service.FoodService;
import com.natehealth.foodservice.service.dto.AutoCompleteDTO;
import com.natehealth.foodservice.service.dto.FoodWeight;
import com.natehealth.foodservice.web.rest.errors.BadRequestAlertException;
import com.natehealth.foodservice.service.dto.FoodDTO;

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
import java.util.stream.StreamSupport;

/**
 * REST controller for managing {@link com.natehealth.foodservice.domain.Food}.
 */
@RestController
@RequestMapping("/api")
public class FoodResource {

    private final Logger log = LoggerFactory.getLogger(FoodResource.class);

    private static final String ENTITY_NAME = "foodserviceFood";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FoodService foodService;

    public FoodResource(FoodService foodService) {
        this.foodService = foodService;
    }

    /**
     * {@code GET  /foods} : get all the foods.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of foods in body.
     */
    @GetMapping("/foods")
    public List<FoodDTO> getAllFoods() {
        log.debug("REST request to get all Foods");
        return foodService.findAll();
    }

    /**
     * {@code GET  /foods/:id} : get the "id" food.
     *
     * @param id the id of the foodDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the foodDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/foods/{id}")
    public ResponseEntity<FoodDTO> getFood(@PathVariable Long id) {
        log.debug("REST request to get Food : {}", id);
        Optional<FoodDTO> foodDTO = foodService.findOne(id);
        return ResponseUtil.wrapOrNotFound(foodDTO);
    }

    @GetMapping("/foods/upc/{upc}")
    public ResponseEntity<FoodDTO> getFoodByUpc(@PathVariable String upc) {
        log.debug("REST request to get Food : {}", upc);
        Optional<FoodDTO> foodDTO = foodService.findByUpc(upc);
        return ResponseUtil.wrapOrNotFound(foodDTO);
    }

    @GetMapping("/foods/search/{search}")
    public List<AutoCompleteDTO> getFoodBySearch(@PathVariable String search) {
        log.debug("REST request to get all Foods");
        return foodService.findByWord(search);
    }

    @PostMapping("/foods")
    public List<FoodDTO> getFoodByIds(@Valid @RequestBody List<Long> ids) {
        return foodService.findAllByIds(ids);
    }

    @PostMapping("/food")
    public ResponseEntity<FoodDTO> getFoodByIdAndWeight(@Valid @RequestBody FoodWeight foodWeight) {
        return ResponseUtil.wrapOrNotFound(foodService.findByIdAndWeight(foodWeight));
    }
}
