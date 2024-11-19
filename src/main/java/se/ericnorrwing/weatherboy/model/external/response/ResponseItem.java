package se.ericnorrwing.weatherboy.model.external.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ResponseItem(
		@JsonProperty("local_names")
		LocalNames localCityNames,
		@JsonProperty("country")
		String countryCode,
		String name,
		@JsonProperty("lon")
		double longitude,
		@JsonProperty("lat")
		double latitude
) {
}
