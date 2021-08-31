package africa.za.gambit.systems.paf.webservice;

import africa.za.gambit.junit.cucumber.framework.base.TestBase;
import africa.za.gambit.junit.cucumber.framework.supporting.report.Report;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j;

import static io.restassured.RestAssured.given;

@Log4j
public class AgeOfEmpires extends TestBase {

    private String webserviceName = "framework.webService.age-of-empires";
    private String apiUrl;

    public Response doUnit(String id) {
        apiUrl = TestBase.setApiUrl(webserviceName);
        String apiOperation = "/unit/";

        RequestSpecBuilder rsb = new RequestSpecBuilder();
        rsb.setBaseUri(apiUrl + apiOperation + id);
        rsb.setRelaxedHTTPSValidation();
        Response response = given()
                .spec(rsb.build())
                .log().uri().log().method().log().body()
                .get()
                .thenReturn();
        Report.log(scenario, "Webservice call to api", response.getBody().prettyPrint());
        return response;
    }
}