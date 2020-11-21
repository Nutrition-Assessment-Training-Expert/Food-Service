package com.natehealth.foodservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Food.
 */
@Entity
@Table(name = "food")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Food implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    @Column(name = "description", length = 255)
    private String description;

    @OneToMany(mappedBy = "food")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<FoodPortion> foodPoritons = new HashSet<>();

    @OneToMany(mappedBy = "food")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<FoodNutrient> foodNutrients = new HashSet<>();

    @OneToOne(mappedBy = "food")
    @JsonIgnore
    private BrandedFood brandedFood;

    @ManyToOne
    @JsonIgnoreProperties(value = "foods", allowSetters = true)
    private FoodCategory foodCategory;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public Food description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<FoodPortion> getFoodPoritons() {
        return foodPoritons;
    }

    public Food foodPoritons(Set<FoodPortion> foodPortions) {
        this.foodPoritons = foodPortions;
        return this;
    }

    public Food addFoodPoriton(FoodPortion foodPortion) {
        this.foodPoritons.add(foodPortion);
        foodPortion.setFood(this);
        return this;
    }

    public Food removeFoodPoriton(FoodPortion foodPortion) {
        this.foodPoritons.remove(foodPortion);
        foodPortion.setFood(null);
        return this;
    }

    public void setFoodPoritons(Set<FoodPortion> foodPortions) {
        this.foodPoritons = foodPortions;
    }

    public Set<FoodNutrient> getFoodNutrients() {
        return foodNutrients;
    }

    public Food foodNutrients(Set<FoodNutrient> foodNutrients) {
        this.foodNutrients = foodNutrients;
        return this;
    }

    public Food addFoodNutrient(FoodNutrient foodNutrient) {
        this.foodNutrients.add(foodNutrient);
        foodNutrient.setFood(this);
        return this;
    }

    public Food removeFoodNutrient(FoodNutrient foodNutrient) {
        this.foodNutrients.remove(foodNutrient);
        foodNutrient.setFood(null);
        return this;
    }

    public void setFoodNutrients(Set<FoodNutrient> foodNutrients) {
        this.foodNutrients = foodNutrients;
    }

    public BrandedFood getBrandedFood() {
        return brandedFood;
    }

    public Food brandedFood(BrandedFood brandedFood) {
        this.brandedFood = brandedFood;
        return this;
    }

    public void setBrandedFood(BrandedFood brandedFood) {
        this.brandedFood = brandedFood;
    }

    public FoodCategory getFoodCategory() {
        return foodCategory;
    }

    public Food foodCategory(FoodCategory foodCategory) {
        this.foodCategory = foodCategory;
        return this;
    }

    public void setFoodCategory(FoodCategory foodCategory) {
        this.foodCategory = foodCategory;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Food)) {
            return false;
        }
        return id != null && id.equals(((Food) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Food{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
