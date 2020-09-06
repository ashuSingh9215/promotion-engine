package com.abc.shopping.service;

import com.abc.shopping.contracts.PromotionValueCalculator;
import com.abc.shopping.contracts.ShoppingService;
import com.abc.shopping.model.ShoppingCart;
import com.abc.shopping.utility.MultipleSkusPromoCalculator;
import com.abc.shopping.utility.MultiplierSkuPromoCalculator;

public class ShoppingServiceImpl implements ShoppingService {

    // Declare instances of all promoCalculators
    private PromotionValueCalculator multipleSkusPromoCalculator = MultipleSkusPromoCalculator.getInstance();
    private PromotionValueCalculator multiplierSkuPromoCalculator = MultiplierSkuPromoCalculator.getInstance();

    public Double getTotalCartValue(ShoppingCart shoppingCart) {

        Double promotionValue = getPromotionValue(shoppingCart);
        Double nonPromotionCost = getRemainingCartValue(shoppingCart);

        return promotionValue + nonPromotionCost;
    }

    private Double getRemainingCartValue(ShoppingCart shoppingCart) {
        return shoppingCart.getItems().entrySet()
                .stream().mapToDouble(entry -> entry.getKey().getCost()*entry.getValue()).sum();
    }

    private Double getPromotionValue(ShoppingCart shoppingCart) {
        return multipleSkusPromoCalculator.getPromotionValue(shoppingCart)
                +multiplierSkuPromoCalculator.getPromotionValue(shoppingCart);
    }

}
