package africa.za.gambit.stepdefinitions.paf.simple;

import africa.za.gambit.junit.cucumber.framework.base.TestBase;
import africa.za.gambit.junit.cucumber.framework.supporting.report.Report;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.log4j.Log4j;

@Log4j
public class SimpleQuickFeature extends TestBase {

    @Given("^I want a quick test to execute$")
    public void iWantAQuickTestToExecute() {
        Report.log(scenario, "Executing test");
        TestBase.setMandatoryReportStepValues("FINAL PAYLOAD");
    }

    @When("^I execute and look up the logged in user$")
    public void iExecuteAndLookUpTheLoggedInUser() {
        Report.log(scenario, "User logged in is: " + System.getProperty("user.name"));
        log.info("User logged in is: " + System.getProperty("user.name"));
    }

    @Then("^The user logged in is (.+)$")
    public void theUserLoggedInIs(String user) {
        if (user.equalsIgnoreCase("whoami")) {
            Report.validate(scenario, "Validating that the correct user has been logged in", "Incorrect user has logged in", System.getProperty("user.name"), System.getProperty("user.name"));
        } else {
            Report.validate(scenario, "Validating that the correct user has been logged in", "Incorrect user has logged in", user, System.getProperty("user.name"));
        }
    }

}
