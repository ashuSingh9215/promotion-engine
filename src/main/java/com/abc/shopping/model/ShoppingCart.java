package com.abc.shopping.model;

import com.abc.shopping.contracts.Promotion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {

    private Map<SKU, Integer> items;
    private List<Promotion> promotions;

    public ShoppingCart() {
        items = new HashMap<>();
        promotions = new ArrayList<>();
    }


    public Map<SKU, Integer> getItems() {
        return items;
    }

    public List<Promotion> getPromotions() {
        return promotions;
    }

    public void addToCart(SKU item){
        Integer itemCount = items.getOrDefault(item, 0);
        items.put(item, itemCount+1);
    }

    public void applyPromotion(Promotion promotion){
        promotions.add(promotion);
    }

}
