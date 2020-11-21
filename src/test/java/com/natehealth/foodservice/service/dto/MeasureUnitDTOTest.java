package com.natehealth.foodservice.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.natehealth.foodservice.web.rest.TestUtil;

public class MeasureUnitDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MeasureUnitDTO.class);
        MeasureUnitDTO measureUnitDTO1 = new MeasureUnitDTO();
        measureUnitDTO1.setId(1L);
        MeasureUnitDTO measureUnitDTO2 = new MeasureUnitDTO();
        assertThat(measureUnitDTO1).isNotEqualTo(measureUnitDTO2);
        measureUnitDTO2.setId(measureUnitDTO1.getId());
        assertThat(measureUnitDTO1).isEqualTo(measureUnitDTO2);
        measureUnitDTO2.setId(2L);
        assertThat(measureUnitDTO1).isNotEqualTo(measureUnitDTO2);
        measureUnitDTO1.setId(null);
        assertThat(measureUnitDTO1).isNotEqualTo(measureUnitDTO2);
    }
}
