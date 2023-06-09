package utils;

import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;

public class TestUtils {
    public static Response verifyCode(Response response, Integer statusCode) {
        return response.then().statusCode(statusCode).extract().response();
    }

    public static String getRandomString(int length) {
        return RandomStringUtils.randomAlphabetic(length).toLowerCase();
    }
}
