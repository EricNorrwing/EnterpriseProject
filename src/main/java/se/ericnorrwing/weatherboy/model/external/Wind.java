package se.ericnorrwing.weatherboy.model.external;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Wind(int deg, double speed, double gust) {}