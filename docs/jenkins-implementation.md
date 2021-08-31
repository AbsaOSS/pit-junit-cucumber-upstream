## Jenkins Implementation
   
When project teams on boarded onto Subatomic, by default a Jenkins instance was provisioned.
The testing team will utilize the existing project team Jenkins instances for on-demand and or scheduled jobs.
In order to separate the development and testing applications on subatomic and Jenkins, each team has been configured with a pi-test-team on subatomic, namely:

* pi-test-team-isango
* pi-test-team-isintu
* pi-test-team-morongwa

For each pi-test-team, access is controlled through the configured Project Teams LDAP groups. In order to gain access to the Jenkins instance, an AfricaAccess request must submitted against the groups below:

| Test Team             | LDAP Group                        |
| :-----------          | :-----------                      |
|pi-test-team-isango	|isango-PROD-ALL-Users              |
|pi-test-team-isintu	|isintu-PROD-ZA-Users               |
|pi-test-team-morongwa	|panafricanmorongwa-PROD-ALL-Users  |

One stand-alone Subatomic team has been created to cater for End-to-End tests.

* The subatomic team name created is: **pi-test-team** 
* The AfricaAccess group created for this team is: **PiTestTeam-PROD-ZA-user-access**


### Subatomic Projects
The testing framework is environment friendly and to cater for this; each team will have two projects already created, namely:

* sit-testing-TEAM_NAME
* uat-testing-TEAM_NAME

The project name _sit-testing-TEAM_NAME_ is to cater for features being executed against the **SIT** environment when using the -Denvironment=SIT argument
The project name _uat-testing-TEAM_NAME_ is to cater for features being executed against the **UAT** environment when using the -Denvironment=UAT argument


### Subatomic Applications
Applications in subatomic in the context of the automation framework, is essentially a test that is configured to be executed.
When an application is created in Subatomic under the configured team and project, it will create a clean file structure on Jenkins and on the automation framework when the CI/CD files are merged.

#### Application Creation Process
The below process assumes that you are navigated and on the team page.
1.	Select the project name
2.	Under the Applications tab, select Add Application
3.	When the **Add application** form is loaded, the following should be captured before clicking **Submit**:
~~~~
Name – The name of your test(naming convention to be followed as per mentioned after this section)

Description – A description of the functionality under test(should give the essence of what you want to achieve)

Application Type – must be selected as Library

Select repository – The repository name is: pit-test-junit-cucumber

Default Branch – The default branch must be MASTER

Select Language Pack – this must be selected as Java 8 Maven
~~~~
Once the _Add Application_ form has been submitted, the Subatomic application will start to provision.

#### Application naming convention
* _system-flow_ can be used when catering for a system across multiple countries
    >> Example: flex-flow
* _country-system-flow_ when catering for specific countries and system
    >> Example: kenya-flex-flow
* _component-name-flow_ 
> * can be used when catering for component as generic functionality
    
>> Example: dupcheck-flow

> * can be used when catering for component for a specific country functionality
     
>> Example: dupcheck-kenya-flow

#### Provision Process
When an application has been created, Subatomic will automatically create a branch with the CI/CD files and create a pull request to merge into the branch stipulated.
Before merging the pull request, the following steps need to be completed:

1.	Pull the CI/CD branch created
2.	Modify the Jenkinsfile.groovy file and,
a.	Include the run command under the Test stage
b.	Delete the Publish stage
3.	Commit this change
4.	Merge the pull request and delete the branch


**_Note:_**
The automation framework allows for running tests with and without Selenium functionality. To cater for this a sub container has been created.
By default, the default base container named sub-maven-3-jdk-8:latest can be used to run tests that DO NOT require Selenium functionality
In order to execute tests that use the Selenium functionality, additional changes to the Jenkinsfile.groovy file is required. These steps include the following:
1.	Change the dockerPullProject value to `${baseDockerRegistry}/pi-test-team-docker-local`
2.	Change the containerTemplate, image to `dockerPullProject + '/sub-mvn-jdk-chrome:latest'`


#### Executing a test
Once the provisioning process has completed and the CI/CD files have been merged, the Subatomic application will be present on the test teams Jenkins instance.
In order to execute a test;
1.	Navigate to the correct application name on Jenkins, which will be nested under the subatomic project name
2.	Select the name of the application
3.	Select Scan Repository Now on the left hand pane
4.	Progress of a test can be monitored on the console view


## Support/ Resources / Links
* [Subatomic](https://sub.bcp.absa.co.za/)
* Jenkins
> The required Jenkins instance can be accessed through Subatomic
>> Subatomic -> teamName -> Quick Links -> Ci/CD
