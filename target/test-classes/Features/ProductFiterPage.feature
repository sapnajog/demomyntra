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