@PAF-Regression
Feature: PAF-3_API framework test

  Scenario Outline: Positive GET response in the Unit operation
    Given The user requests a GET on the API endpoint using the id <id>
    And The final test payload is logged out
    And The user retrieves the status code from the response and validates it as <statusCode>
    Then the user extracts the name from json path <nameJsonPath>
    And the user extracts the description from the json path <descriptionJsonPath>
    Then the user validates that the name is <unitName>
    And the user validates that the description is <unitDescription>
    Examples:
      | id | statusCode | unitName | nameJsonPath | unitDescription | descriptionJsonPath |
    ##@externaldata@src/test/resources/data/testdata/paf/SampleDoc.xlsx@api-positive

  @PAF-3
  Scenario Outline: Negative GET response in the Unit operation
    Given The user requests a GET on the API endpoint using an invalid id <id>
    Then The user receives the status code <statusCode>
    Examples:
      | id | statusCode |
      ##@externaldata@src/test/resources/data/testdata/paf/SampleDoc.xlsx@api-negative
