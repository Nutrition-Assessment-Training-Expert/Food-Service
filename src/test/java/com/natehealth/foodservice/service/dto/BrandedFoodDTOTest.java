package com.natehealth.foodservice.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.natehealth.foodservice.web.rest.TestUtil;

public class BrandedFoodDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BrandedFoodDTO.class);
        BrandedFoodDTO brandedFoodDTO1 = new BrandedFoodDTO();
        brandedFoodDTO1.setId(1L);
        BrandedFoodDTO brandedFoodDTO2 = new BrandedFoodDTO();
        assertThat(brandedFoodDTO1).isNotEqualTo(brandedFoodDTO2);
        brandedFoodDTO2.setId(brandedFoodDTO1.getId());
        assertThat(brandedFoodDTO1).isEqualTo(brandedFoodDTO2);
        brandedFoodDTO2.setId(2L);
        assertThat(brandedFoodDTO1).isNotEqualTo(brandedFoodDTO2);
        brandedFoodDTO1.setId(null);
        assertThat(brandedFoodDTO1).isNotEqualTo(brandedFoodDTO2);
    }
}
