package se.ericnorrwing.weatherboy.model.external;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ApiParameters(
		//TODO WHERE IS IT USED
	@JsonProperty("country")
	String country,

	@JsonProperty("sunrise")
	int sunrise,

	@JsonProperty("sunset")
	int sunset,

	@JsonProperty("id")
	int id,

	@JsonProperty("type")
	int type
) {
}