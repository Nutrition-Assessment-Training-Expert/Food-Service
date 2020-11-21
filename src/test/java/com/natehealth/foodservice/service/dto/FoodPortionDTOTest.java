package com.natehealth.foodservice.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.natehealth.foodservice.web.rest.TestUtil;

public class FoodPortionDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FoodPortionDTO.class);
        FoodPortionDTO foodPortionDTO1 = new FoodPortionDTO();
        foodPortionDTO1.setId(1L);
        FoodPortionDTO foodPortionDTO2 = new FoodPortionDTO();
        assertThat(foodPortionDTO1).isNotEqualTo(foodPortionDTO2);
        foodPortionDTO2.setId(foodPortionDTO1.getId());
        assertThat(foodPortionDTO1).isEqualTo(foodPortionDTO2);
        foodPortionDTO2.setId(2L);
        assertThat(foodPortionDTO1).isNotEqualTo(foodPortionDTO2);
        foodPortionDTO1.setId(null);
        assertThat(foodPortionDTO1).isNotEqualTo(foodPortionDTO2);
    }
}
