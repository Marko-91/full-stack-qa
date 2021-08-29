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

public class Get {
    private static final Logger log = Logger.getLogger(Login.class.getName());
    /**
     * This tests validates getting user with existing user name in data base.
     *
     * Test steps:
     * 1. Create user information as payload
     * 2. Register a user to the application and assert that user is registered
     * 3. Get the user using user name that was created and assert server response code
     */
    @Test
    public void testGetUser() {
        //TODO: Report last name not correctly stored in DB
        log.info("Step 1: Create user information as payload");
        Map<String, String> userInfo = createUser();
        printData(userInfo);

        log.info("Step 2: Register a user to the application and assert that user is registered");
        JSONObject requestParams = new JSONObject();
        requestParams.put(User.FIRSTNAME.getName(), userInfo.get(User.FIRSTNAME.getName()));
        requestParams.put(User.LASTNAME.getName(), userInfo.get(User.LASTNAME.getName()));
        requestParams.put(User.PASSWORD.getName(), userInfo.get(User.PASSWORD.getName()));
        requestParams.put(User.USERNAME.getName(), userInfo.get(User.USERNAME.getName()));

        String response = given()
                .header("Content-Type", "application/json")
                .body(requestParams.toJSONString())
                .put(Globals.endpoint + "users/register")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED)
                .body("password", equalTo(userInfo.get(User.PASSWORD.getName())))
                .body("lastName", equalTo(userInfo.get(User.LASTNAME.getName())))
                .body("username", equalTo(userInfo.get(User.USERNAME.getName())))
                .body("firstName", equalTo(userInfo.get(User.FIRSTNAME.getName())))
                .extract().asString();

        System.out.println(response); //BUG FOUND last name is counted as first

        log.info("Step 3: Get the user using user name that was created and assert server response code");
        response = given()
                .header("Content-Type", "application/json")
                .body(requestParams.toJSONString())
                .get(Globals.endpoint + "users/" + userInfo.get(User.USERNAME.getName()))
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract().asString();

        System.out.println(response);
    }
}
