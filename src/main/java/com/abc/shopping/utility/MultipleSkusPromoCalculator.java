package com.abc.shopping.utility;

import com.abc.shopping.contracts.Promotion;
import com.abc.shopping.contracts.PromotionValueCalculator;
import com.abc.shopping.model.MultipleSkusPromotion;
import com.abc.shopping.model.SKU;
import com.abc.shopping.model.ShoppingCart;

import java.util.Iterator;
import java.util.Map;

public class MultipleSkusPromoCalculator implements PromotionValueCalculator {

    private static MultipleSkusPromoCalculator promoCalculator;

    private MultipleSkusPromoCalculator(){
    }

    public static MultipleSkusPromoCalculator getInstance(){
        if(promoCalculator == null)
            promoCalculator = new MultipleSkusPromoCalculator();
        return promoCalculator;
    }

    @Override
    public Double getPromotionValue(ShoppingCart shoppingCart) {

        Iterator<Promotion> promotionIterator = shoppingCart.getPromotions().iterator();
        Map<SKU, Integer> items = shoppingCart.getItems();

        double promoValue = 0;

        while (promotionIterator.hasNext()){
            Promotion promotion = promotionIterator.next();
            if(promotion instanceof MultipleSkusPromotion){
                MultipleSkusPromotion promo = (MultipleSkusPromotion) promotion;
                SKU sku1 = promo.getSku1();
                SKU sku2 = promo.getSku2();
                if(items.containsKey(sku1) && items.containsKey(sku2)){
                    int sku1Count = items.get(sku1);
                    int sku2Count = items.get(sku2);
                    int promoCount = sku1Count > sku2Count ? sku2Count : sku1Count;
                    promoValue += (promoCount) * promo.getPromotionValue();
                    items.put(sku1, sku1Count-promoCount);
                    items.put(sku2, sku2Count-promoCount);
                }
            }
        }

        return promoValue;
    }
}
