Feature: Google test
  @Google2
  Scenario: Testing Google
    Given I navigate to Google search page
    When  I check page title
    Then  I have page title assertion