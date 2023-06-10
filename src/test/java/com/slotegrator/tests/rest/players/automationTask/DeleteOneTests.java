package com.slotegrator.tests.rest.players.automationTask;

import com.slotegrator.tests.rest.BaseSetup;
import com.slotegrator.tests.rest.players.api.requests.PlayerRq;
import com.slotegrator.tests.rest.players.data.responses.PlayerOneRp;
import com.slotegrator.tests.rest.players.data.responses.PlayerRp;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.assertj.core.api.Assertions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import static utils.PlayerGenerator.getRandomPlayer;
import static utils.TestUtils.verifyCode;

@Feature("Players")
@Story("DELETE /api/automationTask/deleteOne")
public class DeleteOneTests extends BaseSetup {

    private List<Integer> playersIds;
    private List<PlayerOneRp> players;

    @BeforeClass
    public void createPlayer() {
        PlayerRq.getInstance().init(credentialsMap.get(ROLE_TESTER));
        players = Arrays.asList(PlayerRq.getInstance().getAllPlayers().as(PlayerOneRp[].class));
        playersIds = new ArrayList<>();
        if (players.size() < 12) {
            for (int i = 0; i < (12 - players.size()); i++) {
                PlayerRq.getInstance().postPlayerCreate(getRandomPlayer()).as(PlayerRp.class);
            }
        }

    }

    @Epic("Expected bugs")
    @Description("DELETE /api/automationTask/deleteOne/{id}")
    @Test(description = "Successful deleting all players")
    public void deleteAllPlayers() {
        try {
            for (int i = 0; i < players.size(); i++) {
                playersIds.add(i, players.get(i).getId());
            }
            for (PlayerOneRp player : players) {
                Response response = verifyCode(PlayerRq.getInstance()
                        .deleteOnePlayer(player.getId()), 201);
                assertThat("Json Scheme validation", response.asString(),
                        matchesJsonSchemaInClasspath("jsonSchemas/deleteOne.json"));
                Assertions.assertThat(response.as(PlayerOneRp.class)).isEqualToIgnoringGivenFields(player,
                        "currencyCode");
            }
            Assert.assertEquals(PlayerRq.getInstance().getAllPlayers().as(PlayerOneRp[].class).length, 0);
        } catch (RuntimeException e) {
            System.out.println("Players with null ids cannot be deleted");
        }
        Assert.assertFalse(playersIds.isEmpty(), "Check if the list of ids is not empty before deleting");
        Assertions.assertThat(playersIds).doesNotContainNull();
    }
}
