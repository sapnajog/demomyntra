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

  Scenario: Verify search box is visible on home page
    Then Search box should be visible

  Scenario: Verify home menu is visible
    Then Home menu should be visible

  Scenario: Verify search for Curtains
    When User searches for "Curtains"
    Then Search results should be displayed

  Scenario: Verify search for Cushions
    When User searches for "Cushions"
    Then Search results should be displayed

  Scenario: Verify search for Bed Runners
    When User searches for "Bed Runners"
    Then Search results should be displayed

  Scenario: Verify search for Bedsheets
    When User searches for "Bedsheets"
    Then Search results should be displayed

  Scenario: Verify search for Home Decor
    When User searches for "Home Decor"
    Then Search results should be displayed

  Scenario: Verify search result header for Bedsheets
    When User searches for "Bedsheets"
    Then Search result should contain "bedsheets"

  Scenario: Verify search result header for Curtains
    When User searches for "Curtains"
    Then Search result should contain "curtains"

  Scenario: Verify home page title is correct
    Then Page title should contain "Myntra"
