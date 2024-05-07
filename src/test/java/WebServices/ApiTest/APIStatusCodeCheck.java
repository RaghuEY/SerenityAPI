package WebServices.ApiTest;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import static org.hamcrest.Matchers.*;


import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class APIStatusCodeCheck {

    @BeforeClass
    public static void init(){
        RestAssured.baseURI = "https://open.er-api.com";
        RestAssured.basePath="/v6/latest/";
    }


    @Title("Sending Get Request test")
    @Test
    public void testGetRequest(){
//        Response response = SerenityRest.given().when().get("USD");
//        response.prettyPrint();
        Response response = SerenityRest.given()
                            .relaxedHTTPSValidation()
                            .headers(new Headers()).when().get("AED");
        response.prettyPrint();
        response.then().statusCode(200);
        response.then().body("result", equalTo("success"));


    }

}
