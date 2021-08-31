package africa.za.gambit.stepdefinitions.paf.selenium;

import africa.za.gambit.junit.cucumber.framework.base.TestBase;
import africa.za.gambit.junit.cucumber.framework.supporting.report.Report;
import africa.za.gambit.systems.paf.pageObjects.GoogleSearchPage;
import io.cucumber.java.en.When;
import lombok.extern.log4j.Log4j;

@Log4j
public class TextSearch extends TestBase {

    GoogleSearchPage page = new GoogleSearchPage();

    @When("^I type (.+) and click the search button$")
    public void iTypeAndClickTheSearchButton(String searchText) {
        page.enterText(searchText);
        Report.screenshot(scenario);
        page.clickSearchButton();

        TestBase.setMandatoryReportStepValues(searchText);
    }
}
