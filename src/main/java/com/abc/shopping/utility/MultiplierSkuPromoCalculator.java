package com.abc.shopping.utility;

import com.abc.shopping.contracts.Promotion;
import com.abc.shopping.contracts.PromotionValueCalculator;
import com.abc.shopping.model.MultiplierSkuPromotion;
import com.abc.shopping.model.SKU;
import com.abc.shopping.model.ShoppingCart;

import java.util.Iterator;
import java.util.Map;

public class MultiplierSkuPromoCalculator implements PromotionValueCalculator {

    private static MultiplierSkuPromoCalculator promoCalculator;

    private MultiplierSkuPromoCalculator(){
    }

    public static MultiplierSkuPromoCalculator getInstance(){
        if(promoCalculator == null)
            promoCalculator = new MultiplierSkuPromoCalculator();
        return promoCalculator;
    }

    @Override
    public Double getPromotionValue(ShoppingCart shoppingCart) {

        Iterator<Promotion> promotionIterator = shoppingCart.getPromotions().iterator();
        Map<SKU, Integer> items = shoppingCart.getItems();

        double promoValue = 0;

        while (promotionIterator.hasNext()){
            Promotion promotion = promotionIterator.next();
            if(promotion instanceof MultiplierSkuPromotion){
                MultiplierSkuPromotion promo = (MultiplierSkuPromotion) promotion;
                SKU promoSku = promo.getSku();
                Integer multiplier = promo.getMultiplier();
                if(items.containsKey(promoSku)){
                    int count = items.get(promoSku);
                    promoValue += (count/multiplier) * promo.getPromotionValue();
                    items.put(promoSku, count%multiplier);
                }
            }
        }

        return promoValue;
    }
}
