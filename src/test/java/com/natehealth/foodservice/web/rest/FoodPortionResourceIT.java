package com.natehealth.foodservice.web.rest;

import com.natehealth.foodservice.FoodserviceApp;
import com.natehealth.foodservice.domain.FoodPortion;
import com.natehealth.foodservice.repository.FoodPortionRepository;
import com.natehealth.foodservice.service.FoodPortionService;
import com.natehealth.foodservice.service.dto.FoodPortionDTO;
import com.natehealth.foodservice.service.mapper.FoodPortionMapper;

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
 * Integration tests for the {@link FoodPortionResource} REST controller.
 */
@SpringBootTest(classes = FoodserviceApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class FoodPortionResourceIT {

    private static final Double DEFAULT_AMOUNT = 1D;
    private static final Double UPDATED_AMOUNT = 2D;

    private static final String DEFAULT_PORTION_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_PORTION_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_MODIFIER = "AAAAAAAAAA";
    private static final String UPDATED_MODIFIER = "BBBBBBBBBB";

    private static final Double DEFAULT_GRAM_WEIGHT = 1D;
    private static final Double UPDATED_GRAM_WEIGHT = 2D;

    @Autowired
    private FoodPortionRepository foodPortionRepository;

    @Autowired
    private FoodPortionMapper foodPortionMapper;

    @Autowired
    private FoodPortionService foodPortionService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFoodPortionMockMvc;

    private FoodPortion foodPortion;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FoodPortion createEntity(EntityManager em) {
        FoodPortion foodPortion = new FoodPortion()
            .amount(DEFAULT_AMOUNT)
            .portionDescription(DEFAULT_PORTION_DESCRIPTION)
            .modifier(DEFAULT_MODIFIER)
            .gramWeight(DEFAULT_GRAM_WEIGHT);
        return foodPortion;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FoodPortion createUpdatedEntity(EntityManager em) {
        FoodPortion foodPortion = new FoodPortion()
            .amount(UPDATED_AMOUNT)
            .portionDescription(UPDATED_PORTION_DESCRIPTION)
            .modifier(UPDATED_MODIFIER)
            .gramWeight(UPDATED_GRAM_WEIGHT);
        return foodPortion;
    }

    @BeforeEach
    public void initTest() {
        foodPortion = createEntity(em);
    }

    @Test
    @Transactional
    public void createFoodPortion() throws Exception {
        int databaseSizeBeforeCreate = foodPortionRepository.findAll().size();
        // Create the FoodPortion
        FoodPortionDTO foodPortionDTO = foodPortionMapper.toDto(foodPortion);
        restFoodPortionMockMvc.perform(post("/api/food-portions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(foodPortionDTO)))
            .andExpect(status().isCreated());

        // Validate the FoodPortion in the database
        List<FoodPortion> foodPortionList = foodPortionRepository.findAll();
        assertThat(foodPortionList).hasSize(databaseSizeBeforeCreate + 1);
        FoodPortion testFoodPortion = foodPortionList.get(foodPortionList.size() - 1);
        assertThat(testFoodPortion.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testFoodPortion.getPortionDescription()).isEqualTo(DEFAULT_PORTION_DESCRIPTION);
        assertThat(testFoodPortion.getModifier()).isEqualTo(DEFAULT_MODIFIER);
        assertThat(testFoodPortion.getGramWeight()).isEqualTo(DEFAULT_GRAM_WEIGHT);
    }

    @Test
    @Transactional
    public void createFoodPortionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = foodPortionRepository.findAll().size();

        // Create the FoodPortion with an existing ID
        foodPortion.setId(1L);
        FoodPortionDTO foodPortionDTO = foodPortionMapper.toDto(foodPortion);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFoodPortionMockMvc.perform(post("/api/food-portions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(foodPortionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FoodPortion in the database
        List<FoodPortion> foodPortionList = foodPortionRepository.findAll();
        assertThat(foodPortionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllFoodPortions() throws Exception {
        // Initialize the database
        foodPortionRepository.saveAndFlush(foodPortion);

        // Get all the foodPortionList
        restFoodPortionMockMvc.perform(get("/api/food-portions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(foodPortion.getId().intValue())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].portionDescription").value(hasItem(DEFAULT_PORTION_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].modifier").value(hasItem(DEFAULT_MODIFIER)))
            .andExpect(jsonPath("$.[*].gramWeight").value(hasItem(DEFAULT_GRAM_WEIGHT.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getFoodPortion() throws Exception {
        // Initialize the database
        foodPortionRepository.saveAndFlush(foodPortion);

        // Get the foodPortion
        restFoodPortionMockMvc.perform(get("/api/food-portions/{id}", foodPortion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(foodPortion.getId().intValue()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.portionDescription").value(DEFAULT_PORTION_DESCRIPTION))
            .andExpect(jsonPath("$.modifier").value(DEFAULT_MODIFIER))
            .andExpect(jsonPath("$.gramWeight").value(DEFAULT_GRAM_WEIGHT.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingFoodPortion() throws Exception {
        // Get the foodPortion
        restFoodPortionMockMvc.perform(get("/api/food-portions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFoodPortion() throws Exception {
        // Initialize the database
        foodPortionRepository.saveAndFlush(foodPortion);

        int databaseSizeBeforeUpdate = foodPortionRepository.findAll().size();

        // Update the foodPortion
        FoodPortion updatedFoodPortion = foodPortionRepository.findById(foodPortion.getId()).get();
        // Disconnect from session so that the updates on updatedFoodPortion are not directly saved in db
        em.detach(updatedFoodPortion);
        updatedFoodPortion
            .amount(UPDATED_AMOUNT)
            .portionDescription(UPDATED_PORTION_DESCRIPTION)
            .modifier(UPDATED_MODIFIER)
            .gramWeight(UPDATED_GRAM_WEIGHT);
        FoodPortionDTO foodPortionDTO = foodPortionMapper.toDto(updatedFoodPortion);

        restFoodPortionMockMvc.perform(put("/api/food-portions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(foodPortionDTO)))
            .andExpect(status().isOk());

        // Validate the FoodPortion in the database
        List<FoodPortion> foodPortionList = foodPortionRepository.findAll();
        assertThat(foodPortionList).hasSize(databaseSizeBeforeUpdate);
        FoodPortion testFoodPortion = foodPortionList.get(foodPortionList.size() - 1);
        assertThat(testFoodPortion.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testFoodPortion.getPortionDescription()).isEqualTo(UPDATED_PORTION_DESCRIPTION);
        assertThat(testFoodPortion.getModifier()).isEqualTo(UPDATED_MODIFIER);
        assertThat(testFoodPortion.getGramWeight()).isEqualTo(UPDATED_GRAM_WEIGHT);
    }

    @Test
    @Transactional
    public void updateNonExistingFoodPortion() throws Exception {
        int databaseSizeBeforeUpdate = foodPortionRepository.findAll().size();

        // Create the FoodPortion
        FoodPortionDTO foodPortionDTO = foodPortionMapper.toDto(foodPortion);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFoodPortionMockMvc.perform(put("/api/food-portions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(foodPortionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FoodPortion in the database
        List<FoodPortion> foodPortionList = foodPortionRepository.findAll();
        assertThat(foodPortionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFoodPortion() throws Exception {
        // Initialize the database
        foodPortionRepository.saveAndFlush(foodPortion);

        int databaseSizeBeforeDelete = foodPortionRepository.findAll().size();

        // Delete the foodPortion
        restFoodPortionMockMvc.perform(delete("/api/food-portions/{id}", foodPortion.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FoodPortion> foodPortionList = foodPortionRepository.findAll();
        assertThat(foodPortionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
