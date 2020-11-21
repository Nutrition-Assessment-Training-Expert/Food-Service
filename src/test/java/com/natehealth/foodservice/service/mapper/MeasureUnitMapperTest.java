package com.natehealth.foodservice.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MeasureUnitMapperTest {

    private MeasureUnitMapper measureUnitMapper;

    @BeforeEach
    public void setUp() {
        measureUnitMapper = new MeasureUnitMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(measureUnitMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(measureUnitMapper.fromId(null)).isNull();
    }
}
