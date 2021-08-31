package africa.za.gambit.stepdefinitions.paf.webservice;

import africa.za.gambit.junit.cucumber.framework.base.TestBase;
import africa.za.gambit.junit.cucumber.framework.supporting.report.Report;
import africa.za.gambit.systems.paf.webservice.AgeOfEmpires;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j;

@Log4j
public class ApiFrameworkFeature extends TestBase {

    private Response response;
    private String actualName;
    private String actualDescription;

    @Given("^The user requests a GET on the API endpoint using the id (.+)$")
    public void theUserRequestsAGETOnTheAPIEndpointUsingTheId(String id) {
        response = new AgeOfEmpires().doUnit(id);
        TestBase.setMandatoryReportStepValues("Valid Id: " + id);
    }

    @Given("^The user requests a GET on the API endpoint using an invalid id (.+)$")
    public void theUserRequestsAGETOnTheAPIEndpointUsingAnInvalidId(String id) {
        response = new AgeOfEmpires().doUnit(id);
        TestBase.setMandatoryReportStepValues("Invalid Id: " + id);
    }

    @Then("^the user extracts the name from json path (.+)$")
    public void the_user_extracts_the_name_from_json_path(String nameJsonPath) {
        actualName = response.getBody().jsonPath().getString(nameJsonPath);
        Report.log(scenario, "JSON Path for 'name' value", nameJsonPath);
        Report.log(scenario, "Response Body", response.getBody().asString());
    }

    @Then("^the user validates that the name is (.+)$")
    public void the_user_validates_that_the_name_is(String unitName) {
        Report.validate(scenario, "Asserting unit name", "The incorrect unit name has been received", unitName, actualName);
    }

    @And("^the user validates that the description is (.+)$")
    public void the_user_validates_that_the_description_is(String unitDescription) {
        Report.validate(scenario, "Asserting unit description", "The incorrect unit description has been received", unitDescription, actualDescription);
    }

    @And("^The user retrieves the status code from the response and validates it as (.+)$")
    public void the_user_retrieves_the_status_code_from_the_response_and_validates_it_as(String statusCode) {
        int actualStatusCode = response.getStatusCode();
        Report.validate(scenario, "Asserting status code", "The incorrect response status code has been received", Integer.parseInt(statusCode), actualStatusCode);
    }

    @And("^the user extracts the description from the json path (.+)$")
    public void the_user_extracts_the_description_from_the_json_path(String descriptionJsonPath) {
        actualDescription = response.getBody().jsonPath().getString(descriptionJsonPath);
        Report.log(scenario, "JSON Path for 'description' value", descriptionJsonPath);
        Report.log(scenario, "Response Body", response.getBody().asString());
    }

    @Then("^The user receives the status code (.+)$")
    public void the_user_receives_the_status_code(String statusCode) {
        int actualStatusCode = response.getStatusCode();
        Report.validate(scenario, "Asserting status code", "The incorrect response status code has been received", Integer.parseInt(statusCode), actualStatusCode);
    }

}
