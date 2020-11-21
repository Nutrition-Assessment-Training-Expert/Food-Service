package com.natehealth.foodservice.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FoodNutrientMapperTest {

    private FoodNutrientMapper foodNutrientMapper;

    @BeforeEach
    public void setUp() {
        foodNutrientMapper = new FoodNutrientMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(foodNutrientMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(foodNutrientMapper.fromId(null)).isNull();
    }
}
