package com.natehealth.foodservice.web.rest;

import com.natehealth.foodservice.FoodserviceApp;
import com.natehealth.foodservice.domain.FoodNutrient;
import com.natehealth.foodservice.repository.FoodNutrientRepository;
import com.natehealth.foodservice.service.FoodNutrientService;
import com.natehealth.foodservice.service.dto.FoodNutrientDTO;
import com.natehealth.foodservice.service.mapper.FoodNutrientMapper;

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
 * Integration tests for the {@link FoodNutrientResource} REST controller.
 */
@SpringBootTest(classes = FoodserviceApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class FoodNutrientResourceIT {

    private static final Double DEFAULT_AMOUNT = 1D;
    private static final Double UPDATED_AMOUNT = 2D;

    @Autowired
    private FoodNutrientRepository foodNutrientRepository;

    @Autowired
    private FoodNutrientMapper foodNutrientMapper;

    @Autowired
    private FoodNutrientService foodNutrientService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFoodNutrientMockMvc;

    private FoodNutrient foodNutrient;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FoodNutrient createEntity(EntityManager em) {
        FoodNutrient foodNutrient = new FoodNutrient()
            .amount(DEFAULT_AMOUNT);
        return foodNutrient;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FoodNutrient createUpdatedEntity(EntityManager em) {
        FoodNutrient foodNutrient = new FoodNutrient()
            .amount(UPDATED_AMOUNT);
        return foodNutrient;
    }

    @BeforeEach
    public void initTest() {
        foodNutrient = createEntity(em);
    }

    @Test
    @Transactional
    public void createFoodNutrient() throws Exception {
        int databaseSizeBeforeCreate = foodNutrientRepository.findAll().size();
        // Create the FoodNutrient
        FoodNutrientDTO foodNutrientDTO = foodNutrientMapper.toDto(foodNutrient);
        restFoodNutrientMockMvc.perform(post("/api/food-nutrients")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(foodNutrientDTO)))
            .andExpect(status().isCreated());

        // Validate the FoodNutrient in the database
        List<FoodNutrient> foodNutrientList = foodNutrientRepository.findAll();
        assertThat(foodNutrientList).hasSize(databaseSizeBeforeCreate + 1);
        FoodNutrient testFoodNutrient = foodNutrientList.get(foodNutrientList.size() - 1);
        assertThat(testFoodNutrient.getAmount()).isEqualTo(DEFAULT_AMOUNT);
    }

    @Test
    @Transactional
    public void createFoodNutrientWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = foodNutrientRepository.findAll().size();

        // Create the FoodNutrient with an existing ID
        foodNutrient.setId(1L);
        FoodNutrientDTO foodNutrientDTO = foodNutrientMapper.toDto(foodNutrient);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFoodNutrientMockMvc.perform(post("/api/food-nutrients")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(foodNutrientDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FoodNutrient in the database
        List<FoodNutrient> foodNutrientList = foodNutrientRepository.findAll();
        assertThat(foodNutrientList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllFoodNutrients() throws Exception {
        // Initialize the database
        foodNutrientRepository.saveAndFlush(foodNutrient);

        // Get all the foodNutrientList
        restFoodNutrientMockMvc.perform(get("/api/food-nutrients?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(foodNutrient.getId().intValue())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getFoodNutrient() throws Exception {
        // Initialize the database
        foodNutrientRepository.saveAndFlush(foodNutrient);

        // Get the foodNutrient
        restFoodNutrientMockMvc.perform(get("/api/food-nutrients/{id}", foodNutrient.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(foodNutrient.getId().intValue()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingFoodNutrient() throws Exception {
        // Get the foodNutrient
        restFoodNutrientMockMvc.perform(get("/api/food-nutrients/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFoodNutrient() throws Exception {
        // Initialize the database
        foodNutrientRepository.saveAndFlush(foodNutrient);

        int databaseSizeBeforeUpdate = foodNutrientRepository.findAll().size();

        // Update the foodNutrient
        FoodNutrient updatedFoodNutrient = foodNutrientRepository.findById(foodNutrient.getId()).get();
        // Disconnect from session so that the updates on updatedFoodNutrient are not directly saved in db
        em.detach(updatedFoodNutrient);
        updatedFoodNutrient
            .amount(UPDATED_AMOUNT);
        FoodNutrientDTO foodNutrientDTO = foodNutrientMapper.toDto(updatedFoodNutrient);

        restFoodNutrientMockMvc.perform(put("/api/food-nutrients")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(foodNutrientDTO)))
            .andExpect(status().isOk());

        // Validate the FoodNutrient in the database
        List<FoodNutrient> foodNutrientList = foodNutrientRepository.findAll();
        assertThat(foodNutrientList).hasSize(databaseSizeBeforeUpdate);
        FoodNutrient testFoodNutrient = foodNutrientList.get(foodNutrientList.size() - 1);
        assertThat(testFoodNutrient.getAmount()).isEqualTo(UPDATED_AMOUNT);
    }

    @Test
    @Transactional
    public void updateNonExistingFoodNutrient() throws Exception {
        int databaseSizeBeforeUpdate = foodNutrientRepository.findAll().size();

        // Create the FoodNutrient
        FoodNutrientDTO foodNutrientDTO = foodNutrientMapper.toDto(foodNutrient);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFoodNutrientMockMvc.perform(put("/api/food-nutrients")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(foodNutrientDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FoodNutrient in the database
        List<FoodNutrient> foodNutrientList = foodNutrientRepository.findAll();
        assertThat(foodNutrientList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFoodNutrient() throws Exception {
        // Initialize the database
        foodNutrientRepository.saveAndFlush(foodNutrient);

        int databaseSizeBeforeDelete = foodNutrientRepository.findAll().size();

        // Delete the foodNutrient
        restFoodNutrientMockMvc.perform(delete("/api/food-nutrients/{id}", foodNutrient.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FoodNutrient> foodNutrientList = foodNutrientRepository.findAll();
        assertThat(foodNutrientList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
