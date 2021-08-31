package africa.za.gambit.stepdefinitions.paf.selenium;

import africa.za.gambit.junit.cucumber.framework.base.TestBase;
import africa.za.gambit.junit.cucumber.framework.supporting.report.Report;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import lombok.extern.log4j.Log4j;

@Log4j
public class PageTitleVerify extends TestBase {

    @And("^I am on the google search page$")
    public void iAmOnTheGoogleSearchPage() {
        String title = driver.getTitle();
        Report.validate(scenario, "Validating page title", "The incorrect page title has been identified", title, "Google");
    }

    @Then("^The page title expected to display is (.+)$")
    public void thePageTitleExpectedToDisplayIs(String title) {
        String actualTitle = driver.getTitle();
        Report.validate(scenario, "Validating page title", "The incorrect page title has been identified", title, actualTitle);
    }

    @Then("^The correct page title displayed$")
    public void theCorrectPageTitleDisplayed() {
        String actualTitle = driver.getTitle();
        Report.validate(scenario, "Validating page title", "The incorrect page title has been identified", "hooligan - Google Search", actualTitle);
    }
}
