package com.slotegrator.tests.rest.players.api.requests;

import com.slotegrator.tests.rest.players.api.Player;
import com.slotegrator.tests.rest.players.data.requests.PlayerBody;
import io.restassured.response.Response;


public class PlayerRq {

    private static volatile PlayerRq instance;
    private final ThreadLocal<Player> player = new ThreadLocal<>();

    public void init(Credentials credentials) {
        player.set(new Player(credentials));
    }

    public static PlayerRq getInstance() {
        PlayerRq localInstance = instance;
        if (localInstance == null) {
            synchronized (PlayerRq.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new PlayerRq();
                }
            }
        }
        return localInstance;
    }

    public Response postPlayerCreate(PlayerBody playerBody){
        return player.get().postPlayerCreate(playerBody);
    }

    public Response postGetOnePlayerByEmail(PlayerBody playerEmail) {
        return player.get().postGetOnePlayer(playerEmail);
    }

    public Response getAllPlayers() {
        return player.get().getAllPlayers();
    }

    public Response deleteOnePlayer(Integer id) {
        return player.get().deleteOnePlayer(id);
    }
}
