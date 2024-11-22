package se.ericnorrwing.weatherboy.model.external.weather;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Wind(
        @JsonProperty("speed") double speed,
        @JsonProperty("deg") int deg,
        @JsonProperty("gust") double gust
) {}

