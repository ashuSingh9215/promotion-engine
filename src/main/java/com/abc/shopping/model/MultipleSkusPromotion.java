package com.abc.shopping.model;

import com.abc.shopping.contracts.Promotion;

public class MultipleSkusPromotion implements Promotion {

    private SKU sku1;
    private SKU sku2;
    private Double fixedPrice;

    public MultipleSkusPromotion(SKU sku1, SKU sku2, Double fixedPrice) {
        this.sku1 = sku1;
        this.sku2 = sku2;
        this.fixedPrice = fixedPrice;
    }

    public Double getPromotionValue() {
        return this.fixedPrice;
    }

    public String getName(){
        return "Buy "+sku1.getName()+ " and "+sku2.getName()+" at "+fixedPrice;
    }

    public SKU getSku1() {
        return sku1;
    }

    public SKU getSku2() {
        return sku2;
    }

}
