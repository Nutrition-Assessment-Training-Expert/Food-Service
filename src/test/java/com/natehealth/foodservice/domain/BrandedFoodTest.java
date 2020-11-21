package com.natehealth.foodservice.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.natehealth.foodservice.web.rest.TestUtil;

public class BrandedFoodTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BrandedFood.class);
        BrandedFood brandedFood1 = new BrandedFood();
        brandedFood1.setId(1L);
        BrandedFood brandedFood2 = new BrandedFood();
        brandedFood2.setId(brandedFood1.getId());
        assertThat(brandedFood1).isEqualTo(brandedFood2);
        brandedFood2.setId(2L);
        assertThat(brandedFood1).isNotEqualTo(brandedFood2);
        brandedFood1.setId(null);
        assertThat(brandedFood1).isNotEqualTo(brandedFood2);
    }
}
