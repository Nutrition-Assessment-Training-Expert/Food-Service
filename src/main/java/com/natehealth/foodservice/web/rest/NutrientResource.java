package com.natehealth.foodservice.web.rest;

import com.natehealth.foodservice.service.NutrientService;
import com.natehealth.foodservice.web.rest.errors.BadRequestAlertException;
import com.natehealth.foodservice.service.dto.NutrientDTO;

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
 * REST controller for managing {@link com.natehealth.foodservice.domain.Nutrient}.
 */
@RestController
@RequestMapping("/api")
public class NutrientResource {

    private final Logger log = LoggerFactory.getLogger(NutrientResource.class);

    private static final String ENTITY_NAME = "foodserviceNutrient";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NutrientService nutrientService;

    public NutrientResource(NutrientService nutrientService) {
        this.nutrientService = nutrientService;
    }

    /**
     * {@code POST  /nutrients} : Create a new nutrient.
     *
     * @param nutrientDTO the nutrientDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new nutrientDTO, or with status {@code 400 (Bad Request)} if the nutrient has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/nutrients")
    public ResponseEntity<NutrientDTO> createNutrient(@Valid @RequestBody NutrientDTO nutrientDTO) throws URISyntaxException {
        log.debug("REST request to save Nutrient : {}", nutrientDTO);
        if (nutrientDTO.getId() != null) {
            throw new BadRequestAlertException("A new nutrient cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NutrientDTO result = nutrientService.save(nutrientDTO);
        return ResponseEntity.created(new URI("/api/nutrients/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /nutrients} : Updates an existing nutrient.
     *
     * @param nutrientDTO the nutrientDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated nutrientDTO,
     * or with status {@code 400 (Bad Request)} if the nutrientDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the nutrientDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/nutrients")
    public ResponseEntity<NutrientDTO> updateNutrient(@Valid @RequestBody NutrientDTO nutrientDTO) throws URISyntaxException {
        log.debug("REST request to update Nutrient : {}", nutrientDTO);
        if (nutrientDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NutrientDTO result = nutrientService.save(nutrientDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, nutrientDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /nutrients} : get all the nutrients.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of nutrients in body.
     */
    @GetMapping("/nutrients")
    public List<NutrientDTO> getAllNutrients() {
        log.debug("REST request to get all Nutrients");
        return nutrientService.findAll();
    }

    /**
     * {@code GET  /nutrients/:id} : get the "id" nutrient.
     *
     * @param id the id of the nutrientDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the nutrientDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/nutrients/{id}")
    public ResponseEntity<NutrientDTO> getNutrient(@PathVariable Long id) {
        log.debug("REST request to get Nutrient : {}", id);
        Optional<NutrientDTO> nutrientDTO = nutrientService.findOne(id);
        return ResponseUtil.wrapOrNotFound(nutrientDTO);
    }

    /**
     * {@code DELETE  /nutrients/:id} : delete the "id" nutrient.
     *
     * @param id the id of the nutrientDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/nutrients/{id}")
    public ResponseEntity<Void> deleteNutrient(@PathVariable Long id) {
        log.debug("REST request to delete Nutrient : {}", id);
        nutrientService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
