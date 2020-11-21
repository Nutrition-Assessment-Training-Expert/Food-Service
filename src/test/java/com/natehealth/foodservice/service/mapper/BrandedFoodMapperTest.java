package com.natehealth.foodservice.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class BrandedFoodMapperTest {

    private BrandedFoodMapper brandedFoodMapper;

    @BeforeEach
    public void setUp() {
        brandedFoodMapper = new BrandedFoodMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(brandedFoodMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(brandedFoodMapper.fromId(null)).isNull();
    }
}
