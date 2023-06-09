package com.slotegrator.tests.rest.players.api.requests;

import com.slotegrator.tests.rest.players.api.Auth;
import com.slotegrator.tests.rest.players.api.User;
import io.restassured.response.Response;


public class AuthRq {

    private static volatile AuthRq instance;
    private final ThreadLocal<Auth> auth = new ThreadLocal<>();

    public void init(Credentials credentials) {
        auth.set(new Auth(credentials));
    }

    public static AuthRq getInstance() {
        AuthRq localInstance = instance;
        if (localInstance == null) {
            synchronized (AuthRq.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new AuthRq();
                }
            }
        }
        return localInstance;
    }

    public Response postAuthenticate(User authenticateUser){
        return auth.get().postAuthenticate(authenticateUser);
    }
}
