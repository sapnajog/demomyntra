Feature: Product Details Page Validation As a user I want to view product details So that I can verify product information before purchase

  Background:
    Given User navigates to Product Details Page

  @smoke @navigation
  Scenario: Verify user navigates to PDP via PLP
    Then User should be on Product Details Page

  @smoke
  Scenario: Verify product name is displayed
    Then Product name should be visible

  @smoke
  Scenario: Verify product image is displayed
    Then Product image should be visible

  @smoke
  Scenario: Verify product price is displayed
    Then Product price should be visible

  @smoke
  Scenario: Verify product description is displayed
    Then Product description should be visible

  @smoke
  Scenario: Verify Add to Bag button is enabled
    Then Add to Bag button should be enabled

  @regression
  Scenario Outline: Verify delivery validation for pincode
    When User enters pincode "<pincode>"
    Then Delivery validation should be "<result>"

    Examples:
      | pincode | result |
      | 411027  | valid  |
      | 000000  | invalid|
      | ABCDE   | invalid|
      | 123     | invalid|

  @regression
  Scenario: Verify product details (brand, name, price)
    Then Product details should be displayed correctly

  @negative
  Scenario: Verify product image is not broken
    Then Product image should be valid

  @negative
  Scenario: Verify product price is valid
    Then Product price should be valid

  @negative
  Scenario: Verify product description availability
    Then Product description or specifications should be available
    
  Scenario: Verify product title is displayed on PDP
    Then Product title should be displayed

  Scenario: Verify size options are available on PDP
    Then Size options should be available

  Scenario: Verify delivery validation for valid Nashik pincode
    When User enters pincode "422001"
    Then Delivery validation should be "valid"
   
  @Ignore 
  Scenario: Verify delivery validation for valid Delhi pincode
    When User enters pincode "110001"
    Then Delivery validation should be "valid"

  Scenario: Verify delivery validation for valid Bangalore pincode
    When User enters pincode "560001"
    Then Delivery validation should be "valid"

  Scenario: Verify delivery validation for valid Hyderabad pincode
    When User enters pincode "500001"
    Then Delivery validation should be "valid"
  