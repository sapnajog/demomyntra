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
   
  Scenario: Verify Place Order button is visible in cart
    Given User navigates to Product Details Page for cart
    When User adds product to cart
    And User opens cart page
    Then Place Order button should be visible

  Scenario: Verify login page displayed after Place Order
    Given User navigates to Product Details Page for cart
    When User adds product to cart
    And User opens cart page
    And User clicks on Place Order button
    Then Login page should be displayed after order