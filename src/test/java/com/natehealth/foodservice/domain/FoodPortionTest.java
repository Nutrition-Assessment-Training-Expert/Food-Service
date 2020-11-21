package com.natehealth.foodservice.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.natehealth.foodservice.web.rest.TestUtil;

public class FoodPortionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FoodPortion.class);
        FoodPortion foodPortion1 = new FoodPortion();
        foodPortion1.setId(1L);
        FoodPortion foodPortion2 = new FoodPortion();
        foodPortion2.setId(foodPortion1.getId());
        assertThat(foodPortion1).isEqualTo(foodPortion2);
        foodPortion2.setId(2L);
        assertThat(foodPortion1).isNotEqualTo(foodPortion2);
        foodPortion1.setId(null);
        assertThat(foodPortion1).isNotEqualTo(foodPortion2);
    }
}
