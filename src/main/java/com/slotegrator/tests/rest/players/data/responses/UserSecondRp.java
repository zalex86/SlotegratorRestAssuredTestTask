package com.slotegrator.tests.rest.players.data.responses;

import lombok.Data;

@Data
public class UserSecondRp {
	private String accessToken;
	private String scope;
	private String tokenType;
	private String expiresIn;
}