## Mandatory Steps

In order to provide a higher level of detail for execution runs communicated to stakeholders via email, an additional
HTML report will be generated and attached to the email distributed. The requirement requested was to include the
following:

1. Output the final test payload to be viewed for reference to the test result
2. On failure of a result, error messages are to be displayed

In order to fulfill the above the following modifications were made to the framework:

1. Introduce a mandatory step along with the step definition to output the test payload
2. Enhance the Report.validate methods to include a failure description. This description should be treated as a
   description communicated to a party with no knowledge of the script.

### How to implement the step

1. Include the gherkin step into the feature file

   ``
   And The final test payload is logged out
   ``
2. Include the setting of the payload in _YOUR script step definitions_ 
   ``
   TestBase.setMandatoryReportStepValues(payload)
   ``

###### NOTE:

- Set the payload before any validations

### Implementation handling

- Should you include the step but NOT set the value in the step definition, the following will be displayed on the added
  HTML report

``
SCRIPTING ERROR: Test payload step included in scenario but the step definition value was not set
``

- Should you include the step but set the value in the step definition AFTER A VALIDATION point that fails on execution, the
  following will be displayed on the added HTML report

``
SCRIPTING ERROR: Final test payload was not set before first failure in script
``