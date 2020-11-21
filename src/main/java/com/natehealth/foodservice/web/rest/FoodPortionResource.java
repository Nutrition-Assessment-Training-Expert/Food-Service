package com.natehealth.foodservice.web.rest;

import com.natehealth.foodservice.service.FoodPortionService;
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
     * {@code POST  /food-portions} : Create a new foodPortion.
     *
     * @param foodPortionDTO the foodPortionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new foodPortionDTO, or with status {@code 400 (Bad Request)} if the foodPortion has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/food-portions")
    public ResponseEntity<FoodPortionDTO> createFoodPortion(@Valid @RequestBody FoodPortionDTO foodPortionDTO) throws URISyntaxException {
        log.debug("REST request to save FoodPortion : {}", foodPortionDTO);
        if (foodPortionDTO.getId() != null) {
            throw new BadRequestAlertException("A new foodPortion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FoodPortionDTO result = foodPortionService.save(foodPortionDTO);
        return ResponseEntity.created(new URI("/api/food-portions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /food-portions} : Updates an existing foodPortion.
     *
     * @param foodPortionDTO the foodPortionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated foodPortionDTO,
     * or with status {@code 400 (Bad Request)} if the foodPortionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the foodPortionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/food-portions")
    public ResponseEntity<FoodPortionDTO> updateFoodPortion(@Valid @RequestBody FoodPortionDTO foodPortionDTO) throws URISyntaxException {
        log.debug("REST request to update FoodPortion : {}", foodPortionDTO);
        if (foodPortionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FoodPortionDTO result = foodPortionService.save(foodPortionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, foodPortionDTO.getId().toString()))
            .body(result);
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

    /**
     * {@code DELETE  /food-portions/:id} : delete the "id" foodPortion.
     *
     * @param id the id of the foodPortionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/food-portions/{id}")
    public ResponseEntity<Void> deleteFoodPortion(@PathVariable Long id) {
        log.debug("REST request to delete FoodPortion : {}", id);
        foodPortionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
