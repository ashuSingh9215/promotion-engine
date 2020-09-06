package com.abc.shopping.service;

import com.abc.shopping.contracts.ShoppingService;
import com.abc.shopping.model.MultipleSkusPromotion;
import com.abc.shopping.model.MultiplierSkuPromotion;
import com.abc.shopping.model.SKU;
import com.abc.shopping.model.ShoppingCart;
import org.junit.Assert;
import org.junit.jupiter.api.Test;


class ShoppingServiceTest {

    ShoppingService shoppingService = new ShoppingServiceImpl();

    private SKU A = new SKU("A", 50.0);
    private SKU B = new SKU("B", 40.0);
    private SKU C = new SKU("C", 30.0);
    private SKU D = new SKU("D", 20.0);
    
    @Test
    void testEmptyCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        double totalCartValue = shoppingService.getTotalCartValue(shoppingCart);
        Assert.assertEquals(0, totalCartValue, 0);
    }

    @Test
    void testCartWithNoPromotions() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addToCart(A);
        shoppingCart.addToCart(A);
        shoppingCart.addToCart(A);
        shoppingCart.addToCart(B);
        shoppingCart.addToCart(C);
        shoppingCart.addToCart(D);
        shoppingCart.addToCart(B);
        shoppingCart.addToCart(D);

        double totalCartValue = shoppingService.getTotalCartValue(shoppingCart);

        /*
         *
         * A+A+A = 150
         * B+B   = 80
         * C    = 30
         * D+D  = 40
         * */
        Assert.assertEquals(300, totalCartValue, 0);
    }

    @Test
    void testCartWithSingleMultiplierPromotion() {

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addToCart(A);
        shoppingCart.addToCart(A);
        shoppingCart.addToCart(A);
        shoppingCart.addToCart(B);
        shoppingCart.addToCart(C);
        shoppingCart.addToCart(D);
        shoppingCart.addToCart(B);
        shoppingCart.addToCart(D);
        shoppingCart.addToCart(A);
        shoppingCart.addToCart(B);
        shoppingCart.addToCart(C);

        shoppingCart.applyPromotion(new MultiplierSkuPromotion(A, 3, 120.0));

        double totalCartValue = shoppingService.getTotalCartValue(shoppingCart);

        /*
        *
        * A+A+A+A = 120+50
        * B+B+B   = 120
        * C+C     = 60
        * D+D     = 40
        * */
        Assert.assertEquals(390, totalCartValue, 0);
    }

    @Test
    void testCartWithMultipleMultiplierPromotion() {

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addToCart(A);
        shoppingCart.addToCart(A);
        shoppingCart.addToCart(A);
        shoppingCart.addToCart(B);
        shoppingCart.addToCart(C);
        shoppingCart.addToCart(D);
        shoppingCart.addToCart(B);
        shoppingCart.addToCart(D);
        shoppingCart.addToCart(A);
        shoppingCart.addToCart(B);
        shoppingCart.addToCart(C);

        shoppingCart.applyPromotion(new MultiplierSkuPromotion(A, 3, 120.0));
        shoppingCart.applyPromotion(new MultiplierSkuPromotion(B, 2, 60.0));
        shoppingCart.applyPromotion(new MultiplierSkuPromotion(D, 2, 20.0));

        double totalCartValue = shoppingService.getTotalCartValue(shoppingCart);

        /*
         *
         * A+A+A+A = 120+50
         * B+B+B   = 60+40
         * C+C     = 60
         * D+D     = 20
         * */
        Assert.assertEquals(350, totalCartValue, 0);
    }

    @Test
    void testCartWithOneMultipleSkuPromotion() {

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addToCart(A);
        shoppingCart.addToCart(A);
        shoppingCart.addToCart(A);
        shoppingCart.addToCart(B);
        shoppingCart.addToCart(C);
        shoppingCart.addToCart(D);
        shoppingCart.addToCart(B);
        shoppingCart.addToCart(D);
        shoppingCart.addToCart(A);
        shoppingCart.addToCart(B);
        shoppingCart.addToCart(C);

        shoppingCart.applyPromotion(new MultipleSkusPromotion(C, D, 30.0));

        double totalCartValue = shoppingService.getTotalCartValue(shoppingCart);

        /*
         *
         * A+A+A+A = 200
         * B+B+B   = 120
         * C+D + C+D     = 60
         *
         * */
        Assert.assertEquals(380, totalCartValue, 0);
    }

    @Test
    void testCartWithMultipleSkuPromotions() {

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addToCart(A);
        shoppingCart.addToCart(A);
        shoppingCart.addToCart(A);
        shoppingCart.addToCart(B);
        shoppingCart.addToCart(C);
        shoppingCart.addToCart(D);
        shoppingCart.addToCart(B);
        shoppingCart.addToCart(D);
        shoppingCart.addToCart(A);
        shoppingCart.addToCart(B);
        shoppingCart.addToCart(C);

        shoppingCart.applyPromotion(new MultipleSkusPromotion(C, D, 30.0));
        shoppingCart.applyPromotion(new MultipleSkusPromotion(A, B, 60.0));

        double totalCartValue = shoppingService.getTotalCartValue(shoppingCart);

        /*
         *
         * A+B + A+B + A+B = 180
         * A = 50
         * C+D + C+D     = 60
         *
         * */
        Assert.assertEquals(290, totalCartValue, 0);
    }

    @Test
    void testCartWithMultiplePromotions() {

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addToCart(A);
        shoppingCart.addToCart(A);
        shoppingCart.addToCart(A);
        shoppingCart.addToCart(B);
        shoppingCart.addToCart(C);
        shoppingCart.addToCart(D);
        shoppingCart.addToCart(B);
        shoppingCart.addToCart(D);
        shoppingCart.addToCart(A);
        shoppingCart.addToCart(B);
        shoppingCart.addToCart(C);
        shoppingCart.addToCart(D);

        shoppingCart.applyPromotion(new MultipleSkusPromotion(C, D, 30.0));
        shoppingCart.applyPromotion(new MultiplierSkuPromotion(A, 2, 70.0));

        double totalCartValue = shoppingService.getTotalCartValue(shoppingCart);

        /*
         *
         * A+A + A+A = 140
         * B+B+B   = 120
         * C+D + C+D     = 60
         * D     =  20
         * */
        Assert.assertEquals(340, totalCartValue, 0);
    }
}