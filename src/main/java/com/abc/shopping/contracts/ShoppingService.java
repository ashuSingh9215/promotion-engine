package com.abc.shopping.contracts;


import com.abc.shopping.model.ShoppingCart;

public interface ShoppingService {

    Double getTotalCartValue(ShoppingCart shoppingCart);
}
