Feature: Account Registration
  Scenario: Registering a new  account in Agoda.com.
    Given the registration page is open
    When a valid email "gabutasos.bevubaci@gotgel.org" is entered
    And a new password "Abc!234567890" is entered
    And all the required fields are entered
    And the sign up button is clicked
    Then redirection to the account confirmation page occurs
    And username is displayed
