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
import static org.hamcrest.MatcherAssert.assertThat;
import static tools.UserHelper.createUser;
import static tools.UserHelper.printData;

public class Delete {
    private static final Logger log = Logger.getLogger(Login.class.getName());

    /**
     * This test validates creating user, then deleting the same user from data base.
     *
     * Test steps:
     * 1. Create user information as payload
     * 2. Register a user and assert that user is registered
     * 3. Delete user that was created
     * 4. Assert that server response contains the relevant message
     */
    @Test
    public void testCreateAndDeleteUser() {
        log.info("Step 1: Create user information as payload");
        Map<String, String> userInfo = createUser();
        printData(userInfo);

        log.info("Step 2: Register a user to the application and assert that user is registered");
        JSONObject requestParams = new JSONObject();
        requestParams.put(User.FIRSTNAME.getName(), userInfo.get(User.FIRSTNAME.getName()));
        requestParams.put(User.LASTNAME.getName(), userInfo.get(User.LASTNAME.getName()));
        requestParams.put(User.PASSWORD.getName(), userInfo.get(User.PASSWORD.getName()));
        requestParams.put(User.USERNAME.getName(), userInfo.get(User.USERNAME.getName()));
        printData(userInfo);

        String response = given()
                .header("Content-Type", "application/json")
                .body(requestParams.toJSONString())
                .put(Globals.endpoint + "users/register")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED)
                .extract().asString();

        System.out.println(response); //BUG FOUND last name is counted as first

        response = given()
                .header("Content-Type", "application/json")
                .body(requestParams.toJSONString())
                .delete(Globals.endpoint + "users/" + userInfo.get("username"))
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract().asString();

        assertThat("Response should contain removed message",
                response.contains("User with username " + userInfo.get("username") + " is removed"));
        System.out.println(response);
    }

    @Test
    /**
     * This test validates sanity usage of deleting all users in DB
     */
    public void testDeleteAllUsers() {

        String response = given()
                .header("Content-Type", "application/json")
                .delete(Globals.endpoint + "users/deleteAllUsers")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract().asString();

        assertThat("Response should contain all removed message",
                response.contains("All users removed"));
        System.out.println(response);
    }
}
