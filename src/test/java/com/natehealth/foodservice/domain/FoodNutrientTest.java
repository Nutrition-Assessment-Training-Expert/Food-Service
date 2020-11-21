package com.natehealth.foodservice.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.natehealth.foodservice.web.rest.TestUtil;

public class FoodNutrientTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FoodNutrient.class);
        FoodNutrient foodNutrient1 = new FoodNutrient();
        foodNutrient1.setId(1L);
        FoodNutrient foodNutrient2 = new FoodNutrient();
        foodNutrient2.setId(foodNutrient1.getId());
        assertThat(foodNutrient1).isEqualTo(foodNutrient2);
        foodNutrient2.setId(2L);
        assertThat(foodNutrient1).isNotEqualTo(foodNutrient2);
        foodNutrient1.setId(null);
        assertThat(foodNutrient1).isNotEqualTo(foodNutrient2);
    }
}
