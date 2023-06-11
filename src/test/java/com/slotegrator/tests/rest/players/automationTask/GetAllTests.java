package com.slotegrator.tests.rest.players.automationTask;

import com.slotegrator.tests.rest.BaseGetDeleteSetup;
import com.slotegrator.tests.rest.players.api.requests.PlayerRq;
import com.slotegrator.tests.rest.players.data.responses.PlayerOneRp;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import static utils.TestUtils.verifyCode;

@Feature("Players")
@Story("GET /api/automationTask/getAll")
public class GetAllTests extends BaseGetDeleteSetup {

    @Description("GET /api/automationTask/getAll")
    @Test(description = "Successful getting all players")
    public void getAllPlayers() {
        PlayerRq.getInstance().init(credentialsMap.get(ROLE_TESTER));
        Response response = verifyCode(PlayerRq.getInstance()
                .getAllPlayers(), 200);
        List<PlayerOneRp> rp = Arrays.asList(response.as(PlayerOneRp[].class));
        assertThat("Json Scheme validation", response.asString(),
                matchesJsonSchemaInClasspath("jsonSchemas/playerGetAll.json"));
        Assertions.assertThat(rp.size()).isGreaterThan(2);
    }

    @Epic("Expected bugs")
    @Description("GET /api/automationTask/getAll")
    @Test(description = "Successful getting all players (As Documented)")
    public void getAllPlayersOld() {
        PlayerRq.getInstance().init(credentialsMap.get(ROLE_TESTER));
        Response response = verifyCode(PlayerRq.getInstance()
                .getAllPlayers(), 200);
        List<PlayerOneRp> rp = Arrays.asList(response.as(PlayerOneRp[].class));
        assertThat("Json Scheme validation", response.asString(),
                matchesJsonSchemaInClasspath("jsonSchemas/deleteOne.json"));
        Assertions.assertThat(rp.size()).isGreaterThan(2);
    }
}
