package se.ericnorrwing.weatherboy.model.external.weather;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BasicWeatherInformation(
        @JsonProperty("temp") double temp,
        @JsonProperty("feels_like") double feelsLike,
        @JsonProperty("temp_min") double tempMin,
        @JsonProperty("temp_max") double tempMax,
        @JsonProperty("pressure") int pressure,
        @JsonProperty("humidity") int humidity,
        @JsonProperty("sea_level") int seaLevel,
        @JsonProperty("grnd_level") int grndLevel
) {}

