package se.ericnorrwing.weatherboy.model.external.weather;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Rain (@JsonProperty("1h") double rainPerHourInMillimeters)  {}
