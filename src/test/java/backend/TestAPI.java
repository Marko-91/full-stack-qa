package backend;

import com.github.javafaker.Faker;
import globals.Globals;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class TestAPI {
    @Test
    public void test() {
        //HttpRequestHelper.getResponseBody(Globals.endpoint + "users/" + Faker.instance().name().firstName());
        Map<String, String> userInfo = new HashMap<String, String>() {{
            put("firstName", Faker.instance().name().firstName());
            put("lastName", Faker.instance().name().lastName());
            put("password", Faker.instance().harryPotter().quote());
            put("username", Faker.instance().name().username());
        }};

        RequestSpecification request = RestAssured.given();

        JSONObject requestParams = new JSONObject();
        requestParams.put("firstName", userInfo.get("firstName"));
        requestParams.put("lastName", userInfo.get("lastName"));
        //requestParams.put("password", userInfo.get("password"));
        requestParams.put("username", userInfo.get("username"));
        // Add a header stating the Request body is a JSON
        request.header("Content-Type", "application/json");

        // Add the Json to the body of the request
        request.body(requestParams.toJSONString());

        // Post the request and check the response
        Response response = request.put(Globals.endpoint + "users/register");

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 201, "The status code should be equal to 201.");
        String successCode = response.jsonPath().get("SuccessCode");
        //Assert.assertEquals(successCode, "OPERATION_SUCCESS");

        System.out.println("Program finished");
    }
}
