package se.ericnorrwing.weatherboy.model.external.weather;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Snow(@JsonProperty("snow") double snowPerHourInMillimeters) {
}
