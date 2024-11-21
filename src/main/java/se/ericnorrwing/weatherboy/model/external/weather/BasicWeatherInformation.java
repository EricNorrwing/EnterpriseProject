package se.ericnorrwing.weatherboy.model.external.weather;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BasicWeatherInformation(
		@JsonProperty("temp") double temperature,
		@JsonProperty("temp_min") double temperatureMinimum,
		@JsonProperty("grnd_level") int atmosphericPressureAtGroundLevel,
		@JsonProperty("humidity") double humidityInPercent,
		@JsonProperty("pressure") int pressureAtSeaLevel,
		@JsonProperty("sea_level") int atmosphericPressureAtSeaLevel,
		@JsonProperty("feels_like") double feelsLike,
		@JsonProperty("temp_max") double tempMax
) {}