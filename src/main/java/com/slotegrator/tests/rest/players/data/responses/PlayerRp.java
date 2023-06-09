package com.slotegrator.tests.rest.players.data.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.slotegrator.tests.rest.players.data.requests.PlayerBody;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerRp extends PlayerBody {
	@JsonProperty("__v")
	private Integer v;
	@JsonProperty("_id")
	private String id;
	private String surname;
	private String name;
	private String passwordChange;
	private String passwordRepeat;
	private String currencyCode;
	private String email;
	private String username;
}