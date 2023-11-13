Feature: As a user I should be able to search Google and navigate to the page I search for

  @Google2
  Scenario: I want to navigate to a page I search for on Google
    Given I navigate to Google search page
    When I search for a web site
    Then I should be able to navigate there