package backend.crud;

import backend.Login;
import globals.Globals;
import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import tools.User;

import java.util.Map;
import java.util.logging.Logger;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static tools.UserHelper.createUser;
import static tools.UserHelper.printData;

public class Create {
    private static final Logger log = Logger.getLogger(Login.class.getName());

    /**
     * This test validates creation of a new user
     *
     * Test steps:
     * 1. Create user information
     * 2. Register a user and check that data sent to server equal to data on the server
     */
    @Test
    public void testCreateNewUserResponseData() {
        log.info("Step 1: Create user information as payload");
        Map<String, String> userInfo = createUser();

        log.info("Step 2: Register a user to the application and assert that user is registered");
        JSONObject requestParams = new JSONObject();
        requestParams.put(User.FIRSTNAME.getName(), userInfo.get(User.FIRSTNAME.getName()));
        requestParams.put(User.LASTNAME.getName(), userInfo.get(User.LASTNAME.getName()));
        requestParams.put(User.PASSWORD.getName(), userInfo.get(User.PASSWORD.getName()));
        requestParams.put(User.USERNAME.getName(), userInfo.get(User.USERNAME.getName()));
        printData(userInfo);

        log.info("Step 3: Register a user and check that data sent to server equal to data on the server");
        String response = given()
                .header("Content-Type", "application/json")
                .body(requestParams.toJSONString())
                .put(Globals.endpoint + "users/register")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED)
                .body(User.LASTNAME.getName(), equalTo(userInfo.get(User.LASTNAME.getName())))
                .body(User.USERNAME.getName(), equalTo(userInfo.get(User.USERNAME.getName())))
                .body(User.FIRSTNAME.getName(), equalTo(userInfo.get(User.FIRSTNAME.getName())))
                .extract().asString();

        System.out.println(response);
    }

    /**
     * This test validates that sending server incomplete data should return response code 500.
     *
     * Test steps:
     * 1. Create user with incomplete data
     * 2. Send incomplete request with user payload to server
     * 3. Assert that server response is 500
     */
    @Test
    public void testCreateNewUserWithIncompleteDataShouldFail() {
        log.info("Step 1: Create user with incomplete data");
        Map<String, String> userInfo = createUser();
        JSONObject requestParams = new JSONObject();
        requestParams.put(User.FIRSTNAME.getName(), userInfo.get(User.FIRSTNAME.getName()));
        requestParams.put(User.LASTNAME.getName(), userInfo.get(User.LASTNAME.getName()));
        requestParams.put(User.USERNAME.getName(), userInfo.get(User.USERNAME.getName()));
        printData(userInfo);

        System.out.println("Step 2: Send incomplete request with user payload to server");

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
