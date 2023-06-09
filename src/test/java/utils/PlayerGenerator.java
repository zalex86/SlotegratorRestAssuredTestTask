package utils;

import com.slotegrator.tests.rest.players.data.requests.PlayerBody;

import static utils.TestUtils.getRandomString;

public class PlayerGenerator {
    public static PlayerBody getRandomPlayer(){
        String password = getRandomString(6);
        return new PlayerBody()
                .setCurrencyCode(getRandomString(3))
                .setEmail(getRandomString(5) + "@" + getRandomString(2) + ".dev")
                .setName(getRandomString(4))
                .setSurname(getRandomString(5))
                .setPasswordChange(password)
                .setPasswordRepeat(password)
                .setUsername(getRandomString(5));
    }
}
