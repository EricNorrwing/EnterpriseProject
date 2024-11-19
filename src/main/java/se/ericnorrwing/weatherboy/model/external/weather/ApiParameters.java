package se.ericnorrwing.weatherboy.model.external.weather;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record ApiParameters(String country,
							@JsonProperty("sunrise") Instant sunriseTime,
							@JsonProperty("sunset") Instant sunsetTime,
							int id,
							@JsonProperty("type") int apiRequestType
) {}