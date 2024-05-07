package Steps;

import io.restassured.http.Headers;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.hamcrest.MatcherAssert;
import org.jruby.RubyBoolean;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;

public class WebServiceApiSteps {

    private Response response;
    @Step("Send get request for the country id : {0} and {1} {2}")
    public void sendGetRequestForCountryAndValidatePriceRange(String countryId, double from, double to){
        response = SerenityRest.given()
                .relaxedHTTPSValidation()
                .headers(new Headers()).when().get(countryId);
        response.prettyPrint();
        float currRate = response.jsonPath().get("rates.USD");
        boolean res = (currRate > from && currRate < to);
        if (res){
            MatcherAssert.assertThat("Currency in range",res);
        } else{
            MatcherAssert.assertThat("USD Currency " +currRate  +" not in range between " +from +" and " +to,res);
        }
        //int currPairSize = response.jsonPath().get("rates");
    }


    @Step("get the currency pair count")
    public void getCurrencyPairCount(){
        response = SerenityRest.given()
                .relaxedHTTPSValidation()
                .headers(new Headers()).when().get("AED");
        response.prettyPrint();
        Object listVal = response.jsonPath().get("rates");
        boolean res = (listVal.equals(162));
        if (res){
            MatcherAssert.assertThat("Currency pair count is equal to 162 ",res);
        } else{
            MatcherAssert.assertThat("Currency pair count is not equal to 162 ",res);
        }

    }

    @Step("Validate the Status Code and Status Message to be {0}  and {1}")
    public void validateStatusCode(int code){
        response = SerenityRest.given()
                .relaxedHTTPSValidation()
                .headers(new Headers()).when().get("AED");
        response.then().statusCode(code);
        //response.then().body("result", equalTo("success"));
    }
    @Step("Validate the Response body for key {0} and value {1}")
    public void verifyResponseBody(String key,String value){

        response.then().body(key, equalTo(value));

    }
}
