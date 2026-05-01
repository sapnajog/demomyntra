Feature: Myntra Product Filter Validation

  Background:
    Given User is on Myntra home page
    When User navigates to Bed Runners page

  
  Scenario Outline: Verify category filter for products
    When User applies category filter "<categoryName>"
    Then Category filter should be validated for "<categoryName>"

    Examples:
      | categoryName        |
      | Runners             |
      | Cushions            |
      | InvalidCategory123  |

  
  Scenario: Verify brand filter for products
    When User applies brand filter "Aura"
    Then Only "Aura" brand products should be displayed

  
  Scenario: Verify price filter for products
    When User applies price filter
    Then Products should be within price range 500 to 1000

  
  Scenario: Verify color filter for products
    When User applies color filter "Green"
    Then Color filter "Green" should be applied
    
  Scenario: Verify price filter for products
    Given User opens price filtered page
    Then Products should be within price range 500 to 1000

  Scenario: Verify filter panel is visible on PLP
    Then Filter panel should be visible

  Scenario: Verify clear all filters resets the product list
    When User applies category filter "Table Runners"
    And User clears all filters
    Then Product listing page should display products

  Scenario: Verify multiple filters can be applied together
    When User applies category filter "Table Runners"
    And User applies color filter "Green"
    Then Product listing page should display products

  Scenario: Verify category filter with click method
    When User clicks on category filter "Curtains and Sheers"
    And User clicks on color filter "Red"
    And User clicks on brand filter "Aurelia"
    Then Product listing page should display products

  Scenario: Verify category filter for Curtains and Sheers
    When User applies category filter "Curtains"
    Then Category filter should be validated for "Curtains"

  Scenario: Verify color filter Blue is applied
    When User applies color filter "Blue"
    Then Color filter should be applied for "Blue"

  Scenario: Verify color filter Red is applied
    When User applies color filter "Red"
    Then Color filter should be applied for "Red"

  Scenario: Verify brand filter search works
    When User applies brand filter "Home"
    Then Brand filter should be validated for "Home"
  
  @Ignore
  Scenario: Verify category filter for Bed Runners
    When User applies category filter "Bed Runners"
    Then Category filter should be validated for "Bed Runners"
