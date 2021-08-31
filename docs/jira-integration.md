## Jira Integration

The test framework has been integrated into JIRA. The features currently available are:

- Automatic pushing of test evidence to a given JIRA issue using the run -Dcucumber.filter.tags value (provided it meets the cucumber.filter.tags rules)
- Execution summary stats captured as a comment on the JIRA issue

In order for the framework to use the feature, two configurations are required:

1. You need to be a user configured to use the framework
2. The user needs to create and store their Jira API token into the framework database

### Create and store an API token

Create an API token from your Atlassian account:

1. Log in to https://id.atlassian.com/manage/api-tokens.
2. Click Create API token.
3. From the dialog that appears, enter a memorable and concise Label for your token and click Create.
4. Click Copy to clipboard, then paste the token to your script, or elsewhere to save:
5. Insert key to test framework using the endpoint `/jira-controller/add-token` accessible
   at [https://app-utility-framework.framework-enablers-sit.cto-morongwa.sdc-nonprod.caas.absa.co.za/swagger-ui.html](https://app-utility-framework.framework-enablers-sit.cto-morongwa.sdc-nonprod.caas.absa.co.za/swagger-ui.html) 

Note:

- The implementation assumes that **two-factor authentication is not configured** on your Atlassian account
- For security reasons it isn't possible to view the token after closing the creation dialog; if necessary, create a new
  token. Assuming you have already added a token to the framework, you can update the framework using the
  endpoint `/jira-controller/update-token-for-user`
- When executing a run tag that includes multiple feature files, the summary comment stats will factor in all scenarios.



### Implementation and call results
The Jira integration is bundled with the email run arg. When the rules for email distribution have been met, Jira calls will also occur.

#### Call results
The below call results can be expected to be printed out to console for the given scenarios

##### Scenario 1 : Jira integration functionality has been disabled
~~~~
[INFO ] Processor:# - Jira integration functionality has been disabled by the framework administrators
~~~~
**Note:** Manually result attachment is to be performed by the user.

##### Scenario 2 : A run argument tag that is not a jira-id OR a jira-id with a description (refer to cucumber.filter.tags rules on project root README.md)
~~~~
[INFO ] Processor:# - Ignoring Jira posting. No Jira issue based on run arg and the alternate update method is disabled
~~~~
**Note:** Manually result attachment is to be performed by the user.

##### Scenario 3 :  User has no key Jira token captured
~~~~
[INFO ] Processor:# - Ignoring Jira posting as user token is not present
~~~~
**Note:** Manually result attachment is to be performed by the user.

##### Scenario 4 :  User has captured a Jira token but has been disabled for use
~~~~
[INFO ] Processor:# - Ignoring Jira posting as user token has been disabled by the framework administrators
~~~~
**Note:** Manually result attachment is to be performed by the user.

##### Scenario 5 : User has captured a Jira token but is invalid
~~~~
[INFO ] Processor:# - Jira update request invoked using: CONTROLLER-LOCATION
[ERROR] Processor:# - WHOOPS... We could not update jira. Please contact the framework administrators and forward the below error:
{"requestId":"REQUEST-ID","jiraId":["ISSUE-ID"],"processResult":"failed","commentResult":"Jira credentials are incorrect for the user NAME-SURNAME [401]","attachmentResult":"aborted"}
~~~~
**Note:** A new token is to be generated and captured by the user. Manual result attachment is to be performed by the user.

##### Scenario 6 : Invalid Jira ID provided
~~~~
[INFO ] Processor:# - Jira update request invoked using: CONTROLLER LOCATION
[ERROR] Processor:# - WHOOPS... We could not update jira. Please contact the framework administrators and forward the below error:
{"requestId":"REQUEST-ID","jiraId":["ISSUE-ID"],"processResult":"failed","commentResult":"Jira issue is not found or the user NAME-SURNAME does not have permissions to view it [404]","attachmentResult":"aborted"}
~~~~
**Note:** This error message can also be returned if you are not allowed to view the issue on the Jira project. In this case, please contact your Jira project admin to allow access.
Once access has been provided, you can then manually attach the test evidence.

##### Scenario 7 : Attachment Size exceeded
~~~~
Attachment Size exceeded
[INFO ] Processor:# - Jira update request invoked using: CONTROLLER-LOCATION
[ERROR] Processor:# - WHOOPS... We could not update jira. Please contact the framework administrators and forward the below error:
{"requestId":"REQUEST-ID","jiraId":["ISSUE-ID"],"processResult":"failed","commentResult":"success","attachmentResult":"Jira issue attachment file size of 23 MB exceeded"}
~~~~
**Note:** A comment with the run results will be posted on your behalf but manual attachment of the results must be performed. 
The current limit is set on the test framework and not by Jira.

##### Scenario 8 : When the email (-DsendEmail) argument is FALSE
~~~~
The cucumber report will be generated and no other teardown functionality will occur
~~~~
**Note:** This assumes that the email framework trigger rules have not been invoked.

##### Scenario 9 : Successful call to Jira
~~~~
[INFO ] Processor:# - Jira update request invoked using: CONTROLLER-LOCATION
[INFO ] Processor:# - Jira Issue Id: [ISSUE-ID] has been updated successfully
~~~~