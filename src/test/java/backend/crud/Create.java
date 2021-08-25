package backend.crud;

import com.github.javafaker.Faker;
import globals.Globals;
import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static tools.EmailHelper.printData;

public class Create {
    /**
     * TODO
     */
    @Test
    public void testCreateNewUserResponseData() {
        //TODO: Report last name not correctly stored in DB
        System.out.println("Step 1: Create user information");
        Map<String, String> userInfo = new HashMap<String, String>() {{
            put("firstName", Faker.instance().name().firstName());
            put("lastName", Faker.instance().name().lastName());
            put("password", Faker.instance().harryPotter().quote());
            put("username", Faker.instance().name().username());
        }};

        printData(userInfo);

        System.out.println("Step 2: Send request with user payload to server");

        JSONObject requestParams = new JSONObject();
        requestParams.put("firstName", userInfo.get("firstName"));
        requestParams.put("lastName", userInfo.get("lastName"));
        requestParams.put("password", userInfo.get("password"));
        requestParams.put("username", userInfo.get("username"));


        System.out.println("Step 3: Check server response data");
        String response = given()
                .header("Content-Type", "application/json")
                .body(requestParams.toJSONString())
                .put(Globals.endpoint + "users/register")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED)
                .body("username", equalTo(userInfo.get("username")))
                .body("lastName", equalTo(userInfo.get("lastName")))
                .body("firstName", equalTo(userInfo.get("firstName")))
                .extract().asString();

        System.out.println(response); //BUG FOUND last name is counted as first
    }

    /**
     * TODO
     */
    @Test
    public void testCreateNewUserWithIncompleteDataShouldFail() {
        System.out.println("Step 1: Create user information");
        Map<String, String> userInfo = new HashMap<String, String>() {{
            put("firstName", Faker.instance().name().firstName());
            put("lastName", Faker.instance().name().lastName());
            put("password", Faker.instance().harryPotter().quote());
            put("username", Faker.instance().name().username());
        }};

        System.out.println("Step 2: Send incomplete request with user payload to server");

        JSONObject requestParams = new JSONObject();
        requestParams.put("firstName", userInfo.get("firstName"));
        requestParams.put("lastName", userInfo.get("lastName"));
        requestParams.put("username", userInfo.get("username"));


        System.out.println("Step 3: Check server response data");
        String response = given()
                .header("Content-Type", "application/json")
                .body(requestParams.toJSONString())
                .put(Globals.endpoint + "users/register")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                .extract().asString();

        System.out.println(response);

    }
}
