package com.natehealth.foodservice.web.rest;

import com.natehealth.foodservice.FoodserviceApp;
import com.natehealth.foodservice.domain.Nutrient;
import com.natehealth.foodservice.repository.NutrientRepository;
import com.natehealth.foodservice.service.NutrientService;
import com.natehealth.foodservice.service.dto.NutrientDTO;
import com.natehealth.foodservice.service.mapper.NutrientMapper;

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
 * Integration tests for the {@link NutrientResource} REST controller.
 */
@SpringBootTest(classes = FoodserviceApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class NutrientResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_UNIT_NAME = "AAAAAA";
    private static final String UPDATED_UNIT_NAME = "BBBBBB";

    private static final Double DEFAULT_RANK = 1D;
    private static final Double UPDATED_RANK = 2D;

    @Autowired
    private NutrientRepository nutrientRepository;

    @Autowired
    private NutrientMapper nutrientMapper;

    @Autowired
    private NutrientService nutrientService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNutrientMockMvc;

    private Nutrient nutrient;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Nutrient createEntity(EntityManager em) {
        Nutrient nutrient = new Nutrient()
            .name(DEFAULT_NAME)
            .unitName(DEFAULT_UNIT_NAME)
            .rank(DEFAULT_RANK);
        return nutrient;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Nutrient createUpdatedEntity(EntityManager em) {
        Nutrient nutrient = new Nutrient()
            .name(UPDATED_NAME)
            .unitName(UPDATED_UNIT_NAME)
            .rank(UPDATED_RANK);
        return nutrient;
    }

    @BeforeEach
    public void initTest() {
        nutrient = createEntity(em);
    }

    @Test
    @Transactional
    public void createNutrient() throws Exception {
        int databaseSizeBeforeCreate = nutrientRepository.findAll().size();
        // Create the Nutrient
        NutrientDTO nutrientDTO = nutrientMapper.toDto(nutrient);
        restNutrientMockMvc.perform(post("/api/nutrients")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(nutrientDTO)))
            .andExpect(status().isCreated());

        // Validate the Nutrient in the database
        List<Nutrient> nutrientList = nutrientRepository.findAll();
        assertThat(nutrientList).hasSize(databaseSizeBeforeCreate + 1);
        Nutrient testNutrient = nutrientList.get(nutrientList.size() - 1);
        assertThat(testNutrient.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testNutrient.getUnitName()).isEqualTo(DEFAULT_UNIT_NAME);
        assertThat(testNutrient.getRank()).isEqualTo(DEFAULT_RANK);
    }

    @Test
    @Transactional
    public void createNutrientWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = nutrientRepository.findAll().size();

        // Create the Nutrient with an existing ID
        nutrient.setId(1L);
        NutrientDTO nutrientDTO = nutrientMapper.toDto(nutrient);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNutrientMockMvc.perform(post("/api/nutrients")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(nutrientDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Nutrient in the database
        List<Nutrient> nutrientList = nutrientRepository.findAll();
        assertThat(nutrientList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllNutrients() throws Exception {
        // Initialize the database
        nutrientRepository.saveAndFlush(nutrient);

        // Get all the nutrientList
        restNutrientMockMvc.perform(get("/api/nutrients?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nutrient.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].unitName").value(hasItem(DEFAULT_UNIT_NAME)))
            .andExpect(jsonPath("$.[*].rank").value(hasItem(DEFAULT_RANK.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getNutrient() throws Exception {
        // Initialize the database
        nutrientRepository.saveAndFlush(nutrient);

        // Get the nutrient
        restNutrientMockMvc.perform(get("/api/nutrients/{id}", nutrient.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(nutrient.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.unitName").value(DEFAULT_UNIT_NAME))
            .andExpect(jsonPath("$.rank").value(DEFAULT_RANK.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingNutrient() throws Exception {
        // Get the nutrient
        restNutrientMockMvc.perform(get("/api/nutrients/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNutrient() throws Exception {
        // Initialize the database
        nutrientRepository.saveAndFlush(nutrient);

        int databaseSizeBeforeUpdate = nutrientRepository.findAll().size();

        // Update the nutrient
        Nutrient updatedNutrient = nutrientRepository.findById(nutrient.getId()).get();
        // Disconnect from session so that the updates on updatedNutrient are not directly saved in db
        em.detach(updatedNutrient);
        updatedNutrient
            .name(UPDATED_NAME)
            .unitName(UPDATED_UNIT_NAME)
            .rank(UPDATED_RANK);
        NutrientDTO nutrientDTO = nutrientMapper.toDto(updatedNutrient);

        restNutrientMockMvc.perform(put("/api/nutrients")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(nutrientDTO)))
            .andExpect(status().isOk());

        // Validate the Nutrient in the database
        List<Nutrient> nutrientList = nutrientRepository.findAll();
        assertThat(nutrientList).hasSize(databaseSizeBeforeUpdate);
        Nutrient testNutrient = nutrientList.get(nutrientList.size() - 1);
        assertThat(testNutrient.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testNutrient.getUnitName()).isEqualTo(UPDATED_UNIT_NAME);
        assertThat(testNutrient.getRank()).isEqualTo(UPDATED_RANK);
    }

    @Test
    @Transactional
    public void updateNonExistingNutrient() throws Exception {
        int databaseSizeBeforeUpdate = nutrientRepository.findAll().size();

        // Create the Nutrient
        NutrientDTO nutrientDTO = nutrientMapper.toDto(nutrient);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNutrientMockMvc.perform(put("/api/nutrients")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(nutrientDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Nutrient in the database
        List<Nutrient> nutrientList = nutrientRepository.findAll();
        assertThat(nutrientList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNutrient() throws Exception {
        // Initialize the database
        nutrientRepository.saveAndFlush(nutrient);

        int databaseSizeBeforeDelete = nutrientRepository.findAll().size();

        // Delete the nutrient
        restNutrientMockMvc.perform(delete("/api/nutrients/{id}", nutrient.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Nutrient> nutrientList = nutrientRepository.findAll();
        assertThat(nutrientList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
