package se.ericnorrwing.weatherboy.model.external;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Coordinate(

	@JsonProperty("lon")
	Object lon,

	@JsonProperty("lat")
	Object lat
) {
}