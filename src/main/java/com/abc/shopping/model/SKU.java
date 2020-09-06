package com.abc.shopping.model;

import java.util.Objects;

public class SKU {

    private String name;
    private Double cost;

    public SKU(String name, Double cost) {
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public Double getCost() {
        return cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SKU sku = (SKU) o;
        return getName().equals(sku.getName()) &&
                getCost().equals(sku.getCost());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getCost());
    }
}
