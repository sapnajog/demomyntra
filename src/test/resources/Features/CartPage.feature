Feature: Cart Page Validation
  As a user
  I want to add products to cart
  So that I can purchase them

  Scenario: Verify product added to cart from product details page
    Given User navigates to Product Details Page for cart
    When User adds product to cart
    And User opens cart page
    Then Product should be visible in cart

  Scenario: Verify product added to cart via product listing page
   Given User navigates to Product Details Page for cart
   When User clicks on Add To Bag button
   And User opens cart page
   Then Product should be added to cart