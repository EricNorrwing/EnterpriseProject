package se.ericnorrwing.weatherboy.model.external.weather;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Coordinate(
        //TODO Point?
        @JsonProperty("lon") double longitude,
        @JsonProperty("lat") double latitude
) {}

