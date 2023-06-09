package com.slotegrator.tests.rest.players.automationTask;

import com.slotegrator.tests.rest.BaseSetup;
import com.slotegrator.tests.rest.players.api.requests.PlayerRq;
import com.slotegrator.tests.rest.players.data.responses.PlayerOneRp;
import com.slotegrator.tests.rest.players.data.responses.PlayerRp;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static utils.PlayerGenerator.getRandomPlayer;
import static utils.TestUtils.verifyCode;

public class DeleteOneTests extends BaseSetup {

    private List<Integer> playersIds;

    @BeforeClass
    public void createPlayer() {
        PlayerRq.getInstance().init(credentialsMap.get(ROLE_TESTER));
        List<PlayerOneRp> players = Arrays.asList(PlayerRq.getInstance().getAllPlayers().as(PlayerOneRp[].class));
        playersIds = new ArrayList<>();
        if (players.size() < 12) {
            for (int i = 0; i < (12 - players.size()); i++) {
                PlayerRq.getInstance().postPlayerCreate(getRandomPlayer()).as(PlayerRp.class);
            }
        }
        for (int i = 0; i < players.size(); i++) {
            playersIds.add(i, Integer.parseInt(players.get(i).getId()));
        }
    }

    @Description("POST /api/automationTask/getOne")
    @Test(description = "Successful getting a player")
    public void deleteAllPlayers() {
        PlayerRq.getInstance().init(credentialsMap.get(ROLE_TESTER));
        for (Integer playersId : playersIds) {
            verifyCode(PlayerRq.getInstance()
                    .deleteOnePlayer(playersId), 201);
        }

        Assert.assertEquals(PlayerRq.getInstance().getAllPlayers().as(PlayerOneRp[].class).length, 0);
    }
}
