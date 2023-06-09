package com.slotegrator.tests.rest.players.data.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerOneRp {
	private String id;
	private String surname;
	private String name;
	@JsonProperty("currency_code")
	private String currencyCode;
	private String email;
	private String username;
}