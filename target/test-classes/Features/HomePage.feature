Feature: Myntra Home Page Validation

  Background:
    Given User is on Myntra Home Page

  Scenario: Verify homepage logo is clickable
    When User clicks on Myntra logo
    Then User should be navigated to homepage

  Scenario: Verify logo click after search
    When User searches for "BedSheets"
    And User clicks on Myntra logo
    Then User should be navigated to homepage

  Scenario: Verify search results load
    When User searches for "BedSheets"
    Then Search results should be displayed

  Scenario: Verify search with invalid product
    When User searches for "xyznonexistentproduct123"
    Then Invalid search message should be displayed

  Scenario: Verify home page Functionalty
    When User clicks on Myntra logo
    And User searches for "BedSheets"
    Then Search result should contain "bedsheets"
