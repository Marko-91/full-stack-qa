package tools;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class HttpRequestHelper {

    public static void getResponseBody(String endpoint){
        given().when().get(endpoint).then().log()
                .all();

    }

    public static void getResponseBody(String endpoint, Map<String, String> queryParams){
        queryParams.forEach((k, v) -> {

        });
        given().queryParam("CUSTOMER_ID","68195")
                .queryParam("PASSWORD","1234!")
                .queryParam("Account_No","1")
                .when().get(endpoint).then().log()
                .body();


    }


}
