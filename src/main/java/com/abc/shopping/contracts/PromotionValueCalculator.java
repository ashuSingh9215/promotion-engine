package com.abc.shopping.contracts;

import com.abc.shopping.model.ShoppingCart;

public interface PromotionValueCalculator {
    Double getPromotionValue(ShoppingCart shoppingCart);
}
