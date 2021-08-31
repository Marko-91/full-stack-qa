package tools;

import com.github.javafaker.Faker;
import globals.Globals;
import org.apache.commons.lang3.RandomUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class UserHelper {

    /**
     * This method will create a new user as a key-value map.
     * User fields: first name, last name, password, user name
     * @return User with key value pairs.
     */
    public static Map createUser() {
        Faker faker = new Faker();

        return new HashMap<String, String>() {{
            put(User.FIRSTNAME.getName(), faker.name().firstName());
            put(User.LASTNAME.getName(), faker.name().lastName());
            put(User.PASSWORD.getName(), faker.animal().name());
            put(User.USERNAME.getName(), faker.name().username());
        }};
    }

    public static String getRandomEmail(String firstName) {
        return firstName + "@gmail.com";
    }

    public static int getRandomNumber(int in_maxNumber) {
        Random l_randomNumber = new Random();
        return l_randomNumber.nextInt(in_maxNumber);
    }

    public static void printData(Map <String, String> map) {
        System.out.println("[TEST] User data:");
        map.forEach((key, value) -> System.out.println(key + " " + value));
    }
}
