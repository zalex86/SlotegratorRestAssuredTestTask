package com.slotegrator.tests.rest;

import com.slotegrator.tests.rest.players.api.requests.Credentials;
import org.testng.annotations.BeforeSuite;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class BaseSetup {
    protected static final ConcurrentMap<String, Credentials> credentialsMap = new ConcurrentHashMap<>();
    public static final String ROLE_TESTER = "ROLE_TESTER";
    public final String TESTER_LOGIN = "a.zaitsev.qa@gmail.com";
    public final String TESTER_PASSWORD = "pAWBNAU4EF6l";
    @BeforeSuite
    public void beforeSuite() {
        credentialsMap.put("ROLE_TESTER", new Credentials(TESTER_LOGIN, TESTER_PASSWORD));
    }
}
