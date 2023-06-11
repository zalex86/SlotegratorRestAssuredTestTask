package com.slotegrator.tests.rest;

import com.slotegrator.tests.rest.players.api.requests.PlayerRq;
import com.slotegrator.tests.rest.players.data.responses.PlayerOneRp;
import com.slotegrator.tests.rest.players.data.responses.PlayerRp;
import org.testng.annotations.BeforeClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static utils.PlayerGenerator.getRandomPlayer;

public abstract class BaseGetDeleteSetup extends BaseSetup{
    protected List<PlayerOneRp> players = new ArrayList<>();
    protected List<PlayerOneRp> playersSorted = new ArrayList<>();
    @BeforeClass
    public void create12Users() {
        players = Arrays.asList(PlayerRq.getInstance().getAllPlayers().as(PlayerOneRp[].class));
        if (players.size() < 12) {
            for (int i = 0; i < (12 - players.size()); i++) {
                PlayerRq.getInstance().postPlayerCreate(getRandomPlayer()).as(PlayerRp.class);
            }
        }
        playersSorted = players.stream().sorted(Comparator.comparing(PlayerOneRp::getName)).collect(Collectors.toList());
    }
}
