package se.ericnorrwing.weatherboy.model.external.weather;

import com.fasterxml.jackson.annotation.JsonProperty;

public record WeatherDescription(
        @JsonProperty("id") int id,
        @JsonProperty("main") String main,
        @JsonProperty("description") String description,
        @JsonProperty("icon") String icon
) {}
