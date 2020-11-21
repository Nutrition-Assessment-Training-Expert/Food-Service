package com.natehealth.foodservice.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class NutrientMapperTest {

    private NutrientMapper nutrientMapper;

    @BeforeEach
    public void setUp() {
        nutrientMapper = new NutrientMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(nutrientMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(nutrientMapper.fromId(null)).isNull();
    }
}
