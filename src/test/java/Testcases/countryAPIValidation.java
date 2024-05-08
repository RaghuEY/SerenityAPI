package Testcases;

import Steps.WebServiceApiSteps;
import io.restassured.RestAssured;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class countryAPIValidation {
    @Steps
    WebServiceApiSteps stepRef;

    @BeforeClass
    public static void init(){
        RestAssured.baseURI = "https://open.er-api.com";
        RestAssured.basePath="/v6/latest/";
    }

    @Title("Getting the Country price details and validating it")
    @Test
    public void validateCountryPrice(){
        stepRef.validateStatusCode(200);
        stepRef.verifyResponseBody("result","success");
        stepRef.sendGetRequestForCountryAndValidatePriceRange("AED", 3.6,3.7);
    }

    @Title("Validate the Currency pairs returned by API")
    @Test
    public void validateCurrentPairCount(){
        stepRef.validateStatusCode(200);
        stepRef.verifyResponseBody("result","success");
        stepRef.getCurrencyPairCount();

    }

    @Title("Validate the Json Schema against the API Response")
    @Test
    public void validateJsonSchema(){
        stepRef.validateStatusCode(200);
        stepRef.verifyResponseBody("result","success");
        stepRef.verifyJSONSchema();
    }

    @Title("Validate the API Response Time")
    @Test
    public void validateApiResponseTime(){
        stepRef.validateStatusCode(200);
        stepRef.verifyResponseBody("result","success");
        stepRef.valAPIResponseTime();
    }
}
