package com.slotegrator.tests.rest.players.automationTask;

import com.slotegrator.tests.rest.BaseSetup;
import com.slotegrator.tests.rest.players.api.requests.PlayerRq;
import com.slotegrator.tests.rest.players.data.requests.PlayerBody;
import com.slotegrator.tests.rest.players.data.responses.PlayerOneRp;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import static utils.PlayerGenerator.getRandomPlayer;
import static utils.TestUtils.verifyCode;

@Feature("Players")
@Story("POST /api/automationTask/getOne")
public class GetOneTests extends BaseSetup {

    private PlayerOneRp player;

    @BeforeClass
    public void createPlayer() {
        PlayerRq.getInstance().init(credentialsMap.get(ROLE_TESTER));
        player = PlayerRq.getInstance().postPlayerCreate(getRandomPlayer()).as(PlayerOneRp.class);

    }

    @Description("POST /api/automationTask/getOne")
    @Test(description = "Successful getting a player")
    public void getOnePlayer() {
        PlayerBody playerBody = new PlayerBody().setEmail(player.getEmail());
        PlayerRq.getInstance().init(credentialsMap.get(ROLE_TESTER));
        Response response = verifyCode(PlayerRq.getInstance()
                .postGetOnePlayerByEmail(playerBody), 201);
        PlayerOneRp rp = response.as(PlayerOneRp.class);
        assertThat("Json Scheme validation", response.asString(),
                matchesJsonSchemaInClasspath("jsonSchemas/playerOne.json"));
        Assertions.assertThat(rp).isEqualToIgnoringGivenFields(player, "id", "v",
                "passwordChange", "passwordRepeat", "currencyCode");

    }

    @Epic("Expected bugs")
    @Description("POST /api/automationTask/getOne")
    @Test(description = "Successful getting a player (as documeneted)")
    public void getOnePlayerOld() {
        PlayerBody playerBody = new PlayerBody().setEmail(player.getEmail());
        PlayerRq.getInstance().init(credentialsMap.get(ROLE_TESTER));
        Response response = verifyCode(PlayerRq.getInstance()
                .postGetOnePlayerByEmail(playerBody), 200);
        PlayerOneRp rp = response.as(PlayerOneRp.class);
        assertThat("Json Scheme validation", response.asString(),
                matchesJsonSchemaInClasspath("jsonSchemas/deleteOne.json"));
        Assertions.assertThat(rp).isEqualToIgnoringGivenFields(player,
                "id", "passwordChange", "passwordRepeat", "currencyCode");

    }
}
