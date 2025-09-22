Feature: Verify Registartion page
  Scenario: Registration for user
    Given I on login page
    When I enter "test" and "Test@yopmail.com" and click on SignUp button
    Then I redirected to registartion page

  Scenario Outline: Login with existing user
    Given I on login page
    When I enter <email> and <password> and click on login button
    Then I am able to logged in successfully
    Examples:
      | email                     | password   |
      | "parabpraveena@gmail.com" | "test@123" |