Feature: Product Listing Page Validation

  Background:
    Given User is on Myntra ProductListingPage Home Page

  Scenario: Verify product listing end to end flow
    When User hovers on Home menu
    And User clicks on Bed Runners category
    Then Product listing page should display products

    And All product images should be displayed
    And First product image should be visible

    When User clicks on first product
    Then User should navigate to Product Details Page

    And Product name should be displayed
    And Product image should be displayed
    And Product price should be displayed
    And Add to Bag button should be enabled

    When User adds product to bag
    And User navigates to cart
    Then Product should be added to bag

    When User clicks on Place Order
    Then Login page should be displayed