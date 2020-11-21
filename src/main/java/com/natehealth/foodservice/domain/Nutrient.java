package com.natehealth.foodservice.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Nutrient.
 */
@Entity
@Table(name = "nutrient")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Nutrient implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 40)
    @Column(name = "name", length = 40)
    private String name;

    @Size(max = 6)
    @Column(name = "unit_name", length = 6)
    private String unitName;

    @Column(name = "jhi_rank")
    private Double rank;

    @OneToMany(mappedBy = "nutrient")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<FoodNutrient> foodNutrients = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Nutrient name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnitName() {
        return unitName;
    }

    public Nutrient unitName(String unitName) {
        this.unitName = unitName;
        return this;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Double getRank() {
        return rank;
    }

    public Nutrient rank(Double rank) {
        this.rank = rank;
        return this;
    }

    public void setRank(Double rank) {
        this.rank = rank;
    }

    public Set<FoodNutrient> getFoodNutrients() {
        return foodNutrients;
    }

    public Nutrient foodNutrients(Set<FoodNutrient> foodNutrients) {
        this.foodNutrients = foodNutrients;
        return this;
    }

    public Nutrient addFoodNutrient(FoodNutrient foodNutrient) {
        this.foodNutrients.add(foodNutrient);
        foodNutrient.setNutrient(this);
        return this;
    }

    public Nutrient removeFoodNutrient(FoodNutrient foodNutrient) {
        this.foodNutrients.remove(foodNutrient);
        foodNutrient.setNutrient(null);
        return this;
    }

    public void setFoodNutrients(Set<FoodNutrient> foodNutrients) {
        this.foodNutrients = foodNutrients;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Nutrient)) {
            return false;
        }
        return id != null && id.equals(((Nutrient) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Nutrient{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", unitName='" + getUnitName() + "'" +
            ", rank=" + getRank() +
            "}";
    }
}
