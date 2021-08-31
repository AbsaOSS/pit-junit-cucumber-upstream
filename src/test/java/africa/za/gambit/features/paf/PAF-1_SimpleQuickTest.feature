@PAF-1 @PAF-Regression
Feature: PAF-1_Simple Quick Test
  This is my feature level description for high level reporting

  @PAF-Regression-scenarioTag
  Scenario Outline: Quick and simple scenario
  This is my SCENARIO level description for high level reporting

    Given I want a quick test to execute
    And The final test payload is logged out
    When I execute and look up the logged in user
    Then The user logged in is <user>
    Examples:
      | user   |
      | whoami |
