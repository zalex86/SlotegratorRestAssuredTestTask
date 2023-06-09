package com.slotegrator.tests.rest.players.automationTask;

import com.slotegrator.tests.rest.BaseSetup;
import com.slotegrator.tests.rest.players.api.requests.PlayerRq;
import com.slotegrator.tests.rest.players.data.responses.PlayerOneRp;
import com.slotegrator.tests.rest.players.data.responses.PlayerRp;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import static utils.PlayerGenerator.getRandomPlayer;
import static utils.TestUtils.verifyCode;

public class GetAllTests extends BaseSetup {

    @BeforeClass
    public void createPlayer() {
        PlayerRq.getInstance().init(credentialsMap.get(ROLE_TESTER));
        PlayerRq.getInstance().postPlayerCreate(getRandomPlayer()).as(PlayerRp.class);
        PlayerRq.getInstance().postPlayerCreate(getRandomPlayer()).as(PlayerRp.class);
    }

    @Description("POST /api/automationTask/getOne")
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
}
