package se.ericnorrwing.weatherboy.model.external;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Cloud(

	@JsonProperty("all")
	int all
) {
}