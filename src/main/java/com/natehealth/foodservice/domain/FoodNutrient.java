package com.natehealth.foodservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A FoodNutrient.
 */
@Entity
@Table(name = "food_nutrient")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FoodNutrient implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount")
    private Double amount;

    @ManyToOne
    @JsonIgnoreProperties(value = "foodNutrients", allowSetters = true)
    private Nutrient nutrient;

    @ManyToOne
    @JsonIgnoreProperties(value = "foodNutrients", allowSetters = true)
    private Food food;

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

    public FoodNutrient amount(Double amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Nutrient getNutrient() {
        return nutrient;
    }

    public FoodNutrient nutrient(Nutrient nutrient) {
        this.nutrient = nutrient;
        return this;
    }

    public void setNutrient(Nutrient nutrient) {
        this.nutrient = nutrient;
    }

    public Food getFood() {
        return food;
    }

    public FoodNutrient food(Food food) {
        this.food = food;
        return this;
    }

    public void setFood(Food food) {
        this.food = food;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FoodNutrient)) {
            return false;
        }
        return id != null && id.equals(((FoodNutrient) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FoodNutrient{" +
            "id=" + getId() +
            ", amount=" + getAmount() +
            "}";
    }
}
