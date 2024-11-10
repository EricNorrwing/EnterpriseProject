package se.ericnorrwing.weatherboy.model.external;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BasicWeatherInformation(

	@JsonProperty("temp")
	Object temp,

	@JsonProperty("temp_min")
	Object tempMin,

	@JsonProperty("grnd_level")
	int grndLevel,

	@JsonProperty("humidity")
	int humidity,

	@JsonProperty("pressure")
	int pressure,

	@JsonProperty("sea_level")
	int seaLevel,

	@JsonProperty("feels_like")
	Object feelsLike,

	@JsonProperty("temp_max")
	Object tempMax
) {
}