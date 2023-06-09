package com.slotegrator.tests.rest.players.data.responses;

import lombok.Data;

@Data
public class UserLoginRp{
	private String accessToken;
	private UserRp user;
}