package com.natehealth.foodservice.web.rest;

import com.natehealth.foodservice.FoodserviceApp;
import com.natehealth.foodservice.domain.FoodCategory;
import com.natehealth.foodservice.repository.FoodCategoryRepository;
import com.natehealth.foodservice.service.FoodCategoryService;
import com.natehealth.foodservice.service.dto.FoodCategoryDTO;
import com.natehealth.foodservice.service.mapper.FoodCategoryMapper;

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
 * Integration tests for the {@link FoodCategoryResource} REST controller.
 */
@SpringBootTest(classes = FoodserviceApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class FoodCategoryResourceIT {

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private FoodCategoryRepository foodCategoryRepository;

    @Autowired
    private FoodCategoryMapper foodCategoryMapper;

    @Autowired
    private FoodCategoryService foodCategoryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFoodCategoryMockMvc;

    private FoodCategory foodCategory;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FoodCategory createEntity(EntityManager em) {
        FoodCategory foodCategory = new FoodCategory()
            .description(DEFAULT_DESCRIPTION);
        return foodCategory;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FoodCategory createUpdatedEntity(EntityManager em) {
        FoodCategory foodCategory = new FoodCategory()
            .description(UPDATED_DESCRIPTION);
        return foodCategory;
    }

    @BeforeEach
    public void initTest() {
        foodCategory = createEntity(em);
    }

    @Test
    @Transactional
    public void createFoodCategory() throws Exception {
        int databaseSizeBeforeCreate = foodCategoryRepository.findAll().size();
        // Create the FoodCategory
        FoodCategoryDTO foodCategoryDTO = foodCategoryMapper.toDto(foodCategory);
        restFoodCategoryMockMvc.perform(post("/api/food-categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(foodCategoryDTO)))
            .andExpect(status().isCreated());

        // Validate the FoodCategory in the database
        List<FoodCategory> foodCategoryList = foodCategoryRepository.findAll();
        assertThat(foodCategoryList).hasSize(databaseSizeBeforeCreate + 1);
        FoodCategory testFoodCategory = foodCategoryList.get(foodCategoryList.size() - 1);
        assertThat(testFoodCategory.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createFoodCategoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = foodCategoryRepository.findAll().size();

        // Create the FoodCategory with an existing ID
        foodCategory.setId(1L);
        FoodCategoryDTO foodCategoryDTO = foodCategoryMapper.toDto(foodCategory);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFoodCategoryMockMvc.perform(post("/api/food-categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(foodCategoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FoodCategory in the database
        List<FoodCategory> foodCategoryList = foodCategoryRepository.findAll();
        assertThat(foodCategoryList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllFoodCategories() throws Exception {
        // Initialize the database
        foodCategoryRepository.saveAndFlush(foodCategory);

        // Get all the foodCategoryList
        restFoodCategoryMockMvc.perform(get("/api/food-categories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(foodCategory.getId().intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getFoodCategory() throws Exception {
        // Initialize the database
        foodCategoryRepository.saveAndFlush(foodCategory);

        // Get the foodCategory
        restFoodCategoryMockMvc.perform(get("/api/food-categories/{id}", foodCategory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(foodCategory.getId().intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }
    @Test
    @Transactional
    public void getNonExistingFoodCategory() throws Exception {
        // Get the foodCategory
        restFoodCategoryMockMvc.perform(get("/api/food-categories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFoodCategory() throws Exception {
        // Initialize the database
        foodCategoryRepository.saveAndFlush(foodCategory);

        int databaseSizeBeforeUpdate = foodCategoryRepository.findAll().size();

        // Update the foodCategory
        FoodCategory updatedFoodCategory = foodCategoryRepository.findById(foodCategory.getId()).get();
        // Disconnect from session so that the updates on updatedFoodCategory are not directly saved in db
        em.detach(updatedFoodCategory);
        updatedFoodCategory
            .description(UPDATED_DESCRIPTION);
        FoodCategoryDTO foodCategoryDTO = foodCategoryMapper.toDto(updatedFoodCategory);

        restFoodCategoryMockMvc.perform(put("/api/food-categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(foodCategoryDTO)))
            .andExpect(status().isOk());

        // Validate the FoodCategory in the database
        List<FoodCategory> foodCategoryList = foodCategoryRepository.findAll();
        assertThat(foodCategoryList).hasSize(databaseSizeBeforeUpdate);
        FoodCategory testFoodCategory = foodCategoryList.get(foodCategoryList.size() - 1);
        assertThat(testFoodCategory.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingFoodCategory() throws Exception {
        int databaseSizeBeforeUpdate = foodCategoryRepository.findAll().size();

        // Create the FoodCategory
        FoodCategoryDTO foodCategoryDTO = foodCategoryMapper.toDto(foodCategory);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFoodCategoryMockMvc.perform(put("/api/food-categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(foodCategoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FoodCategory in the database
        List<FoodCategory> foodCategoryList = foodCategoryRepository.findAll();
        assertThat(foodCategoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFoodCategory() throws Exception {
        // Initialize the database
        foodCategoryRepository.saveAndFlush(foodCategory);

        int databaseSizeBeforeDelete = foodCategoryRepository.findAll().size();

        // Delete the foodCategory
        restFoodCategoryMockMvc.perform(delete("/api/food-categories/{id}", foodCategory.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FoodCategory> foodCategoryList = foodCategoryRepository.findAll();
        assertThat(foodCategoryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
