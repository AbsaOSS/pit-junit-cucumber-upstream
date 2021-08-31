## Junit Cucumber Test Framework

## Features
* Clear maven folder structure
* Clear systems folder structure
* Rest service testing using REST-assured
* WebUi testing using selenium
* Database testing using JDBC
* Soft wrapped utilities for REST-assured, Selenium and Database Transactions
* Custom annotations and hooks

## To Get Started
#### Pre-Requisite
1. A framework administrator must capture your details on the framework
2. Access to a Jira project with a token captured (see ./docs/jira-integration.md)
3. Java Installed (java version "1.8.0_241")
4. Maven installed (apache-maven-3.6.3)
5. IntelliJ Community with the plugins / functionality enabled:
    * Lombok plugin
    * Cucumber for Java
    * Gherkin

**NOTE:** Please send an email with the below information to **PaymentIntegrationTestSupport@absa.co.za** for you to be onboarded
````dtd
- Name
        - Surname
        - Email address
        - Ab number
        - Team name
````

#### Mandatory VM Arguments

````dtd
-ea
        -Dproject="Cucumber Junit PAF"
        -Dteam=PAF
        -Denvironment=SIT
        -Dselenium=TRUE
        -DsendEmail=TRUE
        -Dheadless=TRUE
        -Ddry-run=FALSE
        -Dcucumber.filter.tags=@PAF-Regression
````

##### Permissible argument values
* project - Free text with a maximum character count of 35
* team - Can be one of 'morongwa', 'ohm', 'isango' and 'pit'
* environment - Can be one of 'dev', 'sit' or 'uat'
* selenium - Can be one of 'true' or 'false'
* headless - Can be one of 'true' or 'false'
* sendEmail - Can be one of 'true' or 'false'
* dry-run - Can be one of 'true' or 'false'
* cucumber.filter.tags - Tag value must start with the team Jira project key

#### Usage and definition
##### project
* The value is populated to the HTML report generated

##### team
* The value is used for the filtering of features files upon execution, validation against the tag value and the retrieval of the team email distribution list

##### environment
* This value is used to switch out the environmental properties file

##### selenium
* When this value is true, the argument will invoke the selenium functionality using the @Before hook and the configuration is loaded from the properties file: `webdriver.properties`
````dtd
webdriver.browserType=chrome
        webdriver.scriptTimeout=20
        webdriver.pageLoadTimeout=15
        webdriver.globalwait=10
        webdriver.incognito=true
````
##### headless
* When this value is true, the argument will invoke the selenium functionality using the headless mode

##### sendEmail
* When this value is true, an email will be sent to the distribution list specified in the environmental property per team:
````dtd
team.emailRecipient.to
        team.emailRecipient.cc
````
* When an email is triggered via the command argument the email will send one of two emails namely:
    * An email with the test evidence is attached along with a summary of the run
    * An email with the test evidence attached but this email will not have an email body (this means that an external controller is not up and running)

###### Note: The property value is `comma-separated` for multiple recipient support

##### dry-run
* When this value is true, a run will be executed, but the teardown functionality will be limited to the generation of the execution report only

##### cucumber.filter.tags
Execution tags have been limited and must correspond with the below naming convention.
All project keys and test types are case-sensitive.

##### 1. Jira reference only
Syntax: `@projectKey-issueId`
-	`projectKey` can be:
    - ID (Isango)
    - MD (Morongwa)
    - OPOM (Ohm)
    - CBG (Cbg)
    - PNF (kuongorora)
-	`issueId` is the jira issue id number
````
Example: @MD-1233
````

##### 2. Jira reference with description
Syntax: `@projectKey-issueId_description`
-	`projectKey` can be:
    - ID (Isango)
    - MD (Morongwa)
    - OPOM (Ohm)
    - CBG (Cbg)
    - PNF (kuongorora)
-	`issueId` is the jira issue id number
-	`description` is your description
````
Example: @MD-1233_NegativeComponentTests
````

##### 3. Team and test type
Syntax: `@team-testType_description`
-	`team` can be:
    - PIT (End to end tests)
    - ID (Isango)
    - MD (Morongwa)
    - OPOM (Ohm)
    - PNF (kuongorora)
-	`testType` can be one of Component, Integration, Functional, EndToEnd, Smoke, Regression or Sanity
````
Example: @MD-Regression
````

##### 4. Team and test type with description
Syntax: `@team-testType_description`
-	`team` can be:
     - PIT (End to end tests)
     - ID (Isango)
     - MD (Morongwa)
     - OPOM (Ohm)
     - PNF (kuongorora)
-	`testType` can be one of Component, Integration, Functional, EndToEnd, Smoke, Regression or Sanity
-	`description` is your description

````dtd
Example: @MD-Regression_Zambia
````
###### ** PIT should only be used for end to end and/or UAT tests

## Project Structure
#### Systems
* All methods acting as keywords, webservice calls, database transactions, etc. for a given system must be stored under the system team name
````dtd
src/main/java/africa/za/gambit/systems  |
                                        | team name     |
                                                        | database
                                                        | keywords
                                                        | pageObjects
                                                        | tools
                                                        | webservice
````

#### Features
* Feature files must be filed per team in the respective folder nested under `src/test/java/africa/za/gambit/features`
````dtd
src/test/java/africa/za/gambit/features  |
                                         | team  name   |
                                                        | sub-folder
````
* When a feature file has been created, the FEATURE name / description must correspond to the below naming convention:
##### 1. Jira reference with description
Syntax: `Feature: projectKey-issueId_description`
-	`projectKey` can be ID (Isango), MD (Morongwa) or OPOM (Ohm) or CBG (Cbg)  or PNF (Kuongorora)
-	`issueId` is the jira issue id number
-	`description` is your description
````
Example: Feature: MD-1233_Negative Component Tests
````
##### 2. Team and test type with description
Syntax: `Feature: team-testType_description`
-	`team` can be PIT **(Payment Integration Team)**, ID (Isango), MD (Morongwa) or OPOM (Ohm)	or CBG (Cbg)  or PNF (Kuongorora)
-	`testType` can be one of Component, Integration, Functional, EndToEnd, Smoke, Regression or Sanity
-	`description` is your description
````
Example: Feature: MD-Regression_Zambia system messages
````
###### ** PIT should only be used for end to end and/or UAT tests

* The naming convention for the feature file itself must correspond with the feature level tag. In the example below, the feature level tag is
  `@PAF-11_MqFrameworkTest` which will require the file name to be `PAF-11_MqFrameworkTest.feature`

Example Tagging:
````
@PAF-4_MqFrameworkTest @PAF-Regression
Feature: PAF-4_Mq Framework Test

  Background:
    Given I want a quick test to execute

    @PAF-Functional_Mq
  Scenario Outline: MQ operations on different instances
    When I clear the queue named "<queue>" of messages on the "<team>" mq instance
    Then I receive a CLEAR response with the result as <clearResult>
    When I put a message containing "<message>" on the mq instance using the queue name: "<queue>"
    Then I receive a PUT response with the result as <putResult>
    When I perform a search for the message: "<message>" on the queue: "<queue>"
    Then I receive a FIND response with the result as <findResult>
    Examples:
      | queue                | team        | clearResult | message                             | putResult | findResult |
      | TEST.SIT.FRAMEWORK.Q | morongwaSA  | Success     | This is my morongwaSA test message  | Success   | Success    |
      | TEST.SIT.FRAMEWORK.Q | morongwaROA | Success     | This is my morongwaROA test message | Success   | Success    |
      | TEST.SIT.FRAMEWORK.Q | isango      | Success     | This is my isango test message      | Success   | Success    |
      | TEST.SIT.FRAMEWORK.Q | ohm         | Success     | This is my ohm test message         | Success   | Success    |

````
#### Step Definitions
* Step definitions must be filed per team in the respective folder nested under `src/test/java/africa/za/gambit/stepdefinitions`
````
src/test/java/africa/za/gambit/stepdefinitions  |
                                                | team  name    |
                                                                | sub-folder
````
* Step definitions class MUST extend the TestBase package to access environmental variables, report logging and asserts
````
public class MyStepDefinitions extends TestBase{

    @Given("^I am in the google page$")
    public void iAmInTheGooglePage(){
        --- Use methods created in systems to ---
    }
}
````
#### Test Data
* Test data must be filed per team in the respective folder nested under `src/test/resources/data/testdata`
* Excel documents must be in the file format `xlsx`
* Absolute paths must not be used
* Syntax: `##@externaldata@filePath@sheetName` where:
    * `filePath` is the path from the content root example: src/test/resources/data/testdata/poc/SampleDoc.xlsx
    * `sheetName` is the sheet name to be referenced in the document.
* Example Scenario Outline using an excel document as a Datable
````
  Scenario Outline: MQ PUT from excel
    When I put a excel payload <payload> on the queue named '<queue>' using the ohm mq instance
    Then I will receive a response with the status code "<code>"
    Examples:
      | queue                | payload                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  | code |
  ##@externaldata@src/test/resources/data/testdata/paf/SampleDoc.xlsx@mq-put
````
##### IMPORTANT Note:
* At runtime the feature files nested under the team will be overwritten and will contain the Datatable with the actual values. It is a requirement,
  to rollback the affected feature files before committing changes.

## Running Scripts
#### Runner
* A single custom runner has been configured and should be driven using tags.

#### Command line execution
* Tests can be run from the command line (provided maven is installed) with all mandatory arguments
```dtd
mvn clean test -Dproject="Cucumber Junit PAF" -Dteam=PAF -Denvironment=SIT -Dselenium=TRUE -DsendEmail=FALSE -Dheadless=TRUE -Ddry-run=TRUE -Dcucumber.filter.tags=@PAF-Regression
```

## Reports
#### HTML Report
* Reports will be automatically generated after runtime and can be located at `target/cucumber-html-reports`
#### Report distribution
* Report distribution is driven by the -DsendEmail argument and will have a zip file attachment that includes the Html automatically generated
#### Optional HTML Reporter
* After a runner has been completed an optional report may be generated using a maven goal `cluecumber-report:reporting`

## Github Branching
#### Master branch
* Additions to the MASTER branch will be merged via pull requests.
* Default reviewers have been set up project wide and includes framework support resources and all team test resources.
* At least one approval from each team is required to be able to merge the pull request
* When a branch is merged to master, a Jenkins job will start and package the MASTER branch. On failure of this job, a communication will be sent to the resource whose branch
  is being merged to correct the issues urgently.

#### Working branches
* It is a requirement that the test resource keep their local branch up to date with the MASTER branch by checking daily if changes are required to be merged into their working branch
* When a new test is being scripted, a branch must be created off the MASTER branch and must conform to the naming convention: `TeamName-ABnumber-JiraIssue ID`

#### Best Practice
* `Standardize branch naming convention` to easily identify the owner of the branch and its scope

Syntax: `team-ab-number-functionality`

````
Example: morongwa-abma450-aims-component
````
* `Daily commits` to be made so that work is available online should something unforeseen happen

* `ALL BRANCH NAMES MUST BE LOWER CASE`

* Create `small content specific branches` and merge changes to environmental property files and systems package logic as quickly as possible

* `Review Pull Requests and do so timeously`

* `Pull requests with branch names that do not conform to the naming convention will automatically be deleted`


## Jenkins instance
* Refer to documentation located at /docs/jenkins-implementation.md

## Mandatory Steps for additional reporting attachment
* Refer to documentation located at /docs/mandatory-steps.md

## Jira Integration
* Refer to documentation located at /docs/jira-integration.md


## Support/ Resources / Links
* [Cucumber](https://cucumber.io/)
* [REST-assured](http://rest-assured.io/)
* [AssertJ](https://assertj.github.io/doc/)
* [Prowide](https://javadoc.io/doc/com.prowidesoftware/pw-swift-core/latest/index.html)
* Useful Chrome extension
    * [Tidy Gherkin](http://tiny.cc/7hflnz)
    * [ChroPath](http://tiny.cc/3gflnz)