Feature: As a User I want to sort and filter the book list

  Background: I am already logged in
    Given I am on bookstore login page
    When I enter credentials
    @DynamicSearch @Parallel
    Scenario: I want to filter books via the search bar
      Given I am in the book Store
      When  I enter keys into the search bar
      Then  The book selection should dynamically filter