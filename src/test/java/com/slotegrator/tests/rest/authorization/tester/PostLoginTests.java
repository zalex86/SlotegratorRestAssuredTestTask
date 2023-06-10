package com.slotegrator.tests.rest.authorization.tester;

import com.slotegrator.tests.rest.BaseSetup;
import com.slotegrator.tests.rest.players.api.User;
import com.slotegrator.tests.rest.players.api.requests.AuthRq;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import jdk.jfr.Description;
import org.testng.annotations.Test;

import static com.jayway.jsonassert.JsonAssert.with;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static utils.TestUtils.verifyCode;

@Feature("Tester")
@Story("POST /api/tester/login")
public class PostLoginTests extends BaseSetup {

    @Description("POST /api/tester/login")
    @Test(description = "Successful authorization by default user")
    public void authByUser(){
        AuthRq.getInstance().init(credentialsMap.get(ROLE_TESTER));
        String response = verifyCode(AuthRq.getInstance().postAuthenticate(new User()
                .setEmail(TESTER_LOGIN)
                .setPassword(TESTER_PASSWORD)),201).asString();
        assertThat("Json Scheme validation", response,
                matchesJsonSchemaInClasspath("jsonSchemas/loginNew.json"));
        with(response).assertThat(("$.user.email"), equalTo(TESTER_LOGIN));

    }

    @Epic("Expected bugs")
    @Description("POST /api/tester/login")
    @Test(description = "Successful authorization by default user (as documented)")
    public void authByUserOld(){
        AuthRq.getInstance().init(credentialsMap.get(ROLE_TESTER));
        String response = verifyCode(AuthRq.getInstance().postAuthenticate(new User()
                .setEmail(TESTER_LOGIN)
                .setPassword(TESTER_PASSWORD)),200).asString();
        assertThat("Json Scheme validation", response,
                matchesJsonSchemaInClasspath("jsonSchemas/login.json"));
    }
}
