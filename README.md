# promotion-engine

This is service responsible to calculate total value of cart containing multiple SKUs with promotions applied(if any).

To build the project, run maven cmd mvn clean install with JDK 1.8+

As of now, two promotion types are implemented in this service. For adding new promotion type, implement Promotion and PromotionValueCalculator interface 
and use them in ShoppingService.

Promotion Type available :
- Buy 'n' items of a SKU for a fixed price. (3 A's for 120)  (MultiplierSkuPromotion)
- Buy SKU1 and SKU2 for a fixed price. (get C and D for 40)  (MultipleSkusPromotion)


TC's verified :
- testCartWithMultipleMultiplierPromotion
- testCartWithMultiplePromotions
- testCartWithMultipleSkuPromotions
- testCartWithNoPromotions
- testCartWithOneMultipleSkuPromotion
- testCartWithSingleMultiplierPromotion
- testEmptyCart
