package com.natehealth.foodservice.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A FoodCategory.
 */
@Entity
@Table(name = "food_category")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FoodCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 35)
    @Column(name = "description", length = 35)
    private String description;

    @OneToMany(mappedBy = "foodCategory")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Food> foods = new HashSet<>();

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

    public FoodCategory description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Food> getFoods() {
        return foods;
    }

    public FoodCategory foods(Set<Food> foods) {
        this.foods = foods;
        return this;
    }

    public FoodCategory addFood(Food food) {
        this.foods.add(food);
        food.setFoodCategory(this);
        return this;
    }

    public FoodCategory removeFood(Food food) {
        this.foods.remove(food);
        food.setFoodCategory(null);
        return this;
    }

    public void setFoods(Set<Food> foods) {
        this.foods = foods;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FoodCategory)) {
            return false;
        }
        return id != null && id.equals(((FoodCategory) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FoodCategory{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
