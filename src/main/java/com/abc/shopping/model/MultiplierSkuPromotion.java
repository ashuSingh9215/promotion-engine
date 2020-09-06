package com.abc.shopping.model;

import com.abc.shopping.contracts.Promotion;

public class MultiplierSkuPromotion implements Promotion {

    private SKU sku;
    private Integer multiplier;
    private Double fixedPrice;

    public MultiplierSkuPromotion(SKU sku, Integer multiplier, Double fixedPrice) {
        this.sku = sku;
        this.multiplier = multiplier;
        this.fixedPrice = fixedPrice;
    }

    public Double getPromotionValue() {
        return fixedPrice;
    }

    public String getName(){
        return "Buy "+multiplier+ " "+sku.getName()+" at "+fixedPrice;
    }

    public SKU getSku() {
        return sku;
    }

    public Integer getMultiplier() {
        return multiplier;
    }

}
