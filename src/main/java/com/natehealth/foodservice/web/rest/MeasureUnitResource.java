package com.natehealth.foodservice.web.rest;

import com.natehealth.foodservice.service.MeasureUnitService;
import com.natehealth.foodservice.web.rest.errors.BadRequestAlertException;
import com.natehealth.foodservice.service.dto.MeasureUnitDTO;

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
 * REST controller for managing {@link com.natehealth.foodservice.domain.MeasureUnit}.
 */
@RestController
@RequestMapping("/api")
public class MeasureUnitResource {

    private final Logger log = LoggerFactory.getLogger(MeasureUnitResource.class);

    private static final String ENTITY_NAME = "foodserviceMeasureUnit";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MeasureUnitService measureUnitService;

    public MeasureUnitResource(MeasureUnitService measureUnitService) {
        this.measureUnitService = measureUnitService;
    }

    /**
     * {@code POST  /measure-units} : Create a new measureUnit.
     *
     * @param measureUnitDTO the measureUnitDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new measureUnitDTO, or with status {@code 400 (Bad Request)} if the measureUnit has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/measure-units")
    public ResponseEntity<MeasureUnitDTO> createMeasureUnit(@Valid @RequestBody MeasureUnitDTO measureUnitDTO) throws URISyntaxException {
        log.debug("REST request to save MeasureUnit : {}", measureUnitDTO);
        if (measureUnitDTO.getId() != null) {
            throw new BadRequestAlertException("A new measureUnit cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MeasureUnitDTO result = measureUnitService.save(measureUnitDTO);
        return ResponseEntity.created(new URI("/api/measure-units/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /measure-units} : Updates an existing measureUnit.
     *
     * @param measureUnitDTO the measureUnitDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated measureUnitDTO,
     * or with status {@code 400 (Bad Request)} if the measureUnitDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the measureUnitDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/measure-units")
    public ResponseEntity<MeasureUnitDTO> updateMeasureUnit(@Valid @RequestBody MeasureUnitDTO measureUnitDTO) throws URISyntaxException {
        log.debug("REST request to update MeasureUnit : {}", measureUnitDTO);
        if (measureUnitDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MeasureUnitDTO result = measureUnitService.save(measureUnitDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, measureUnitDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /measure-units} : get all the measureUnits.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of measureUnits in body.
     */
    @GetMapping("/measure-units")
    public List<MeasureUnitDTO> getAllMeasureUnits() {
        log.debug("REST request to get all MeasureUnits");
        return measureUnitService.findAll();
    }

    /**
     * {@code GET  /measure-units/:id} : get the "id" measureUnit.
     *
     * @param id the id of the measureUnitDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the measureUnitDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/measure-units/{id}")
    public ResponseEntity<MeasureUnitDTO> getMeasureUnit(@PathVariable Long id) {
        log.debug("REST request to get MeasureUnit : {}", id);
        Optional<MeasureUnitDTO> measureUnitDTO = measureUnitService.findOne(id);
        return ResponseUtil.wrapOrNotFound(measureUnitDTO);
    }

    /**
     * {@code DELETE  /measure-units/:id} : delete the "id" measureUnit.
     *
     * @param id the id of the measureUnitDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/measure-units/{id}")
    public ResponseEntity<Void> deleteMeasureUnit(@PathVariable Long id) {
        log.debug("REST request to delete MeasureUnit : {}", id);
        measureUnitService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
