package com.natehealth.foodservice.web.rest;

import com.natehealth.foodservice.FoodserviceApp;
import com.natehealth.foodservice.domain.MeasureUnit;
import com.natehealth.foodservice.repository.MeasureUnitRepository;
import com.natehealth.foodservice.service.MeasureUnitService;
import com.natehealth.foodservice.service.dto.MeasureUnitDTO;
import com.natehealth.foodservice.service.mapper.MeasureUnitMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link MeasureUnitResource} REST controller.
 */
@SpringBootTest(classes = FoodserviceApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class MeasureUnitResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private MeasureUnitRepository measureUnitRepository;

    @Autowired
    private MeasureUnitMapper measureUnitMapper;

    @Autowired
    private MeasureUnitService measureUnitService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMeasureUnitMockMvc;

    private MeasureUnit measureUnit;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MeasureUnit createEntity(EntityManager em) {
        MeasureUnit measureUnit = new MeasureUnit()
            .name(DEFAULT_NAME);
        return measureUnit;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MeasureUnit createUpdatedEntity(EntityManager em) {
        MeasureUnit measureUnit = new MeasureUnit()
            .name(UPDATED_NAME);
        return measureUnit;
    }

    @BeforeEach
    public void initTest() {
        measureUnit = createEntity(em);
    }

    @Test
    @Transactional
    public void createMeasureUnit() throws Exception {
        int databaseSizeBeforeCreate = measureUnitRepository.findAll().size();
        // Create the MeasureUnit
        MeasureUnitDTO measureUnitDTO = measureUnitMapper.toDto(measureUnit);
        restMeasureUnitMockMvc.perform(post("/api/measure-units")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(measureUnitDTO)))
            .andExpect(status().isCreated());

        // Validate the MeasureUnit in the database
        List<MeasureUnit> measureUnitList = measureUnitRepository.findAll();
        assertThat(measureUnitList).hasSize(databaseSizeBeforeCreate + 1);
        MeasureUnit testMeasureUnit = measureUnitList.get(measureUnitList.size() - 1);
        assertThat(testMeasureUnit.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createMeasureUnitWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = measureUnitRepository.findAll().size();

        // Create the MeasureUnit with an existing ID
        measureUnit.setId(1L);
        MeasureUnitDTO measureUnitDTO = measureUnitMapper.toDto(measureUnit);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMeasureUnitMockMvc.perform(post("/api/measure-units")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(measureUnitDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MeasureUnit in the database
        List<MeasureUnit> measureUnitList = measureUnitRepository.findAll();
        assertThat(measureUnitList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMeasureUnits() throws Exception {
        // Initialize the database
        measureUnitRepository.saveAndFlush(measureUnit);

        // Get all the measureUnitList
        restMeasureUnitMockMvc.perform(get("/api/measure-units?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(measureUnit.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }
    
    @Test
    @Transactional
    public void getMeasureUnit() throws Exception {
        // Initialize the database
        measureUnitRepository.saveAndFlush(measureUnit);

        // Get the measureUnit
        restMeasureUnitMockMvc.perform(get("/api/measure-units/{id}", measureUnit.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(measureUnit.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }
    @Test
    @Transactional
    public void getNonExistingMeasureUnit() throws Exception {
        // Get the measureUnit
        restMeasureUnitMockMvc.perform(get("/api/measure-units/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMeasureUnit() throws Exception {
        // Initialize the database
        measureUnitRepository.saveAndFlush(measureUnit);

        int databaseSizeBeforeUpdate = measureUnitRepository.findAll().size();

        // Update the measureUnit
        MeasureUnit updatedMeasureUnit = measureUnitRepository.findById(measureUnit.getId()).get();
        // Disconnect from session so that the updates on updatedMeasureUnit are not directly saved in db
        em.detach(updatedMeasureUnit);
        updatedMeasureUnit
            .name(UPDATED_NAME);
        MeasureUnitDTO measureUnitDTO = measureUnitMapper.toDto(updatedMeasureUnit);

        restMeasureUnitMockMvc.perform(put("/api/measure-units")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(measureUnitDTO)))
            .andExpect(status().isOk());

        // Validate the MeasureUnit in the database
        List<MeasureUnit> measureUnitList = measureUnitRepository.findAll();
        assertThat(measureUnitList).hasSize(databaseSizeBeforeUpdate);
        MeasureUnit testMeasureUnit = measureUnitList.get(measureUnitList.size() - 1);
        assertThat(testMeasureUnit.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingMeasureUnit() throws Exception {
        int databaseSizeBeforeUpdate = measureUnitRepository.findAll().size();

        // Create the MeasureUnit
        MeasureUnitDTO measureUnitDTO = measureUnitMapper.toDto(measureUnit);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMeasureUnitMockMvc.perform(put("/api/measure-units")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(measureUnitDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MeasureUnit in the database
        List<MeasureUnit> measureUnitList = measureUnitRepository.findAll();
        assertThat(measureUnitList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMeasureUnit() throws Exception {
        // Initialize the database
        measureUnitRepository.saveAndFlush(measureUnit);

        int databaseSizeBeforeDelete = measureUnitRepository.findAll().size();

        // Delete the measureUnit
        restMeasureUnitMockMvc.perform(delete("/api/measure-units/{id}", measureUnit.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MeasureUnit> measureUnitList = measureUnitRepository.findAll();
        assertThat(measureUnitList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
