package se.ericnorrwing.weatherboy.model.external.weather;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Optional;

public record Cloud(@JsonProperty("all") Optional<Integer> cloudDensityInPercent) {}