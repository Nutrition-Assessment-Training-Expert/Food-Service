package com.natehealth.foodservice.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.natehealth.foodservice.web.rest.TestUtil;

public class MeasureUnitTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MeasureUnit.class);
        MeasureUnit measureUnit1 = new MeasureUnit();
        measureUnit1.setId(1L);
        MeasureUnit measureUnit2 = new MeasureUnit();
        measureUnit2.setId(measureUnit1.getId());
        assertThat(measureUnit1).isEqualTo(measureUnit2);
        measureUnit2.setId(2L);
        assertThat(measureUnit1).isNotEqualTo(measureUnit2);
        measureUnit1.setId(null);
        assertThat(measureUnit1).isNotEqualTo(measureUnit2);
    }
}
