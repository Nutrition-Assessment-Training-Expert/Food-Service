package com.natehealth.foodservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A FoodPortion.
 */
@Entity
@Table(name = "food_portion")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FoodPortion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount")
    private Double amount;

    @Size(max = 255)
    @Column(name = "portion_description", length = 255)
    private String portionDescription;

    @Size(max = 255)
    @Column(name = "modifier", length = 255)
    private String modifier;

    @Column(name = "gram_weight")
    private Double gramWeight;

    @ManyToOne
    @JsonIgnoreProperties(value = "foodPoritons", allowSetters = true)
    private Food food;

    @ManyToOne
    @JsonIgnoreProperties(value = "foodPortions", allowSetters = true)
    private MeasureUnit measureUnit;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public FoodPortion amount(Double amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getPortionDescription() {
        return portionDescription;
    }

    public FoodPortion portionDescription(String portionDescription) {
        this.portionDescription = portionDescription;
        return this;
    }

    public void setPortionDescription(String portionDescription) {
        this.portionDescription = portionDescription;
    }

    public String getModifier() {
        return modifier;
    }

    public FoodPortion modifier(String modifier) {
        this.modifier = modifier;
        return this;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Double getGramWeight() {
        return gramWeight;
    }

    public FoodPortion gramWeight(Double gramWeight) {
        this.gramWeight = gramWeight;
        return this;
    }

    public void setGramWeight(Double gramWeight) {
        this.gramWeight = gramWeight;
    }

    public Food getFood() {
        return food;
    }

    public FoodPortion food(Food food) {
        this.food = food;
        return this;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public MeasureUnit getMeasureUnit() {
        return measureUnit;
    }

    public FoodPortion measureUnit(MeasureUnit measureUnit) {
        this.measureUnit = measureUnit;
        return this;
    }

    public void setMeasureUnit(MeasureUnit measureUnit) {
        this.measureUnit = measureUnit;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FoodPortion)) {
            return false;
        }
        return id != null && id.equals(((FoodPortion) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FoodPortion{" +
            "id=" + getId() +
            ", amount=" + getAmount() +
            ", portionDescription='" + getPortionDescription() + "'" +
            ", modifier='" + getModifier() + "'" +
            ", gramWeight=" + getGramWeight() +
            "}";
    }
}
