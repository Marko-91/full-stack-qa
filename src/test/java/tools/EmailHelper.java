package tools;

import globals.Globals;
import org.apache.commons.lang3.RandomUtils;

import java.util.Random;

public class EmailHelper {

    public static String getRandomEmail(String firstName) {
        return firstName + getRandomNumber(999999999) + "@gmail.com";
    }

    public static int getRandomNumber(int in_maxNumber) {
        Random l_randomNumber = new Random();
        return l_randomNumber.nextInt(in_maxNumber);
    }
}
