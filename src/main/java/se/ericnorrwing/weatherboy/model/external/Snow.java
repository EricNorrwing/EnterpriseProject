package se.ericnorrwing.weatherboy.model.external;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Snow(@JsonProperty("snow") double snowPerHourInMillimeters) {
}
