package com.slotegrator.tests.rest.players.api;

import com.slotegrator.tests.rest.players.api.requests.Credentials;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class Auth extends RestRequest {

    public Auth(Credentials credentials) {
        super(credentials);
    }

    @Step("POST /api/auth/authenticate")
    public Response postAuthenticate(User authenticateUser){
        String AUTH_LOGIN = "tester/login";
        return logResponse(givenWithRequestSpecBuilder(HOST + AUTH_LOGIN)
                .body(authenticateUser)
                .post());
    }
}
