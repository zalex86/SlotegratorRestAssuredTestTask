package com.slotegrator.tests.rest.players.automationTask;

import com.slotegrator.tests.rest.BaseSetup;
import com.slotegrator.tests.rest.players.api.requests.PlayerRq;
import com.slotegrator.tests.rest.players.data.requests.PlayerBody;
import com.slotegrator.tests.rest.players.data.responses.PlayerRp;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import static utils.PlayerGenerator.getRandomPlayer;
import static utils.TestUtils.verifyCode;

@Feature("Players")
@Story("POST /api/automationTask/create")
public class PostCreateTests extends BaseSetup {

    @Description("POST /api/automationTask/create")
    @Test(description = "Successful creation of a player")
    public void createPlayer() {
        PlayerBody player = getRandomPlayer();
        PlayerRq.getInstance().init(credentialsMap.get(ROLE_TESTER));
        Response response = verifyCode(PlayerRq.getInstance().postPlayerCreate(player), 201);
        PlayerRp rp = response.as(PlayerRp.class);
        assertThat("Json Scheme validation", response.asString(),
                matchesJsonSchemaInClasspath("jsonSchemas/player.json"));
        Assertions.assertThat(rp).isEqualToIgnoringGivenFields(player, "id", "v",
                "passwordChange", "passwordRepeat", "currencyCode");
    }

    @Epic("Expected bugs")
    @Description("POST /api/automationTask/create")
    @Test(description = "Successful creation of a player (as documented)")
    public void createPlayerOld() {
        PlayerBody player = getRandomPlayer();
        PlayerRq.getInstance().init(credentialsMap.get(ROLE_TESTER));
        Response response = verifyCode(PlayerRq.getInstance().postPlayerCreate(player), 201);
        PlayerRp rp = response.as(PlayerRp.class);
        assertThat("Json Scheme validation", response.asString(),
                matchesJsonSchemaInClasspath("jsonSchemas/deleteOne.json"));
        Assertions.assertThat(rp).isEqualToIgnoringGivenFields(player,
                "id", "passwordChange", "passwordRepeat", "currencyCode");
    }
}
