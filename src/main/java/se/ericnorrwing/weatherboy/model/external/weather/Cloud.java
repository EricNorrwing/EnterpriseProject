package se.ericnorrwing.weatherboy.model.external.weather;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Cloud(@JsonProperty("all") int cloudDensityInPercent
) {
}