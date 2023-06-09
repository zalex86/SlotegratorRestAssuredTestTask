package com.slotegrator.tests.rest.players.api;


import com.slotegrator.tests.rest.players.api.requests.Credentials;
import com.slotegrator.tests.rest.players.data.requests.PlayerBody;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class Player extends RestRequest {
    private static final String CREATE = "automationTask/create";
    private static final String GET_ONE = "automationTask/getOne";
    private static final String GET_ALL = "automationTask/getAll";
    private static final String DELETE_ONE = "automationTask/deleteOne";

    public Player(Credentials credentials) {
        super(credentials);
    }

    @Step("Create a user POST /api/automationTask/create")
    public Response postPlayerCreate(PlayerBody playerBody) {
        return logResponse(givenWithAuth(host + CREATE)
                .when()
                .body(playerBody)
                .post());
    }

    @Step("Get one player POST /api/automationTask/getOne")
    public Response postGetOnePlayer(PlayerBody playerEmail) {
        return logResponse(givenWithAuth(host + GET_ONE)
                .when().body(playerEmail)
                .post());
    }

    @Step("Get all players GET /api/automationTask/getAll")
    public Response getAllPlayers() {
        return logResponse(givenWithAuth(host + GET_ALL)
                .when()
                .get());
    }

    @Step("Delete one player DELETE /api/automationTask/deleteOne/{0}")
    public Response deleteOnePlayer(int id) {
        return logResponse(givenWithAuth(host + DELETE_ONE)
                .when().body(id)
                .delete());
    }
}
