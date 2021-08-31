@PAF-2 @PAF-Regression
Feature: PAF-2_Selenium Framework Test

  Background:
    Given I open the browser and navigate to google
    And I am on the google search page

  Scenario Outline: Multiple Page title validation
    When I type <textToSearch> and click the search button
    And The final test payload is logged out
    Then The page title expected to display is <title>

    Examples:
      | textToSearch | title                |
      | blah         | blah - Google Search |
      | junk         | junk - Google Search |

  Scenario: Page title validation within data table feature
    When I type hooligan and click the search button
    And The final test payload is logged out
    Then The correct page title displayed
