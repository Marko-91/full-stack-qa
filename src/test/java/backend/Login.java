package backend;

import globals.Globals;
import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tools.User;
import tools.UserHelper;

import java.util.Map;
import java.util.logging.Logger;

import static io.restassured.RestAssured.given;

public class Login {
    private static final Logger LOGGER = Logger.getLogger(Login.class.getName());

    /**
     * This test validates login using data driven framework in testNG with selenium.
     *
     * Test steps:
     *
     * 1. Create user parameters as payload
     * 2. Register a user with previously given parameters and assert registration
     * 3. Assert that previously created user logs in correctly
     */
    @Test(dataProvider = "dataFeed")
    public void testLoginUser(String firstName, String lastName, String password, String userName) {
        //TODO: Report last name not correctly stored in DB
        LOGGER.info("Step 1: Create user parameters as payload");
        JSONObject requestParams = new JSONObject();
        requestParams.put(User.FIRSTNAME.getName(), firstName);
        requestParams.put(User.LASTNAME.getName(), lastName);
        requestParams.put(User.PASSWORD.getName(), password);
        requestParams.put(User.USERNAME.getName(), userName);


        LOGGER.info("Step 2: Register a user with previously given parameters and assert registration");
        given()
                .header("Content-Type", "application/json")
                .body(requestParams.toJSONString())
                .put(Globals.endpoint + "users/register")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED)
                .extract().asString();

        LOGGER.info("Step 3: Assert that created user logs in correctly");
        given()
                .header("Content-Type", "application/json")
                .body(requestParams.toJSONString())
                .post(Globals.endpoint + "users/login")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract().asString();
    }

    @DataProvider(name = "dataFeed")
    public Object[][] dataFeed() {
        Map user_1 = UserHelper.createUser();
        Map user_2 = UserHelper.createUser();
        return new Object[][] {
                { user_1.get(User.FIRSTNAME.getName()), user_1.get(User.LASTNAME.getName()), user_1.get(User.PASSWORD.getName()), user_1.get(User.USERNAME.getName()) },
                { user_2.get(User.FIRSTNAME.getName()), user_2.get(User.LASTNAME.getName()), user_2.get(User.PASSWORD.getName()), user_2.get(User.USERNAME.getName()) },
        };

    }
}
