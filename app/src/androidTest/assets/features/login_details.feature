Feature: Enter login details

  @smoke
  @e2e
  Scenario Outline: Successful login
    Given I start the application
    When I enter valid email <email>
    And I enter valid password <password>
    And I close the keyboard
    And I click sign in button
    Then I expect to see successful login message
    Examples:
      | email        | password |
      | test3@mail.com | 12345678   |
      | test4@mail.com | 12345678900   |
