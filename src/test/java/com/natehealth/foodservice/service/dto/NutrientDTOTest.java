package com.natehealth.foodservice.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.natehealth.foodservice.web.rest.TestUtil;

public class NutrientDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NutrientDTO.class);
        NutrientDTO nutrientDTO1 = new NutrientDTO();
        nutrientDTO1.setId(1L);
        NutrientDTO nutrientDTO2 = new NutrientDTO();
        assertThat(nutrientDTO1).isNotEqualTo(nutrientDTO2);
        nutrientDTO2.setId(nutrientDTO1.getId());
        assertThat(nutrientDTO1).isEqualTo(nutrientDTO2);
        nutrientDTO2.setId(2L);
        assertThat(nutrientDTO1).isNotEqualTo(nutrientDTO2);
        nutrientDTO1.setId(null);
        assertThat(nutrientDTO1).isNotEqualTo(nutrientDTO2);
    }
}
