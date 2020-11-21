package com.natehealth.foodservice.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FoodPortionMapperTest {

    private FoodPortionMapper foodPortionMapper;

    @BeforeEach
    public void setUp() {
        foodPortionMapper = new FoodPortionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(foodPortionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(foodPortionMapper.fromId(null)).isNull();
    }
}
