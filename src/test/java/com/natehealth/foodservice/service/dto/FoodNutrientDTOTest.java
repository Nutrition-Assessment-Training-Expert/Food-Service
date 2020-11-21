package com.natehealth.foodservice.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.natehealth.foodservice.web.rest.TestUtil;

public class FoodNutrientDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FoodNutrientDTO.class);
        FoodNutrientDTO foodNutrientDTO1 = new FoodNutrientDTO();
        foodNutrientDTO1.setId(1L);
        FoodNutrientDTO foodNutrientDTO2 = new FoodNutrientDTO();
        assertThat(foodNutrientDTO1).isNotEqualTo(foodNutrientDTO2);
        foodNutrientDTO2.setId(foodNutrientDTO1.getId());
        assertThat(foodNutrientDTO1).isEqualTo(foodNutrientDTO2);
        foodNutrientDTO2.setId(2L);
        assertThat(foodNutrientDTO1).isNotEqualTo(foodNutrientDTO2);
        foodNutrientDTO1.setId(null);
        assertThat(foodNutrientDTO1).isNotEqualTo(foodNutrientDTO2);
    }
}
