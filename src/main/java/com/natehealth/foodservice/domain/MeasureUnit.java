package com.natehealth.foodservice.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A MeasureUnit.
 */
@Entity
@Table(name = "measure_unit")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MeasureUnit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 16)
    @Column(name = "name", length = 16)
    private String name;

    @OneToMany(mappedBy = "measureUnit")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<FoodPortion> foodPortions = new HashSet<>();

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

    public MeasureUnit name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<FoodPortion> getFoodPortions() {
        return foodPortions;
    }

    public MeasureUnit foodPortions(Set<FoodPortion> foodPortions) {
        this.foodPortions = foodPortions;
        return this;
    }

    public MeasureUnit addFoodPortion(FoodPortion foodPortion) {
        this.foodPortions.add(foodPortion);
        foodPortion.setMeasureUnit(this);
        return this;
    }

    public MeasureUnit removeFoodPortion(FoodPortion foodPortion) {
        this.foodPortions.remove(foodPortion);
        foodPortion.setMeasureUnit(null);
        return this;
    }

    public void setFoodPortions(Set<FoodPortion> foodPortions) {
        this.foodPortions = foodPortions;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MeasureUnit)) {
            return false;
        }
        return id != null && id.equals(((MeasureUnit) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MeasureUnit{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
