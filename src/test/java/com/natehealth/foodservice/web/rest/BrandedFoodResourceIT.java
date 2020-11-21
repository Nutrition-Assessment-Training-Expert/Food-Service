package com.natehealth.foodservice.web.rest;

import com.natehealth.foodservice.FoodserviceApp;
import com.natehealth.foodservice.domain.BrandedFood;
import com.natehealth.foodservice.repository.BrandedFoodRepository;
import com.natehealth.foodservice.service.BrandedFoodService;
import com.natehealth.foodservice.service.dto.BrandedFoodDTO;
import com.natehealth.foodservice.service.mapper.BrandedFoodMapper;

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
 * Integration tests for the {@link BrandedFoodResource} REST controller.
 */
@SpringBootTest(classes = FoodserviceApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class BrandedFoodResourceIT {

    private static final String DEFAULT_BRAND_OWNER = "AAAAAAAAAA";
    private static final String UPDATED_BRAND_OWNER = "BBBBBBBBBB";

    private static final String DEFAULT_GTIN_UPC = "AAAAAAAAAA";
    private static final String UPDATED_GTIN_UPC = "BBBBBBBBBB";

    private static final String DEFAULT_INGREDIENTS = "AAAAAAAAAA";
    private static final String UPDATED_INGREDIENTS = "BBBBBBBBBB";

    private static final Double DEFAULT_SERVING_SIZE = 1D;
    private static final Double UPDATED_SERVING_SIZE = 2D;

    private static final String DEFAULT_SERVING_SIZE_UNIT = "AA";
    private static final String UPDATED_SERVING_SIZE_UNIT = "BB";

    private static final String DEFAULT_HOUSEHOLD_SERVING_FULLTEXT = "AAAAAAAAAA";
    private static final String UPDATED_HOUSEHOLD_SERVING_FULLTEXT = "BBBBBBBBBB";

    private static final String DEFAULT_BRANDED_FOOD_CATEGORY = "AAAAAAAAAA";
    private static final String UPDATED_BRANDED_FOOD_CATEGORY = "BBBBBBBBBB";

    @Autowired
    private BrandedFoodRepository brandedFoodRepository;

    @Autowired
    private BrandedFoodMapper brandedFoodMapper;

    @Autowired
    private BrandedFoodService brandedFoodService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBrandedFoodMockMvc;

    private BrandedFood brandedFood;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BrandedFood createEntity(EntityManager em) {
        BrandedFood brandedFood = new BrandedFood()
            .brandOwner(DEFAULT_BRAND_OWNER)
            .gtinUpc(DEFAULT_GTIN_UPC)
            .ingredients(DEFAULT_INGREDIENTS)
            .servingSize(DEFAULT_SERVING_SIZE)
            .servingSizeUnit(DEFAULT_SERVING_SIZE_UNIT)
            .householdServingFulltext(DEFAULT_HOUSEHOLD_SERVING_FULLTEXT)
            .brandedFoodCategory(DEFAULT_BRANDED_FOOD_CATEGORY);
        return brandedFood;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BrandedFood createUpdatedEntity(EntityManager em) {
        BrandedFood brandedFood = new BrandedFood()
            .brandOwner(UPDATED_BRAND_OWNER)
            .gtinUpc(UPDATED_GTIN_UPC)
            .ingredients(UPDATED_INGREDIENTS)
            .servingSize(UPDATED_SERVING_SIZE)
            .servingSizeUnit(UPDATED_SERVING_SIZE_UNIT)
            .householdServingFulltext(UPDATED_HOUSEHOLD_SERVING_FULLTEXT)
            .brandedFoodCategory(UPDATED_BRANDED_FOOD_CATEGORY);
        return brandedFood;
    }

    @BeforeEach
    public void initTest() {
        brandedFood = createEntity(em);
    }

    @Test
    @Transactional
    public void createBrandedFood() throws Exception {
        int databaseSizeBeforeCreate = brandedFoodRepository.findAll().size();
        // Create the BrandedFood
        BrandedFoodDTO brandedFoodDTO = brandedFoodMapper.toDto(brandedFood);
        restBrandedFoodMockMvc.perform(post("/api/branded-foods")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(brandedFoodDTO)))
            .andExpect(status().isCreated());

        // Validate the BrandedFood in the database
        List<BrandedFood> brandedFoodList = brandedFoodRepository.findAll();
        assertThat(brandedFoodList).hasSize(databaseSizeBeforeCreate + 1);
        BrandedFood testBrandedFood = brandedFoodList.get(brandedFoodList.size() - 1);
        assertThat(testBrandedFood.getBrandOwner()).isEqualTo(DEFAULT_BRAND_OWNER);
        assertThat(testBrandedFood.getGtinUpc()).isEqualTo(DEFAULT_GTIN_UPC);
        assertThat(testBrandedFood.getIngredients()).isEqualTo(DEFAULT_INGREDIENTS);
        assertThat(testBrandedFood.getServingSize()).isEqualTo(DEFAULT_SERVING_SIZE);
        assertThat(testBrandedFood.getServingSizeUnit()).isEqualTo(DEFAULT_SERVING_SIZE_UNIT);
        assertThat(testBrandedFood.getHouseholdServingFulltext()).isEqualTo(DEFAULT_HOUSEHOLD_SERVING_FULLTEXT);
        assertThat(testBrandedFood.getBrandedFoodCategory()).isEqualTo(DEFAULT_BRANDED_FOOD_CATEGORY);
    }

    @Test
    @Transactional
    public void createBrandedFoodWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = brandedFoodRepository.findAll().size();

        // Create the BrandedFood with an existing ID
        brandedFood.setId(1L);
        BrandedFoodDTO brandedFoodDTO = brandedFoodMapper.toDto(brandedFood);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBrandedFoodMockMvc.perform(post("/api/branded-foods")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(brandedFoodDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BrandedFood in the database
        List<BrandedFood> brandedFoodList = brandedFoodRepository.findAll();
        assertThat(brandedFoodList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllBrandedFoods() throws Exception {
        // Initialize the database
        brandedFoodRepository.saveAndFlush(brandedFood);

        // Get all the brandedFoodList
        restBrandedFoodMockMvc.perform(get("/api/branded-foods?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(brandedFood.getId().intValue())))
            .andExpect(jsonPath("$.[*].brandOwner").value(hasItem(DEFAULT_BRAND_OWNER)))
            .andExpect(jsonPath("$.[*].gtinUpc").value(hasItem(DEFAULT_GTIN_UPC)))
            .andExpect(jsonPath("$.[*].ingredients").value(hasItem(DEFAULT_INGREDIENTS)))
            .andExpect(jsonPath("$.[*].servingSize").value(hasItem(DEFAULT_SERVING_SIZE.doubleValue())))
            .andExpect(jsonPath("$.[*].servingSizeUnit").value(hasItem(DEFAULT_SERVING_SIZE_UNIT)))
            .andExpect(jsonPath("$.[*].householdServingFulltext").value(hasItem(DEFAULT_HOUSEHOLD_SERVING_FULLTEXT)))
            .andExpect(jsonPath("$.[*].brandedFoodCategory").value(hasItem(DEFAULT_BRANDED_FOOD_CATEGORY)));
    }
    
    @Test
    @Transactional
    public void getBrandedFood() throws Exception {
        // Initialize the database
        brandedFoodRepository.saveAndFlush(brandedFood);

        // Get the brandedFood
        restBrandedFoodMockMvc.perform(get("/api/branded-foods/{id}", brandedFood.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(brandedFood.getId().intValue()))
            .andExpect(jsonPath("$.brandOwner").value(DEFAULT_BRAND_OWNER))
            .andExpect(jsonPath("$.gtinUpc").value(DEFAULT_GTIN_UPC))
            .andExpect(jsonPath("$.ingredients").value(DEFAULT_INGREDIENTS))
            .andExpect(jsonPath("$.servingSize").value(DEFAULT_SERVING_SIZE.doubleValue()))
            .andExpect(jsonPath("$.servingSizeUnit").value(DEFAULT_SERVING_SIZE_UNIT))
            .andExpect(jsonPath("$.householdServingFulltext").value(DEFAULT_HOUSEHOLD_SERVING_FULLTEXT))
            .andExpect(jsonPath("$.brandedFoodCategory").value(DEFAULT_BRANDED_FOOD_CATEGORY));
    }
    @Test
    @Transactional
    public void getNonExistingBrandedFood() throws Exception {
        // Get the brandedFood
        restBrandedFoodMockMvc.perform(get("/api/branded-foods/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBrandedFood() throws Exception {
        // Initialize the database
        brandedFoodRepository.saveAndFlush(brandedFood);

        int databaseSizeBeforeUpdate = brandedFoodRepository.findAll().size();

        // Update the brandedFood
        BrandedFood updatedBrandedFood = brandedFoodRepository.findById(brandedFood.getId()).get();
        // Disconnect from session so that the updates on updatedBrandedFood are not directly saved in db
        em.detach(updatedBrandedFood);
        updatedBrandedFood
            .brandOwner(UPDATED_BRAND_OWNER)
            .gtinUpc(UPDATED_GTIN_UPC)
            .ingredients(UPDATED_INGREDIENTS)
            .servingSize(UPDATED_SERVING_SIZE)
            .servingSizeUnit(UPDATED_SERVING_SIZE_UNIT)
            .householdServingFulltext(UPDATED_HOUSEHOLD_SERVING_FULLTEXT)
            .brandedFoodCategory(UPDATED_BRANDED_FOOD_CATEGORY);
        BrandedFoodDTO brandedFoodDTO = brandedFoodMapper.toDto(updatedBrandedFood);

        restBrandedFoodMockMvc.perform(put("/api/branded-foods")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(brandedFoodDTO)))
            .andExpect(status().isOk());

        // Validate the BrandedFood in the database
        List<BrandedFood> brandedFoodList = brandedFoodRepository.findAll();
        assertThat(brandedFoodList).hasSize(databaseSizeBeforeUpdate);
        BrandedFood testBrandedFood = brandedFoodList.get(brandedFoodList.size() - 1);
        assertThat(testBrandedFood.getBrandOwner()).isEqualTo(UPDATED_BRAND_OWNER);
        assertThat(testBrandedFood.getGtinUpc()).isEqualTo(UPDATED_GTIN_UPC);
        assertThat(testBrandedFood.getIngredients()).isEqualTo(UPDATED_INGREDIENTS);
        assertThat(testBrandedFood.getServingSize()).isEqualTo(UPDATED_SERVING_SIZE);
        assertThat(testBrandedFood.getServingSizeUnit()).isEqualTo(UPDATED_SERVING_SIZE_UNIT);
        assertThat(testBrandedFood.getHouseholdServingFulltext()).isEqualTo(UPDATED_HOUSEHOLD_SERVING_FULLTEXT);
        assertThat(testBrandedFood.getBrandedFoodCategory()).isEqualTo(UPDATED_BRANDED_FOOD_CATEGORY);
    }

    @Test
    @Transactional
    public void updateNonExistingBrandedFood() throws Exception {
        int databaseSizeBeforeUpdate = brandedFoodRepository.findAll().size();

        // Create the BrandedFood
        BrandedFoodDTO brandedFoodDTO = brandedFoodMapper.toDto(brandedFood);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBrandedFoodMockMvc.perform(put("/api/branded-foods")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(brandedFoodDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BrandedFood in the database
        List<BrandedFood> brandedFoodList = brandedFoodRepository.findAll();
        assertThat(brandedFoodList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBrandedFood() throws Exception {
        // Initialize the database
        brandedFoodRepository.saveAndFlush(brandedFood);

        int databaseSizeBeforeDelete = brandedFoodRepository.findAll().size();

        // Delete the brandedFood
        restBrandedFoodMockMvc.perform(delete("/api/branded-foods/{id}", brandedFood.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BrandedFood> brandedFoodList = brandedFoodRepository.findAll();
        assertThat(brandedFoodList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
