package com.slotegrator.tests.rest.players.data.requests;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Accessors(chain = true)
public class PlayerBody{
	private String currencyCode;
	private String email;
	private String username;
	private String passwordChange;
	private String passwordRepeat;
	private String surname;
	private String name;


}