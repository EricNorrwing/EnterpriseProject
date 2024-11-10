package se.ericnorrwing.weatherboy.model.external;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Rain (@JsonProperty("1h") double rainPerHourInMillimeters)  {}
