package Testcases;

import io.restassured.RestAssured;
import org.junit.BeforeClass;

public class BaseTest {
    @BeforeClass
    public static void init(){
        RestAssured.baseURI = "https://open.er-api.com";
        RestAssured.basePath="/v6/latest/";
    }
}
