package backend.crud;

import backend.Login;
import globals.Globals;
import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import tools.User;
import tools.UserHelper;

import java.util.Map;
import java.util.logging.Logger;

import static io.restassured.RestAssured.given;

public class Put {

    private static final Logger LOGGER = Logger.getLogger(Login.class.getName());
    /**
     * This test validates login using data driven framework in testNG with selenium.
     *
     * Test steps:
     *
     * 1. Create user parameters as payload
     * 2. Assert that the server response code is 201 i.e. user is created
     */
    @Test
    public void testRegisterUser() {
        LOGGER.info("Step 1: Create user parameters as payload");

        Map user = UserHelper.createUser();
        JSONObject requestParams = new JSONObject();
        requestParams.put(User.FIRSTNAME.getName(), user.get(User.FIRSTNAME.getName()));
        requestParams.put(User.LASTNAME.getName(), user.get(User.LASTNAME.getName()));
        requestParams.put(User.PASSWORD.getName(), user.get(User.PASSWORD.getName()));
        requestParams.put(User.USERNAME.getName(), user.get(User.USERNAME.getName()));

        LOGGER.info("Assert that the server response code is 201 i.e. user is created");
        given()
                .header("Content-Type", "application/json")
                .body(requestParams.toJSONString())
                .put(Globals.endpoint + "users/register")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED)
                .extract().asString();
    }
}
