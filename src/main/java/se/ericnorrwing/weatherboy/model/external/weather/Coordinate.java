package se.ericnorrwing.weatherboy.model.external.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.locationtech.jts.geom.Point;

public record Coordinate(
		@JsonProperty("lon") double longitude,
		@JsonProperty("lat") double latitude
) {}