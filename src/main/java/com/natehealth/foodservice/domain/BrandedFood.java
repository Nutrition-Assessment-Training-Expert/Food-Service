package com.natehealth.foodservice.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A BrandedFood.
 */
@Entity
@Table(name = "branded_food")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BrandedFood implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 78)
    @Column(name = "brand_owner", length = 78)
    private String brandOwner;

    @Size(max = 15)
    @Column(name = "gtin_upc", length = 15)
    private String gtinUpc;

    @Size(max = 4000)
    @Column(name = "ingredients", length = 4000)
    private String ingredients;

    @Column(name = "serving_size")
    private Double servingSize;

    @Size(max = 2)
    @Column(name = "serving_size_unit", length = 2)
    private String servingSizeUnit;

    @Size(max = 174)
    @Column(name = "household_serving_fulltext", length = 174)
    private String householdServingFulltext;

    @Size(max = 72)
    @Column(name = "branded_food_category", length = 72)
    private String brandedFoodCategory;

    @OneToOne
    @JoinColumn(unique = true)
    private Food food;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrandOwner() {
        return brandOwner;
    }

    public BrandedFood brandOwner(String brandOwner) {
        this.brandOwner = brandOwner;
        return this;
    }

    public void setBrandOwner(String brandOwner) {
        this.brandOwner = brandOwner;
    }

    public String getGtinUpc() {
        return gtinUpc;
    }

    public BrandedFood gtinUpc(String gtinUpc) {
        this.gtinUpc = gtinUpc;
        return this;
    }

    public void setGtinUpc(String gtinUpc) {
        this.gtinUpc = gtinUpc;
    }

    public String getIngredients() {
        return ingredients;
    }

    public BrandedFood ingredients(String ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public Double getServingSize() {
        return servingSize;
    }

    public BrandedFood servingSize(Double servingSize) {
        this.servingSize = servingSize;
        return this;
    }

    public void setServingSize(Double servingSize) {
        this.servingSize = servingSize;
    }

    public String getServingSizeUnit() {
        return servingSizeUnit;
    }

    public BrandedFood servingSizeUnit(String servingSizeUnit) {
        this.servingSizeUnit = servingSizeUnit;
        return this;
    }

    public void setServingSizeUnit(String servingSizeUnit) {
        this.servingSizeUnit = servingSizeUnit;
    }

    public String getHouseholdServingFulltext() {
        return householdServingFulltext;
    }

    public BrandedFood householdServingFulltext(String householdServingFulltext) {
        this.householdServingFulltext = householdServingFulltext;
        return this;
    }

    public void setHouseholdServingFulltext(String householdServingFulltext) {
        this.householdServingFulltext = householdServingFulltext;
    }

    public String getBrandedFoodCategory() {
        return brandedFoodCategory;
    }

    public BrandedFood brandedFoodCategory(String brandedFoodCategory) {
        this.brandedFoodCategory = brandedFoodCategory;
        return this;
    }

    public void setBrandedFoodCategory(String brandedFoodCategory) {
        this.brandedFoodCategory = brandedFoodCategory;
    }

    public Food getFood() {
        return food;
    }

    public BrandedFood food(Food food) {
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
        if (!(o instanceof BrandedFood)) {
            return false;
        }
        return id != null && id.equals(((BrandedFood) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BrandedFood{" +
            "id=" + getId() +
            ", brandOwner='" + getBrandOwner() + "'" +
            ", gtinUpc='" + getGtinUpc() + "'" +
            ", ingredients='" + getIngredients() + "'" +
            ", servingSize=" + getServingSize() +
            ", servingSizeUnit='" + getServingSizeUnit() + "'" +
            ", householdServingFulltext='" + getHouseholdServingFulltext() + "'" +
            ", brandedFoodCategory='" + getBrandedFoodCategory() + "'" +
            "}";
    }
}
