package se.ericnorrwing.weatherboy.model.external.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ResponseItem(
		@JsonProperty("localNames")
		LocalNames localCityNames,
		@JsonProperty("country")
		String countryCode,
		String name,
		@JsonProperty("lon")
		Long longitude,
		@JsonProperty("lat")
		Long latitude
) {
}
