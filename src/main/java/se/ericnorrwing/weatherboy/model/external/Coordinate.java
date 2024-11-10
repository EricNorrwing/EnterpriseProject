package se.ericnorrwing.weatherboy.model.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.locationtech.jts.geom.Point;

public record Coordinate(
		//TODO If Point does not work, find other solution
		@JsonProperty("lon") Point longitude,
		@JsonProperty("lat") Point latitude
) {}