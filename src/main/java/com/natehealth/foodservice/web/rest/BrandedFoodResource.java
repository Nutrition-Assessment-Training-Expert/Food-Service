package com.natehealth.foodservice.web.rest;

import com.natehealth.foodservice.service.BrandedFoodService;
import com.natehealth.foodservice.web.rest.errors.BadRequestAlertException;
import com.natehealth.foodservice.service.dto.BrandedFoodDTO;

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
 * REST controller for managing {@link com.natehealth.foodservice.domain.BrandedFood}.
 */
@RestController
@RequestMapping("/api")
public class BrandedFoodResource {

    private final Logger log = LoggerFactory.getLogger(BrandedFoodResource.class);

    private static final String ENTITY_NAME = "foodserviceBrandedFood";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BrandedFoodService brandedFoodService;

    public BrandedFoodResource(BrandedFoodService brandedFoodService) {
        this.brandedFoodService = brandedFoodService;
    }

    /**
     * {@code POST  /branded-foods} : Create a new brandedFood.
     *
     * @param brandedFoodDTO the brandedFoodDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new brandedFoodDTO, or with status {@code 400 (Bad Request)} if the brandedFood has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/branded-foods")
    public ResponseEntity<BrandedFoodDTO> createBrandedFood(@Valid @RequestBody BrandedFoodDTO brandedFoodDTO) throws URISyntaxException {
        log.debug("REST request to save BrandedFood : {}", brandedFoodDTO);
        if (brandedFoodDTO.getId() != null) {
            throw new BadRequestAlertException("A new brandedFood cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BrandedFoodDTO result = brandedFoodService.save(brandedFoodDTO);
        return ResponseEntity.created(new URI("/api/branded-foods/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /branded-foods} : Updates an existing brandedFood.
     *
     * @param brandedFoodDTO the brandedFoodDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated brandedFoodDTO,
     * or with status {@code 400 (Bad Request)} if the brandedFoodDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the brandedFoodDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/branded-foods")
    public ResponseEntity<BrandedFoodDTO> updateBrandedFood(@Valid @RequestBody BrandedFoodDTO brandedFoodDTO) throws URISyntaxException {
        log.debug("REST request to update BrandedFood : {}", brandedFoodDTO);
        if (brandedFoodDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BrandedFoodDTO result = brandedFoodService.save(brandedFoodDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, brandedFoodDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /branded-foods} : get all the brandedFoods.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of brandedFoods in body.
     */
    @GetMapping("/branded-foods")
    public List<BrandedFoodDTO> getAllBrandedFoods() {
        log.debug("REST request to get all BrandedFoods");
        return brandedFoodService.findAll();
    }

    /**
     * {@code GET  /branded-foods/:id} : get the "id" brandedFood.
     *
     * @param id the id of the brandedFoodDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the brandedFoodDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/branded-foods/{id}")
    public ResponseEntity<BrandedFoodDTO> getBrandedFood(@PathVariable Long id) {
        log.debug("REST request to get BrandedFood : {}", id);
        Optional<BrandedFoodDTO> brandedFoodDTO = brandedFoodService.findOne(id);
        return ResponseUtil.wrapOrNotFound(brandedFoodDTO);
    }

    /**
     * {@code DELETE  /branded-foods/:id} : delete the "id" brandedFood.
     *
     * @param id the id of the brandedFoodDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/branded-foods/{id}")
    public ResponseEntity<Void> deleteBrandedFood(@PathVariable Long id) {
        log.debug("REST request to delete BrandedFood : {}", id);
        brandedFoodService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
