package com.natehealth.foodservice.web.rest;

import com.natehealth.foodservice.service.FoodNutrientService;
import com.natehealth.foodservice.web.rest.errors.BadRequestAlertException;
import com.natehealth.foodservice.service.dto.FoodNutrientDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.natehealth.foodservice.domain.FoodNutrient}.
 */
@RestController
@RequestMapping("/api")
public class FoodNutrientResource {

    private final Logger log = LoggerFactory.getLogger(FoodNutrientResource.class);

    private static final String ENTITY_NAME = "foodserviceFoodNutrient";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FoodNutrientService foodNutrientService;

    public FoodNutrientResource(FoodNutrientService foodNutrientService) {
        this.foodNutrientService = foodNutrientService;
    }

    /**
     * {@code POST  /food-nutrients} : Create a new foodNutrient.
     *
     * @param foodNutrientDTO the foodNutrientDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new foodNutrientDTO, or with status {@code 400 (Bad Request)} if the foodNutrient has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/food-nutrients")
    public ResponseEntity<FoodNutrientDTO> createFoodNutrient(@RequestBody FoodNutrientDTO foodNutrientDTO) throws URISyntaxException {
        log.debug("REST request to save FoodNutrient : {}", foodNutrientDTO);
        if (foodNutrientDTO.getId() != null) {
            throw new BadRequestAlertException("A new foodNutrient cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FoodNutrientDTO result = foodNutrientService.save(foodNutrientDTO);
        return ResponseEntity.created(new URI("/api/food-nutrients/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /food-nutrients} : Updates an existing foodNutrient.
     *
     * @param foodNutrientDTO the foodNutrientDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated foodNutrientDTO,
     * or with status {@code 400 (Bad Request)} if the foodNutrientDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the foodNutrientDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/food-nutrients")
    public ResponseEntity<FoodNutrientDTO> updateFoodNutrient(@RequestBody FoodNutrientDTO foodNutrientDTO) throws URISyntaxException {
        log.debug("REST request to update FoodNutrient : {}", foodNutrientDTO);
        if (foodNutrientDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FoodNutrientDTO result = foodNutrientService.save(foodNutrientDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, foodNutrientDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /food-nutrients} : get all the foodNutrients.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of foodNutrients in body.
     */
    @GetMapping("/food-nutrients")
    public List<FoodNutrientDTO> getAllFoodNutrients() {
        log.debug("REST request to get all FoodNutrients");
        return foodNutrientService.findAll();
    }

    /**
     * {@code GET  /food-nutrients/:id} : get the "id" foodNutrient.
     *
     * @param id the id of the foodNutrientDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the foodNutrientDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/food-nutrients/{id}")
    public ResponseEntity<FoodNutrientDTO> getFoodNutrient(@PathVariable Long id) {
        log.debug("REST request to get FoodNutrient : {}", id);
        Optional<FoodNutrientDTO> foodNutrientDTO = foodNutrientService.findOne(id);
        return ResponseUtil.wrapOrNotFound(foodNutrientDTO);
    }

    /**
     * {@code DELETE  /food-nutrients/:id} : delete the "id" foodNutrient.
     *
     * @param id the id of the foodNutrientDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/food-nutrients/{id}")
    public ResponseEntity<Void> deleteFoodNutrient(@PathVariable Long id) {
        log.debug("REST request to delete FoodNutrient : {}", id);
        foodNutrientService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
