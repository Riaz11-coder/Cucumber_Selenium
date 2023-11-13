Feature: As a User I want to be able to view detailed information about a book

  Background: I am already logged in
    Given I am on bookstore login page
    When I enter credentials
  @BookSelection @Parallel
  Scenario: I should be able to view details of a book
    Given I am in the book Store
    When I select a book
    Then I am provided details about that book