package se.ericnorrwing.weatherboy.model.external.weather;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ApiParameters(
        @JsonProperty("type") int type,
        @JsonProperty("id") int id,
        @JsonProperty("country") String country,
        @JsonProperty("sunrise") long sunrise,
        @JsonProperty("sunset") long sunset
) {}
